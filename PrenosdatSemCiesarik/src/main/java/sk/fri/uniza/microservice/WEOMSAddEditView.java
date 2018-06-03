package sk.fri.uniza.microservice;

import io.dropwizard.views.View;

/**
 * Vytvorenie stránky "WEMOSAddEdit.ftl"
 * @author Roman Ciesarík
 */
public class WEOMSAddEditView extends View {

    private final WEMOS wemos;

    /**
     * Getter "wemos"
     * @return wemos
     */
    public WEMOS getWEMOS() {
        return wemos;
    }

    /**
     * Konštruktor inicializuje a vygenerovanie stránku
     * @param wemos premenná "wemos", 
     */
    public WEOMSAddEditView(WEMOS wemos) {
        super("WEMOSAddEdit.ftl");
        this.wemos = wemos;
    }

    

}
