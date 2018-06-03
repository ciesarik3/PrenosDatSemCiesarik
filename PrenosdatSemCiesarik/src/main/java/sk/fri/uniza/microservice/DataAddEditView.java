package sk.fri.uniza.microservice;

import io.dropwizard.views.View;
/**
 * vytvori stranku "dataAddEdit.ftl" 
 * @author Roman Ciesarík
 */
public class DataAddEditView extends View {

    private final Data data;

    /**
    * getter "data"
    * @return data
    */
    public Data getData() {
        return data;
    }

    
    /**
    * Konštruktor DataAddEditView.
    * @param data
    */
    public DataAddEditView(Data data) {
        super("dataAddEdit.ftl");
        this.data = data;
    }

    

}
