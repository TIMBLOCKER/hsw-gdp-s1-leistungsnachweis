package de.hsw.jaxbUtils;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.util.ArrayList;

@XmlRootElement(name = "KUNDENDATEN")
@XmlAccessorType(XmlAccessType.FIELD)
public class Kundendaten extends Kontendaten {

    @XmlAttribute
    int kundennummer;

    @XmlAttribute
    String name, vorname, adresse;
    @XmlAttribute
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    LocalDate geburtsdatum;

    @XmlElement (name = "KONTEN")
    ArrayList<Kontendaten> konten = new ArrayList<>();

    public Kundendaten(int kundennummer, String name, String vorname, String adresse, LocalDate geburtsdatum, ArrayList<Kontendaten> konten) {
        this.kundennummer = kundennummer;
        this.name = name;
        this.vorname = vorname;
        this.adresse = adresse;
        this.geburtsdatum = geburtsdatum;
        this.konten = konten;
    }

    public Kundendaten() {
    }

    public int getKundennummer() {
        return kundennummer;
    }

    public void setKundennummer(int kundennummer) {
        this.kundennummer = kundennummer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public ArrayList<Kontendaten> getKonten() {
        return konten;
    }

    public void setKonten(ArrayList<Kontendaten> konten) {
        this.konten = konten;
    }
}
