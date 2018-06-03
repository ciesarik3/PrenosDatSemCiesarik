package sk.fri.uniza.microservice;

import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.hibernate.SessionFactory;

/**
 * Definície operácii Create Read Update a Delete objektu WEMOS v databáze.
 * @author Roman Ciesarík
 */
public class WEMOSDAO extends AbstractDAO<WEMOS> {

    /**
     * Konštruktor SessionFactory 
     * @param sessionFactory 
     */
    public WEMOSDAO(SessionFactory sessionFactory) {
        super(sessionFactory);  
    }
    
    /**
     * Vymazanie z databázy
     * @param wemos 
     */
    public void delete(WEMOS wemos){
            currentSession().delete(wemos);
    }

    /**
     * Vyhladavanie podla id idWemos
     * @param idWemos 
     * @return WEMOS ako Optional
     */
    public Optional<WEMOS> findById(Long idWemos) {
        if (idWemos != null) {
            return Optional.ofNullable(get(idWemos));
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * Uloženie do databázy
     * @param wemos objekt, ktorý má byť uložený
     * @return WEMOS
     */
    public WEMOS create(WEMOS wemos) {
        return persist(wemos);
    }

    /**
     * Vyhľadá všetky objekty typu WEMOS v databáze
     * Query je umiestnené v triede WEMOS
     * @return vráti list všetkých dosiek
     */
    public List<WEMOS> findAll() {
        return list(namedQuery("sk.fri.uniza.microservice.WEMOS.findAll"));
    }

}
