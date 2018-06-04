package sk.fri.uniza.microservice;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
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
        return dataDAO.create(new Data(new String(znamka),new String(WemosHexaID)));
    }
      
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
     * @param idData 
     * @return vymazané "Data" v JSON
     */    
    @DELETE
    @RolesAllowed("ADMIN")
    @Path("/{idData}")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Data deleteData2(@PathParam("idData") LongParam idData) {
        Optional<Data> result = dataDAO.findById(idData.get());
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
     * @param idData 
     * @return DataAddEditView 
     */      
    @GET
    @Path("/edit/{idData}")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public DataAddEditView getEditForm(@PathParam("idData") LongParam idData) {
        Optional<Data> result = dataDAO.findById(idData.get());

        if (result.isPresent()) {
            return new DataAddEditView(result.get());
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }
    
    /**
     * Rest Edit
     * Povolenie všetci. 
     * Typ funkcia GET, URL http://127.0.0.1:8080/data/edit
     * @param idData 
     * @param znamka 
     * @return DataView stránka
     */      
    @POST
    @Path("/edit")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UnitOfWork
    public DataView editData(@FormParam("idData") String idData, @FormParam("znamka") String znamka) {
        Optional<Data> result = dataDAO.findById(Long.parseLong(idData));
        if (result.isPresent()) {
            result.get().setZnamka(znamka);
            return new DataView(result.get());
        } else {
            Data create = dataDAO.create(new Data(znamka,new String("0")));
            return new DataView(create);
        }
    }
    
    /**
     * Rest zobrazí data s ID
     * Povolenie všetci. 
     * Typ funkcia GET, URL http://127.0.0.1:8080/data/
     * @param idData 
     * @return DataView 
     */      
    @GET
    @Path("/{idData}")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public DataView getData(@PathParam("idData") LongParam idData) {
        Optional<Data> result = dataDAO.findById(idData.get());

        if (result.isPresent()) {
            return new DataView(result.get());
        }

        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }
    
    /**
     * Rest list v formáte JSON.
     * Povolenie všetci. 
     * Typ funkcia GET, URL http://127.0.0.1:8080/data
     * @return list Data 
     */       
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public List<Data> listDatas() {
        return dataDAO.findAll();
    }
    
    /**
     * Rest vymaže dáta z databazy podla id
     * Povolenie všetci. 
     * Typ funkcia GET, URL http://127.0.0.1:8080/data/delete/
     * @param idData 
     * @return DataListView  
     */      
    @GET
    @Path("/delete/{idData}")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public DataListView deleteData(@PathParam("idData") LongParam idData) {
        Optional<Data> result = dataDAO.findById(idData.get());
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
