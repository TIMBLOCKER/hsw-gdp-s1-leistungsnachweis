package de.hsw;

import de.hsw.jaxbUtils.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.util.ArrayList;

@XmlRootElement(name = "kunde")
@XmlAccessorType(XmlAccessType.FIELD)
public class Kunde {

    @XmlAttribute
    String name, vorname, adresse;
    @XmlAttribute
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    LocalDate geburtsdatum;

    @XmlAttribute
    ArrayList<Konto> konten = new ArrayList<>();
    @XmlAttribute
    int tgeld = 0, giro = 0;

    public Kunde() {
        this.name = "Mustermann";
        this.vorname = "Max";
        this.adresse = "Musterstraße 1";
        this.geburtsdatum = LocalDate.of(2000, 1,1);
    }

    public Kunde(String name, String vorname, String adresse, LocalDate geburtsdatum, ArrayList<Konto> konten) {
        this.name = name;
        this.vorname = vorname;
        this.adresse = adresse;
        this.geburtsdatum = geburtsdatum;
        this.konten = konten;
        updateIndex();
    }

    public Kunde(String name, String vorname, String adresse, LocalDate geburtsdatum) {
        this.name = name;
        this.vorname = vorname;
        this.adresse = adresse;
        this.geburtsdatum = geburtsdatum;
    }

    public String getName() {
        return name;
    }

    public boolean addKonto(Konto konto) {
        updateIndex();
        if (konto instanceof Tagesgeld) {
            if (tgeld < 3) {
                konten.add(konto);
                return true;
            }
        } else if (konto instanceof Giro) {
            if (giro < 2) {
                konten.add(konto);
                return true;
            }
        }
        return false;
    }

    public void updateIndex() {
        giro = 0;
        tgeld = 0;
        for (Konto k : konten) {
            if (k instanceof Tagesgeld) {
                tgeld++;
            } else {
                giro++;
            }
        }
    }

    public Konto deleteKonto(Konto konto) {
        if (konto instanceof Tagesgeld) {
            konten.remove(konto);
            tgeld--;
            return konto;
        } else if (konto instanceof Giro) {
            konten.add(konto);
            giro--;
            return konto;
        }
        return null;
    }

    public String getVorname() {
        return vorname;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public String getAdresse() {
        return adresse;
    }

    public ArrayList<Konto> getKonten() {
        return konten;
    }

    @Override
    public String toString() {
        return "Kunde{" +
                "name='" + name + '\'' +
                ", vorname='" + vorname + '\'' +
                ", adresse='" + adresse + '\'' +
                ", geburtsdatum=" + geburtsdatum +
                ", konten=" + konten +
                ", tgeld=" + tgeld +
                ", giro=" + giro +
                '}';
    }

    public String getStringValue(){
        return name+"|"+vorname+"|"+adresse+"|"+geburtsdatum+"|"+konten;
    }
}
