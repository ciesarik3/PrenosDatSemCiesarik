package sk.fri.uniza.microservice;

import io.dropwizard.views.View;
import java.util.List;
/**
 * Vytvorenie obsahu pre "dataList.ftl"
 * @author Roman Ciesarík
 */
public class DataListView extends View{

    private List<Data> datas;

    /**
     * Getter "datas"
     * @return datas 
     */
    public List<Data> getDatas() {
        return datas;
    }
    
    /**
     * Konštruktor innicializuje a vytvorí stránaku "dataList.ftl"
     * @param datas 
     */
    public DataListView(List<Data> datas) {
        super("dataList.ftl");
        this.datas = datas;
    }
}
