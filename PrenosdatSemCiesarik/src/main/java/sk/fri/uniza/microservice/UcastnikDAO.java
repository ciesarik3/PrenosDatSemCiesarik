package sk.fri.uniza.microservice;

import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.hibernate.SessionFactory;

/**
 * Táto trieda definuje Create Read Update a Delete funkcie objektu Ucastnik pre prácu v databáze
 * @author Roman Ciesarík
 */
public class UcastnikDAO extends AbstractDAO<Ucastnik> {

    /**
     * Konštruktor rodičovi predá objekt typu SessionFactory. 
     * definícia základných CRUD operácii pre objekt Ucastnik v databáze.
     * @param sessionFactory objekt predaný rodičovi.
     */
    public UcastnikDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    /**
     * Funkcia delete,vymaže objekt Ucastnik z databázy
     * @param ucastnik 
     */
    public void delete(Ucastnik ucastnik){
        currentSession().delete(ucastnik);
    }

    /**
     * Funkcia na vyhladanie ucastnika podla ID, vráti ho vo formáte Optional 
     * @param id ucastnika
     * @return Optional ucastnik
     */
    public Optional<Ucastnik> findById(Long id) {
        if (id != null) {
            return Optional.ofNullable(get(id));
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * Uloží Ucastnika do databázy
     * @param Ucastnik ktorý bude uložený
     * @return ucastnik
     */
    public Ucastnik create(Ucastnik ucastnik) {
        return persist(ucastnik);
    }

    /**
     * Vyhladanie všetkých účastníkov v databázi zoradených v liste 
     * Query pre komunikáciu s databázou je zadané v triede Ucastnik
     * @return List objektov typu ucastnik 
     */
    public List<Ucastnik> findAll() {
        return list(namedQuery("sk.fri.uniza.microservice.Ucastnik.findAll"));
    }

}
