package sk.fri.uniza.microservice;

import io.dropwizard.views.View;

/**
 * Trieda vytvorí stránku "wemos.ftl"
 * @author Roman Ciesarík
 */
public class WEMOSView extends View {

    private final WEMOS wemos;

    /**
     * Getter "wemos"
     * @return wemos
     */
    public WEMOS getWEMOS() {
        return wemos;
    }

     /**
     * Konštruktor inicializuje a vygeneruje stránku
     * @param wemos 
     */
    public WEMOSView(WEMOS wemos) {
        super("WEMOS.ftl");
        this.wemos = wemos;
    }

    
}
