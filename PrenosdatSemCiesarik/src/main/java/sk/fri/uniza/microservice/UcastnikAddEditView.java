package sk.fri.uniza.microservice;

import io.dropwizard.views.View;

/**
 * Trieda vytvára obsah a stránku v prehliadači podľa HTML kodu v súbore "ucastnikAddEdit.ftl"
 * @author Roman Ciesarík
 */
public class UcastnikAddEditView extends View {

    private final Ucastnik ucastnik;

    /**
     * Getter "Ucastnik"
     * @return ucastnik
     */
    public Ucastnik getUcastnik() {
        return ucastnik;
    }

    /**
     * Konštruktor inicializuje premennú ucastnik a predáva rodičovi 
     * vloženie udajov do web stránky a jej vygenerovanie "uzivatelAddEdit.ftl"
     * @param ucastnik 
     */
    public UcastnikAddEditView(Ucastnik ucastnik) {
        super("ucastnikAddEdit.ftl");
        this.ucastnik = ucastnik;
    }

    

}
