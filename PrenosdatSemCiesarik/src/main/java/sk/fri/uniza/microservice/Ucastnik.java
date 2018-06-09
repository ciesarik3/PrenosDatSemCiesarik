package sk.fri.uniza.microservice;
/**
 * Trieda na dafinovanie vytváranie a načítavanie učastníkov hodnotiaceho projektu
 * @author Roman Ciesarík
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Ucastnik")
@NamedQueries({
    @NamedQuery(
            name = "sk.fri.uniza.microservice.Ucastnik.findAll",
            query = "SELECT s from Ucastnik s"
    )
})
public class Ucastnik {

    public Ucastnik() { // Jackson deserialization 
    }
    
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "meno")
    private String meno;
    @Column(name = "heslo")
    private String heslo;
    @Column(name = "privilegia")
    private String privilegia; 
    
    /**
     * Konštruktor triedy na inicializáciu premennej "meno", "heslo" a "privilegia" 
     * @param meno Meno účastníka
     * @param heslo Heslo účastníka
     * @param privilegia Zadefinovanie oprávnení pre užívateľa
     */
    public Ucastnik(String meno,String heslo,String privilegia) {
       this.meno=meno;
       this.heslo=heslo;
       this.privilegia=privilegia;
    }
    
    /**
     * Getter "id" pre poradie účastníka
     * @return id
     */
    @JsonProperty
    public long getId() {
        return id;
    }
    
    /**
     * Setter "id" pre poradie účastníka
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }
    
    /**
     * Getter "meno" účastníka
     * @return meno
     */
    @JsonProperty
    public String getMeno() {
        return meno;
    }

    /**
     * Setter "meno" účastníka
     * @param meno Nastavenie/opravenie mena nového/existujúceho účastníka
     */
    public void setMeno(String meno) {
        this.meno = meno;
    }
    
    /**
     * Getter "heslo" účastníka
     * @return heslo
     */
    @JsonProperty
    public String getHeslo(){
        return heslo;
    }
    
    /**
     * Setter "heslo" účastníka
     * @param heslo 
     */
    public void setHeslo(String heslo){
    this.heslo=heslo;
    }  
    
    /**
     * Getter "privilegia"
     * @return privilegia
     */
    @JsonProperty
    public String getPrivilegia() {
        return privilegia;
    }
    
     /**
      * Setter "privilegia" 
      * @param privilegia 
      */
    public void setPrivilegia(String privilegia){
    this.privilegia=privilegia;
    }
     
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    @JsonProperty
//    public String getContent() {
//        return content;
//    }

//    @Override
//    public int hashCode() {
//        int hash = 5;
//        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
//        hash = 59 * hash + Objects.hashCode(this.meno);
//        hash = 59 * hash + Objects.hashCode(this.heslo);
//        return hash;
//    }
//
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
//        final Ucastnik other = (Ucastnik) obj;
//        if (this.id != other.id) {
//            return false;
//        }
//        if (!Objects.equals(this.meno, other.meno)) {
//            return false;
//        }
//        if (!Objects.equals(this.heslo, other.heslo)) {
//            return false;
//        }
//        return true;
//    }
}
