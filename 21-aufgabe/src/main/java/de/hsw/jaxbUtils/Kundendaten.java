package de.hsw.jaxbUtils;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.util.ArrayList;

@XmlRootElement(name = "KUNDENDATEN")
@XmlAccessorType(XmlAccessType.FIELD)
public class Kundendaten {

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

    public String getName() {
        return name;
    }



    public String getVorname() {
        return vorname;
    }



    public String getAdresse() {
        return adresse;
    }



    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }



    public ArrayList<Kontendaten> getKonten() {
        return konten;
    }


}
