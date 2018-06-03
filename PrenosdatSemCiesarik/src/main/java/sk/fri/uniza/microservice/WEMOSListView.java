package sk.fri.uniza.microservice;

import io.dropwizard.views.View;
import java.util.List;
/**
 * Trieda vytvorí stránku podľa súboru "WEMOSList.ftl"
 * @author Roman Ciesarík
 */
public class WEMOSListView extends View{

    private List<WEMOS> Wemos;

    /**
     * Getter  Wemos 
     * @return Wemos
     */
    public List<WEMOS> getZariadenia() {
        return Wemos;
    }
    
    /**
     * Konštruktor inicializuje premennú Wemos 
     * generuje web stránku
     * @param Wemos 
     */
    public WEMOSListView(List<WEMOS> Wemos) {
        super("WEMOSList.ftl");
        this.Wemos = Wemos;
    }
    
    
    
}
