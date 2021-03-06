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
    * @param  data Vymazanie dát v tejto premennej v databazi 
    */
    public void delete(Data data){
        currentSession().delete(data);
    }

    /**
    * Hladanie  v dátach cez ID
    * @param idData  
    * @return Data ako Optional
    */
    public Optional<Data> findById(Long idData) {
        if (idData != null) {
            return Optional.ofNullable(get(idData));
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * uloženie Dát do databázy
     * @param data  
     * @return Data  
     */
    public Data create(Data data) {
        return persist(data);
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