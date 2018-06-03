package sk.fri.uniza.microservice;

import io.dropwizard.views.View;
/**
 * Vytvori strankaú podla "data.ftl"
 * @author Roman Ciesarík
 */
public class DataView extends View {

    private final Data data;
    
    /**
    * Getter "data"
    * @return data
    */
    public Data getData() {
        return data;
    }

    /**
     * Konštruktor inicializuje a vygeneruje stránku "data.ftl"
     * @param data 
     */
    public DataView(Data data) {
        super("data.ftl");
        this.data = data;
    }

    
}