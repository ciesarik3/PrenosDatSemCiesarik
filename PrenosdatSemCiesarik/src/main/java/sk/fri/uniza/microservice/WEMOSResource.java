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
@Path("/wemos")
public class WEMOSResource {

    private final WEMOSDAO wemosDAO;

    /**
     * Konštruktor inicializuje "wemosDAO"
     * @param wemosDAO
     */
    public WEMOSResource(WEMOSDAO wemosDAO) {
        this.wemosDAO = wemosDAO;
    }

    /**
     * Rest rozhranie objektu WEMOS.
     * @param idWemos 
     * @return stránka "wemos.ftl"
     */
    @GET
    @Path("/{idWemos}")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    @RolesAllowed("BASIC_USER")
    public WEMOSView getWEMOS(@PathParam("idWemos") LongParam idWemos) {
        Optional<WEMOS> result = wemosDAO.findById(idWemos.get());

        if (result.isPresent()) {
            return new WEMOSView(result.get());
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }
    
    /**
     * Rest rozhranie objektu WEMOS vo formáte JSON.
     * @param idWemos 
     * @return objekt WEMOS vo formáte JSON
     */
    @POST
    @Path("/{idWemos}")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public WEMOS getWEMOSOne(@PathParam("idWemos") LongParam idWemos) {
        Optional<WEMOS> result = wemosDAO.findById(idWemos.get());

        if (result.isPresent()) {
            return result.get();
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }
    
    /**
     * Rest rozhranie pre zmenu udajov daného Wemos-u
     * @param idWemos 
     * @return stránka "wemosAddEdit.ftl"
     */
    @GET
    @Path("/edit/{idWemos}")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    @RolesAllowed("BASIC_USER")
    public WEOMSAddEditView getEditForm(@PathParam("idWemos") LongParam idWemos) {
        Optional<WEMOS> result = wemosDAO.findById(idWemos.get());

        if (result.isPresent()) {
            return new WEOMSAddEditView(result.get());
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * Rest rozhranie pre pridanie Wemos-u
     * @return stránka "wemosAddEdit.ftl"
     */
    @GET
    @Path("/add")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    @RolesAllowed("BASIC_USER")
    public View getAddForm() {
        return new View("WEMOSAddEdit.ftl", StandardCharsets.UTF_8) {
        };
    }

    /**
     * Rest rozhranie. Pridá WEMOS do databázy.
     * @param WemosHexaID 
     * @return wemos v JSON
     */
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public WEMOS postAddForm(@DefaultValue("none") @QueryParam("WemosHexaID") String WemosHexaID) {
        return wemosDAO.create(new WEMOS(WemosHexaID));
    }
    
    /**
     * Rest rozhranie na zmenu parametrov WEMOS-u.
     * @param idWemos id Wemos v databáze, ktorého parametre majú byť zmenené
     * @param WemosHexaID nový typ Wemos
     * @return stránku v prehliadači definovaanú dokumentom "wemosAddEdit.ftl"
     */
    @POST
    @Path("/edit")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UnitOfWork
    @RolesAllowed("BASIC_USER")
    public WEMOSView editWEMOS(@FormParam("idWemos") String idWemos, @FormParam("WemosHexaID") String WemosHexaID ) {
        Optional<WEMOS> result = wemosDAO.findById(Long.parseLong(idWemos));
        if (result.isPresent()) {
            result.get().setWemos(WemosHexaID);
            return new WEMOSView(result.get());
        } else {
            WEMOS create = wemosDAO.create(new WEMOS(WemosHexaID));
            return new WEMOSView(create);
        }
    }

    /**
     * Rest rozhranie vytvorenie objektu a uloží do databázy.
     * Povolenie BASIC_USER a ADMIN. 
     * @param wemos 
     * @return WEMOS v JSON
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed("BASIC_USER")
    public WEMOS createWEMOS(WEMOS wemos) {
        return wemosDAO.create(wemos);
    }

    /**
     * Rest rozhranie vymazanie z databázy daný idWemos.
     * Povolenie ADMIN. 
      *@param idWemos Wemos, ktoré má byť vymazané.
     * @return stránka zoznamu zvyšných dosiek
     */
    @GET
    @Path("/delete/{idWemos}")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    @RolesAllowed("ADMIN")
    public WEMOSListView deleteWEMOS(@PathParam("idWemos") LongParam idWemos) {
        Optional<WEMOS> result = wemosDAO.findById(idWemos.get());
        if (result.isPresent()) {
            wemosDAO.delete(result.get());
            return new WEMOSListView(wemosDAO.findAll());
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }
    
     /**
     * Rest rozhranie, vymaže WEMOS s idWemos.
     * Povolenie ADMIN. 
      *@param idWemos
     * @return objek WEMOS v JSON
     */
    @DELETE
    @RolesAllowed("ADMIN")
    @Path("/{idWemos}")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public WEMOS deleteWEMOS2(@PathParam("idWemos") LongParam idWemos) {
        Optional<WEMOS> result = wemosDAO.findById(idWemos.get());
        if (result.isPresent()) {
            wemosDAO.delete(result.get());
            return result.get();
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * Rest rozhranie lsit všetkých Wemos dosiek
     * Povolenie BASIC_USER a ADMIN. 
     * @return stránka "wemosList.ftl".
     */
    @GET
    @Path("/list")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    @RolesAllowed("BASIC_USER")
    public WEMOSListView getWEMOS() {
        return new WEMOSListView(wemosDAO.findAll());

    }

    /**
     * Rest rozhranie vráti list všetky dosky Wemos v JSON.
     * PovolenieBASIC_USER a ADMIN. 
     * @return list WEMOS v JSON
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed("BASIC_USER")
    public List<WEMOS> listWEMOS() {
        return wemosDAO.findAll();
    }
}
