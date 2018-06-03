package sk.fri.uniza.microservice;

import io.dropwizard.auth.Authorizer;

/**
 * Trieda na definíciu kontroly
 * @author Roman Ciesarík
 */

public class ExampleAuthorizer implements Authorizer<User> {

    /**
     * Funkcia volaná pri prihlásení. 
     * @param user úžívateľ
     * @param role privílegia 
     * @return boolean true ak účastník ma privilégia  
     */  
    
    @Override
    public boolean authorize(User user, String role) {
        return user.getRoles() != null && user.getRoles().contains(role);
    }
}
