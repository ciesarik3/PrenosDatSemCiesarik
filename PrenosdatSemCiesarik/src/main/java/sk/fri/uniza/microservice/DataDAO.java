package sk.fri.uniza.microservice;

import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.hibernate.SessionFactory;
/**
 * Definicie operacii v databaze Create Read Update a Delete
 * @author Roman Ciesarík
 */
public class DataDAO extends AbstractDAO<Data> {

    /**
    * Konštruktor DataDAO, komunikácia s databázou.
    * @param sessionFactory
    */
    public DataDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    /**
    * @param  dat Vymazanie dát v tejto premennej v databazi 
    */
    public void delete(Data dat){
        currentSession().delete(dat);
    }

    /**
    * Hladanie  v dátach cez ID
    * @param id  
    * @return Data ako Optional
    */
    public Optional<Data> findById(Long id) {
        if (id != null) {
            return Optional.ofNullable(get(id));
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * uloženie Dát do databázy
     * @param dat  
     * @return Data  
     */
    public Data create(Data dat) {
        return persist(dat);
    }

    /**
     * najdenie všetkých dát
     * query v triede Data
     * @return List všetkých dát
     */
    public List<Data> findAll() {
        return list(namedQuery("sk.fri.uniza.microservice.Data.findAll"));
    }

}