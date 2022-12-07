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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getBlz() {
        return blz;
    }

    public void setBlz(String blz) {
        this.blz = blz;
    }

    public ArrayList<Kundendaten> getKunden() {
        return kunden;
    }

    public void setKunden(ArrayList<Kundendaten> kunden) {
        this.kunden = kunden;
    }
}
