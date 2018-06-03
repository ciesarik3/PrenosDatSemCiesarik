package sk.fri.uniza.microservice;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import java.util.List;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

/**
 * Trieda definuje privilégia účastníkov a ich autorizovanie pred vstupom do aplikácie 
 * @author Roman Ciesarík
 */

public class ExampleAuthenticator implements Authenticator<BasicCredentials, User> {
    /**
     * Valid users with mapping user -> roles
     */
    private static final Map<String, Set<String>> VALID_USERS = ImmutableMap.of(
        "guest", ImmutableSet.of(),
        "user", ImmutableSet.of("BASIC_USER"),
        "admin", ImmutableSet.of("ADMIN", "BASIC_USER")
    );
    
    /**
     * vytvorenie "sesion" pre overenie užívateľa v databázi
     * odvolanie na projekt hibernate cvičenia
     */
    
    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    SessionFactory buildSessionFactory = new MetadataSources(registry).addResource("hibernate.cfg.xml").buildMetadata().buildSessionFactory();
    
    /**
     * Funkcia pre prihlásenie. Najprv overí kodovo zadaných účastníkov, a ak nenájde zhodu potom otvorí komunikáciu s databázou a porovná s účastníkmi v databáze 
     * @param credentials BasicCredentials
     * @throws AuthenticationException
     * @return Optional User  
     */  
    
    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        
        if (VALID_USERS.containsKey(credentials.getUsername()) && "heslo".equals(credentials.getPassword())) {
            return Optional.of(new User(credentials.getUsername(), VALID_USERS.get(credentials.getUsername())));
        }
        
        Session session = buildSessionFactory.openSession();
        session.beginTransaction();
        Query ucastnici= session.createQuery("SELECT u.meno, u.heslo, u.privilegia FROM ucastnik u where u.meno = :meno and u.heslo = :heslo");
        ucastnici.setParameter("meno", credentials.getUsername());
        ucastnici.setParameter("heslo", credentials.getPassword());
        
        session.close();
        StandardServiceRegistryBuilder.destroy(registry);
        
        if (ucastnici.list().size() > 0) {
            return Optional.of(new User(credentials.getUsername(), VALID_USERS.get(credentials.getUsername())));
        }
        
        
       //List <Uzivatel> u =  session2.createQuery("FROM Uzivatel AS uz where uz.meno = :meno").setParameter("meno",credentials.getUsername()).getResultList();
//        String hql = "SELECT E.meno, E.heslo FROM ucastnik E";
//        Query query1 = session.createQuery(hql);
//        List meno = query1.list();
//       
//        String hq2 = "SELECT E.heslo FROM ucastnik E";
//        Query query2 = session.createQuery(hq2);
//        List heslo = query2.list();
        
        
                
               // SELECT  heslo, meno FROM 
        /*
        if (VALID_USERS.containsKey(credentials.getUsername()) && "heslo".equals(credentials.getPassword())) {
            return Optional.of(new User(credentials.getUsername(), VALID_USERS.get(credentials.getUsername())));
        }*/
       
        return Optional.empty();
    }
}