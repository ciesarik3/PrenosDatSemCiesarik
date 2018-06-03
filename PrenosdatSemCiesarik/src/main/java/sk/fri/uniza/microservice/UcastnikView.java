package sk.fri.uniza.microservice;

import io.dropwizard.views.View;

/**
 * Trieda na tvorbu web stranky podla "ucastnik.ftl"
 * @author Roman Ciesarík
 */
public class UcastnikView extends View {

    private final Ucastnik ucastnik;

    /**
     * Getter "ucastnik"
     * @return ucastnik
     */
    public Ucastnik getUcastnik() {
        return ucastnik;
    }

    /**
     * Konštruktor inicializuje ucastnika a vygeneruje web stránku
     * @param ucastnik premenná "ucastnik", 
     */
    public UcastnikView(Ucastnik ucastnik) {
        super("ucastnik.ftl");
        this.ucastnik = ucastnik;
    }

    
}
