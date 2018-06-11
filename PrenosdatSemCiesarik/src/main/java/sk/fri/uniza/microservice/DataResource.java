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
 * Definícia Rest rozhrania
 * @author Roman Ciesarík
 */
@Path("/data")
public class DataResource {

    private final DataDAO dataDAO;

    /**
     * Konštruktor DataResource. Inicializuje "dataDAO"
     * @param dataDAO
     */
    public DataResource(DataDAO dataDAO) {
        this.dataDAO = dataDAO;
    }
    
    /**
     * Rest uloží Data do databázy.
     * Povolenie všetci. 
     * Typ funkcia GET, URL http://127.0.0.1:8080/data/add
     * @param WemosHexaID id dosky
     * @param znamka 
     * @return "Data" v JSON
     */
    @GET
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Data getAddForm(@DefaultValue("0") @QueryParam("WemosHexaID") String WemosHexaID,@DefaultValue("0") @QueryParam("znamka") String znamka) {
        return dataDAO.create(new Data(znamka, WemosHexaID));
    }
//    @GET
//    @Path("/add")
//    @Produces(MediaType.TEXT_HTML)
//    @UnitOfWork
//    //@RolesAllowed("BASIC_USER")
//    public View getAddForm() {
//        return new View("dataAddEdit.ftl", StandardCharsets.UTF_8) {
//        };
//    }
      
//    /**
//     * Rest rozhranie. Pridá WEMOS do databázy.
//     * @param wemosHexaID 
//     * @return wemos v JSON
//     */
//    @POST
//    @Path("/add")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @UnitOfWork
//    public Data postAddForm(@DefaultValue("0") @FormParam("WemosHexaID") String WemosHexaID,@DefaultValue("0") @FormParam("znamka") String znamka) {
//        return dataDAO.create(new Data(znamka, WemosHexaID));
//    }
    
        
    /**
     * Rest zobrazí všetky dáta zdatabázy
     * Povolenie všetci. 
     * Typ funkcia GET, URL http://127.0.0.1:8080/data/list
     * @return DataListView 
     */    
    @GET
    @Path("/list")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public DataListView getData() {
        return new DataListView(dataDAO.findAll());

    }
    
    /**
     * Rest na vymazanie dát z databázy.
     * Povolenie ADMIN. 
     * Typ funkcia GET, URL http://127.0.0.1:8080/data/
     * @param id 
     * @return vymazané "Data" v JSON
     */    
    @DELETE
    //@RolesAllowed("ADMIN")
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Data deleteData2(@PathParam("id") LongParam id) {
        Optional<Data> result = dataDAO.findById(id.get());
        if (result.isPresent()) {
            dataDAO.delete(result.get());
            return result.get();
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }
    
    /**
     * Rest edit pomovou id
     * Povolenie všetci. 
     * Typ funkcia GET, URL http://127.0.0.1:8080/data/edit/
     * @param id 
     * @return DataAddEditView 
     */      
    @GET
    @Path("/edit/{id}")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public DataAddEditView getEditForm(@PathParam("id") LongParam id) {
        Optional<Data> result = dataDAO.findById(id.get());

        if (result.isPresent()) {
            return new DataAddEditView(result.get());
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }
    
    /**
     * Rest Edit
     * Povolenie všetci. 
     * Typ funkcia GET, URL http://127.0.0.1:8080/data/edit
     * @param id 
     * @param znamka 
     * @return DataView stránka
     */      
    @POST
    @Path("/edit")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UnitOfWork
    public DataView editData(@FormParam("id") String id, @FormParam("znamka") String znamka, @FormParam("wemosHexaID") String wemosHexaID) {
        Optional<Data> result = dataDAO.findById(Long.parseLong(id));
        if (result.isPresent()) {
            result.get().setZnamka(znamka);
            result.get().getWemosHexaID(wemosHexaID);
            return new DataView(result.get());
        } else {
            Data create = dataDAO.create(new Data(znamka, "E"));
            return new DataView(create);
        }
    }
    
    /**
     * Rest zobrazí data s ID
     * Povolenie všetci. 
     * Typ funkcia GET, URL http://127.0.0.1:8080/data/
     * @param id 
     * @return DataView 
     */      
    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public DataView getData(@PathParam("id") LongParam id) {
        Optional<Data> result = dataDAO.findById(id.get());

        if (result.isPresent()) {
            return new DataView(result.get());
        }

        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }
    
//    /**
//     * Rest rozhranie objektu Data vo formáte JSON.
//     * @param id 
//     * @return objekt Data vo formáte JSON
//     */
//    @POST
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @UnitOfWork
//     public Data getDataOne(@PathParam("id") LongParam id) {
//        Optional<Data> result = dataDAO.findById(id.get());
//
//        if (result.isPresent()) {
//            return result.get();
//        }
//        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
//    }
    
    /**
     * Rest list v formáte JSON.
     * Povolenie všetci. 
     * Typ funkcia GET, URL http://127.0.0.1:8080/data
     * @return list Data 
     */       
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public List<Data> listData() {
        return dataDAO.findAll();
    }
    
    /**
     * Rest vymaže dáta z databazy podla id
     * Povolenie všetci. 
     * Typ funkcia GET, URL http://127.0.0.1:8080/data/delete/
     * @param id 
     * @return DataListView  
     */      
    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public DataListView deleteData(@PathParam("id") LongParam id) {
        Optional<Data> result = dataDAO.findById(id.get());
        if (result.isPresent()) {
            dataDAO.delete(result.get());
            return new DataListView(dataDAO.findAll());
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }
    
    /**
     * Rest uloženie do databázy v JSON
     * Povolenie všetci. 
     * Typ funkcia GET, URL http://127.0.0.1:8080/data
     * @param data
     * @return Data v JSON
     */      
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Data createData(Data data) {
        return dataDAO.create(data);
    }
}
