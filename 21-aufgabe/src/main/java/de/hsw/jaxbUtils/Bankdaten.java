package de.hsw.jaxbUtils;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;

@XmlRootElement (name = "BANKDATEN")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bankdaten {

    @XmlAttribute
    String name, adresse;
    @XmlAttribute
    String blz;

    @XmlElement (name = "KUNDE")
    ArrayList<Kundendaten> kunden = new ArrayList<Kundendaten>();

    public Bankdaten(String name, String adresse, String blz, ArrayList<Kundendaten> kunden) {
        this.name = name;
        this.adresse = adresse;
        this.blz = blz;
        this.kunden = kunden;
    }

    public Bankdaten() {
    }

    /**
     * @return Rückgabe des Namens
     */
    public String getName() {
        return name;
    }

    //TODO: 0 Usage??
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Rückgabe der Adresse
     */
    public String getAdresse() {
        return adresse;
    }

    //TODO: 0 Usage?
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * @return Rückgabe der Bankleitzahl
     */
    public String getBlz() {
        return blz;
    }
    //TODO: 0 Usage
    public void setBlz(String blz) {
        this.blz = blz;
    }

    /**
     * @return Rückgabe der Kundendaten
     */
    public ArrayList<Kundendaten> getKunden() {
        return kunden;
    }
    //Todo: 0 Usage
    public void setKunden(ArrayList<Kundendaten> kunden) {
        this.kunden = kunden;
    }
}
