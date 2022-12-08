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
    ArrayList<Kundendaten> kunden = new ArrayList<>();

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



    /**
     * @return Rückgabe der Adresse
     */
    public String getAdresse() {
        return adresse;
    }



    /**
     * @return Rückgabe der Bankleitzahl
     */
    public String getBlz() {
        return blz;
    }

    /**
     * @return Rückgabe der Kundendaten
     */
    public ArrayList<Kundendaten> getKundenDaten() {
        return kunden;
    }

}
