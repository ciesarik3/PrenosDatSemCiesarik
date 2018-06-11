package sk.fri.uniza.microservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
/**
 * Definuje dáta
 * @author Roman Ciesarík
 */
@Entity
@Table(name = "Data")
@NamedQueries({
    @NamedQuery(
            name = "sk.fri.uniza.microservice.Data.findAll",
            query = "SELECT s from Data s"
    )
})
public class Data {

    /**
    * Konštruktor triedy Data. inicializuje premennu datumZapisu.
    */
    public Data() {
        Calendar cal=Calendar.getInstance();
        datumZapisu = "Y:"+cal.get(Calendar.YEAR)+" M:"+(cal.get(Calendar.MONTH)+1)+" D:"+cal.get(Calendar.DAY_OF_MONTH);
    }
    
    /**
    * Konštruktor Data. inicializuje premenne
    * @param  znamka  
    * @param  WemosHexaID 
    */
    public Data(String znamka,String WemosHexaID) {
        this.znamka = znamka;
        Calendar cal=Calendar.getInstance();
        datumZapisu = "Y:"+cal.get(Calendar.YEAR)+" M:"+(cal.get(Calendar.MONTH)+1)+" D:"+cal.get(Calendar.DAY_OF_MONTH);
        this.WemosHexaID=WemosHexaID;
    }
    

    
    @Id
    @GeneratedValue( strategy = GenerationType.TABLE )
    private Long idData; 

    @Column(name = "znamka")
    private String znamka;

    @Column(name = "WemosHexaID")
    private String WemosHexaID;//id zariadenia
    
    @Column(name = "datumZapisu")
    String datumZapisu;

//    Data(String znamka) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
   /**
   * getter "idData"
   * @return idData 
   */
    @JsonProperty
    public Long getId() {
        return idData;
    }
  
   /**
   * setter "idData"
   * @param  idData
   */    
    public void setId(long idData){
        this.idData=idData;
    }
 
   /**
   * getter "znamka"
   * @return znamka 
   */
    @JsonProperty
    public String getZnamka() {
        return znamka;
    }
    
   /**
   * setter "znamka"
   * @param  znamka  
   */  
    public void setZnamka(String znamka) {
        this.znamka = znamka;
    }
    
   /**
   * setter "WemosHexaID"
   * @param WemosHexaID
   */
    public void setWemosHexaID(String WemosHexaID){
    this.WemosHexaID=WemosHexaID;
    }
    
   /**
   * getter "WemosHexaID"
   * @return WemosHexaID
   */
    @JsonProperty
    public String getWemosHexaID(){
    return WemosHexaID;
    }

   /**
   * getter "datumZapisu"
   * @return datumZapisu 
   */    
    @JsonProperty
    public String getDatumZapisu() {
        return datumZapisu;
    }

   /**
   * setter "datumZapisu"
   * @param datumZapisu
   */    
    public void setDatumZapisu(String datumZapisu) {
        this.datumZapisu = datumZapisu;
    }

    String getWemosHexaID(String wemosHexaID) {
       return WemosHexaID;
    }
    
}

