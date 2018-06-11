package sk.fri.uniza.microservice;

import io.dropwizard.views.View;
import java.util.List;
/**
 * Trieda vytvorí stránku podľa súboru "WEMOSList.ftl"
 * @author Roman Ciesarík
 */
public class WEMOSListView extends View{

    private List<WEMOS> wemos;

    /**
     * Getter  Wemos 
     * @return Wemos
     */
    public List<WEMOS> getWemos() {
        return wemos;
    }
    
    /**
     * Konštruktor inicializuje premennú Wemos 
     * generuje web stránku
     * @param wemos 
     */
    public WEMOSListView(List<WEMOS> wemos) {
        super("WEMOSList.ftl");
        this.wemos = wemos;
    }
    
    
    
}
