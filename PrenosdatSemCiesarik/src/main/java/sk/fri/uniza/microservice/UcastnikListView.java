package sk.fri.uniza.microservice;

import io.dropwizard.views.View;
import java.util.List;

/**
 * Trieda vytvorí stránku podľa súboru "uzivateList.ftl"
 * @author Roman Ciesarík
 */
public class UcastnikListView extends View{

    private List<Ucastnik> ucastniks;

    /**
     * Globálny getter ucastnika s 
     * @return ucastniks 
     */
    public List<Ucastnik> getSayings() {
        return ucastniks;
    }
    
    /**
     * Konštruktor inicializuje ucastnik a predáva nad seba 
     * vloženie udajov do web stránky a jej vygenerovanie "uzivatelList.ftl"
     * @param ucastniks 
     */
    public UcastnikListView(List<Ucastnik> ucastniks) {
        super("ucastnikList.ftl");
        this.ucastniks = ucastniks;
    }
    
    
    
}
