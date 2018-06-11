package sk.fri.uniza.microservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
/**
 * Trieda WEMOS (wifi doska ESP8266) ukladanie do databázy.
 * @author Roman Ciesarík
 */
@Entity
@Table(name = "wemos")
@NamedQueries({
    @NamedQuery(
            name = "sk.fri.uniza.microservice.WEMOS.findAll",
            query = "SELECT s from WEMOS s"
    )
})
public class WEMOS {

    @Id
    @GeneratedValue(
        strategy = GenerationType.TABLE
    )
    private long idWemos;
     
    @Length(max = 20)
    private String WemosHexaID;//hexa ID dosky

    /**
     * Prázdny konštruktor 
     */
    public WEMOS() {        
    }

    /**
     * Konštruktor inicializuje premennú WemosHexaID(ID dosky)
     * @param WemosHexaID premenná "WemosHexaID"
     */
    public WEMOS(String WemosHexaID) {
        this.WemosHexaID = WemosHexaID;
    }

    /**
     * Konštruktor inicializuje premennú idWemos a WemosHexaID (id v databazi a ID dosky) 
     * @param WemosHexaID 
     * @param idWemos 
     */
    public WEMOS(long idWemos, String WemosHexaID) {
        this.idWemos = idWemos;
        this.WemosHexaID = WemosHexaID;
    }

   
    /**
     * Getter idWemos
     * @return id
     */
    @JsonProperty
    public long getId() {
        return idWemos;
    }

    /**
     * Setter idWemos
     * @param idWemos
     */
    public void setId(long idWemos) {
        this.idWemos = idWemos;
    }

    /**
     * Setter "WemosHexaID"
     * @param WemosHexaID 
     */
    public void setWemos(String WemosHexaID) {
        this.WemosHexaID = WemosHexaID;
    }

    /**
     * Getter "WemosHexaID"
     * @return WemosHexaID
     */
    @JsonProperty
    public String getWemos() {
        return WemosHexaID;
    }

//    /**
//     * Vytvorí hash kód
//     * @return hash
//     */
//    @Override
//    public int hashCode() {
//        int hash = 5;
//        hash = 59 * hash + (int) (this.idWemos ^ (this.idWemos >>> 32));
//        hash = 59 * hash + Objects.hashCode(this.WemosHexaID);
//        return hash;
//    }
//
//    /**
//     * Prepísanie metódy "equals" využívanej na porovnanie dvoch tried
//     * @param obj
//     * @return true, ak sú rovnaké
//     */
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final WEMOS other = (WEMOS) obj;
//        if (this.idWemos != other.idWemos) {
//            return false;
//        }
//        if (!Objects.equals(this.WemosHexaID, other.WemosHexaID)) {
//            return false;
//        }
//        return true;
//    }
}
