package sk.fri.uniza.microservice;
/**
 *
 * @author hudik1, Ciesarik
 */
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import java.util.Map;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.hibernate.SessionFactory;

//import com.example.helloworld.resources.HelloWorldResource;
//import com.example.helloworld.health.TemplateHealthCheck;
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    static SessionFactory buildSessionFactory;

    private final HibernateBundle<HelloWorldConfiguration> hibernateBundle = new HibernateBundle<HelloWorldConfiguration>(Ucastnik.class) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(HelloWorldConfiguration t) {
            return t.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Exception {

        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {

        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new ViewBundle<HelloWorldConfiguration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(HelloWorldConfiguration configuration) {

                return super.getViewConfiguration(configuration); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
            Environment environment) {

        final UcastnikDAO dao = new UcastnikDAO(hibernateBundle.getSessionFactory());
        final DataDAO dao2 = new DataDAO(hibernateBundle.getSessionFactory());
        final WEMOSDAO dao3 = new WEMOSDAO(hibernateBundle.getSessionFactory());
        
        final UcastnikResource ucastnikResource = new UcastnikResource(dao);
        final DataResource dataResource = new DataResource(dao2);
        final WEMOSResource wemosResource = new WEMOSResource(dao3);
        

        final TemplateHealthCheck healthCheck
                = new TemplateHealthCheck(configuration.getTemplate());

        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new ExampleAuthenticator())
                .setAuthorizer(new ExampleAuthorizer())
                .setRealm("SUPER SECRET STUFF")
                .buildAuthFilter()));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);

        environment.healthChecks().register("template", healthCheck);
        
        environment.jersey().register(ucastnikResource);
        environment.jersey().register(wemosResource);  
        environment.jersey().register(dataResource);

    }

}
