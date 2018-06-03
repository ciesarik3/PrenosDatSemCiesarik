package sk.fri.uniza.microservice;

import java.security.Principal;
import java.util.Set;

/**
 * Trieda prihláseného účastníka
 * @author Roman Ciesarík
 */
public class User implements Principal {
    private final String name;

    private final Set<String> roles;

    /**
     * Konštruktor inicializuje premenné "name" a "roles"=null ked nie su zadané privilégia
     * @param name 
     */
    public User(String name) {
        this.name = name;
        this.roles = null;
    }

    /**
     * Konštruktor inicializuje premenné "name" a "roles"
     * @param name 
     * @param roles privilégia
     */
    public User(String name, Set<String> roles) {
        this.name = name;
        this.roles = roles;
    }

    /**
     * Getter "name"
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Generátor ID pre účastníka
     * @return int
     */
    public int getId() {
        return (int) (Math.random() * 100);
    }

    /**
     * Getter "roles"
     * @return roles
     */
    public Set<String> getRoles() {
        return roles;
    }
}
