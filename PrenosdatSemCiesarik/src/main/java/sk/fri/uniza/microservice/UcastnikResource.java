package sk.fri.uniza.microservice;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import io.dropwizard.views.View;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Trieda
 * Definícia rest rozhrania
 * @author Roman Ciesarík
 */
@Path("/ucastnik")
//@RolesAllowed("BASIC_USER")
public class UcastnikResource {

    private final UcastnikDAO ucastnikDAO;

    /**
     * Konštruktor inicializuje "ucastnikDAO"
     * @param ucastnikDAO 
     */
    public UcastnikResource(UcastnikDAO ucastnikDAO) {
        this.ucastnikDAO = ucastnikDAO;
    }

    /**
     * Funkcia rest rozhrania. Uloží ucastnika do databázy, návratový formát JSON 
     * Typ funkcia GET, URL http://127.0.0.1:8080/ucastnik/add
     * @param meno   účastníka 
     * @param heslo   učastníka
     * @param privilegia nového účastníka
     * @return objekt Ucastnik je vo formáte JSON
     */
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Ucastnik createUcastnik(@DefaultValue("none") @QueryParam("meno") String meno,@DefaultValue("none") @QueryParam("heslo") String heslo,@DefaultValue("guest") @QueryParam("privilegia") String privilegia) {
        try{
        if(!privilegia.equals("admin")&&!privilegia.equals("user")){privilegia="guest";}
        }catch(Exception e){privilegia="guest";}
        
        return ucastnikDAO.create(new Ucastnik(meno,heslo,privilegia));
    }    
    
    /**
     * Rest rozhranie vytvorí stránku s zadaným ID objektu ucastnik načítaného z databázy 
     * Povolenie majú BASIC_USER a ADMIN k tejto funkcii 
     * Typ funkcia GET, URL http://127.0.0.1:8080/ucastnik/{id}
     * @param id užívateľa v databáze 
     * @return stránka "ucastnik.ftl"
     */    
    @GET
    @Path("/{id}")
    @RolesAllowed("BASIC_USER")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public UcastnikView getUcastnik(@PathParam("id") LongParam id) {
        Optional<Ucastnik> result = ucastnikDAO.findById(id.get());

        if (result.isPresent()) {
            return new UcastnikView(result.get());
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * Rest rozhranie pre zmenu udajov účastníka v databáze
     * Povolenie má len ADMIN. 
     * Typ funkcia GET, URL http://127.0.0.1:8080/ucastnik/edit/{id}
     * @param id ucastnika v databáze 
     * @return stránka "ucastnikAddEdit.ftl"
     */
    @GET
    @Path("/edit/{id}")
    @RolesAllowed("ADMIN")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public UcastnikAddEditView getEditForm(@PathParam("id") LongParam id) {
        Optional<Ucastnik> result = ucastnikDAO.findById(id.get());

        if (result.isPresent()) {
            return new UcastnikAddEditView(result.get());
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * Rest rozhranie pre zobrazenie informácii pridaného účastníka v databáze
     * Povolenie všetci. 
     * Typ funkcia GET, URL http://127.0.0.1:8080/ucastnik/add
     * @return stránka"ucastnikAddEdit.ftl"
     */
    @GET
    @Path("/add")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public View getAddForm() {
        return new View("ucastnikAddEdit.ftl", StandardCharsets.UTF_8) {
        };
    }

     /**
     * Rest rozhranie pre zobrazenie a zmenenie parametrov účastníka v databáze
     * Povolenie všetci. 
     * Typ funkcia GET, URL http://127.0.0.1:8080/ucastnik/edit
     * @param _id id ucastnika
     * @param meno ucastnika
     * @param heslo ucastnika 
     * @param privilegia ucastnika 
     * @return stránka "ucastnik.ftl"
     */
    @POST
    @Path("/edit")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UnitOfWork
    public UcastnikView editUcastnik(@FormParam("id") String _id, @FormParam("meno") String meno,@FormParam("heslo") String heslo,@FormParam("privilegia") String privilegia /* Ucastnik ucastnik*/) {
        Optional<Ucastnik> result = ucastnikDAO.findById(Long.parseLong(_id));
        try{
        if(!privilegia.equals("admin")&&!privilegia.equals("user")){privilegia="guest";}
        }catch(Exception e){privilegia="guest";}
        if (result.isPresent()) {
            result.get().setHeslo(heslo);
            result.get().setMeno(meno);
            result.get().setPrivilegia(privilegia);
            return new UcastnikView(result.get());
        } else {
            Ucastnik create = ucastnikDAO.create(new Ucastnik(meno,heslo,privilegia));
            return new UcastnikView(create);
        }
    }
    
    /**
     * Rest rozhranie pre uloženie účastníka do databázy
     * Povolenie všetci. 
     * Typ funkcia GET, URL http://127.0.0.1:8080/ucastnik
     * @param ucastnik uložený do databázy
     * @return objekt vo formate JSON
     */    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Ucastnik createUcastnik(Ucastnik ucastnik) {
        try{
        if(!ucastnik.getPrivilegia().equals("admin")&&!ucastnik.getPrivilegia().equals("user")){ucastnik.setPrivilegia("guest");}
        }catch(Exception e){ucastnik.setPrivilegia("guest");}
        return ucastnikDAO.create(ucastnik);
    }

    /**
     * Rest rozhranie pre vymazanie ucčastnika z databázy
     * Povolenie admin
     * Typ funkcia GET, URL http://127.0.0.1:8080/ucastnik/delete/{id}
     * @param id ucastnika
     * @return stránka "ucastnikList.ftl"
     */
    @GET
    @Path("/delete/{id}")
    @RolesAllowed("ADMIN")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public UcastnikListView deleteUcastnik(@PathParam("id") LongParam id) {
        Optional<Ucastnik> result = ucastnikDAO.findById(id.get());
        if (result.isPresent()) {
            ucastnikDAO.delete(result.get());
            return new UcastnikListView(ucastnikDAO.findAll());
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }
    
    /**
     * Rest rozhranie pre vymazanie ucčastnika z databázy
     * Povolenie admin
     * Typ funkcia GET, URL http://127.0.0.1:8080/ucastnik/{id}
     * @param id ucastnika
     * @return stránka "ucastnikList.ftl"
     */
    @DELETE
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Ucastnik deleteUcastnik2(@PathParam("id") LongParam id) {
        Optional<Ucastnik> result = ucastnikDAO.findById(id.get());
        if (result.isPresent()) {
            ucastnikDAO.delete(result.get());
            return result.get();
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }
    
    /**
     * Rest rozhranie zobrazenie všetkých účastníkov z databázy 
     * Povolenie BASIC_USER a ADMIN. 
     * Typ funkcia GET, URL http://127.0.0.1:8080/ucastnik/list
     * @return stránka "ucastnikList.ftl"
     */
    @GET
    @Path("/list")
    @RolesAllowed("BASIC_USER")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public UcastnikListView getUcastnik() {
        return new UcastnikListView(ucastnikDAO.findAll());
    }

    /**
     * Rest rozhranie vráti list všetkých ucastnikov, format udajov JSON
     * Povolenie BASIC_USER a ADMIN. 
     * Typ funkcia GET, URL http://127.0.0.1:8080/ucastnik
     * @return list ucastnikov vo formáte JSON
     */
    @GET
    @RolesAllowed("BASIC_USER")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public List<Ucastnik> listUcastnik() {
        return ucastnikDAO.findAll();
    }
}
