package sk.fri.uniza.microservice;

import io.dropwizard.views.View;
import java.util.List;
/**
 * Vytvorenie obsahu pre "dataList.ftl"
 * @author Roman Ciesarík
 */
public class DataListView extends View{

    private List<Data> data;

    /**
     * Getter "data"
     * @return data 
     */
    public List<Data> getData() {
        return data;
    }
    
    /**
     * Konštruktor innicializuje a vytvorí stránaku "dataList.ftl"
     * @param data
     */
    public DataListView(List<Data> data) {
        super("dataList.ftl");
        this.data = data;
    }
}
