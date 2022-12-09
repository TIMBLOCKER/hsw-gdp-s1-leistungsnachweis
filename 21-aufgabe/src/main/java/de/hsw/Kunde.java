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

    //TODO Was ist das??? 0 Usage
    public Kunde() {
        this.name = "Mustermann";
        this.vorname = "Max";
        this.adresse = "Musterstraße 1";
        this.geburtsdatum = LocalDate.of(2000, 1,1);
    }

    /**
     * @param name Vorname des Kunden
     * @param vorname Nachname des Kunden
     * @param adresse Adresse des Kunden
     * @param geburtsdatum Geburtsdatum des Kunden
     * @param konten Konten des Kunden
     * Konstruktor für Nachnamen, Vornamen, Adresse, Geburtsdatum, Konten in einer ArrayList
     */
    public Kunde(String name, String vorname, String adresse, LocalDate geburtsdatum, ArrayList<Konto> konten) {
        this.name = name;
        this.vorname = vorname;
        this.adresse = adresse;
        this.geburtsdatum = geburtsdatum;
        this.konten = konten;
        updateIndex();
    }

    /**
     * @param name Nachname des Kunden
     * @param vorname Vorname des Kunden
     * @param adresse Adresse
     * @param geburtsdatum
     * Methode zur Notation der Kunden attribute
     * Konstruktor für Nachnamen, Vornamen, Adresse, Geburtsdatum,
     */
    public Kunde(String name, String vorname, String adresse, LocalDate geburtsdatum) {
        this.name = name;
        this.vorname = vorname;
        this.adresse = adresse;
        this.geburtsdatum = geburtsdatum;
    }

    /**
     * @return Rückgabe des Nachnamens
     */
    public String getName() {
        return name;
    }

    /**
     * @param konto Konto
     * Methode zur Überprüfung beim Hinzufügen eines Kontos, ob der Kunde mehr als 3 Tagesgeldkonten und mehr als 2 Girokonten hat
     * @return Rückgabe Boolean False Wert
     */
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
        System.out.println("Fehler: Es dürfen nur 2 Girokonten und 3 Tagesgeldkonten pro Kunde vergeben werden!");
        return false;
    }

    /**
     * Methode zum Hinzufügen der Anzahl der Konten
     */
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

    /**
     * @param konto Konto
     * Methode zum Löschen eines Kontos (Anzahl)
     * @return Rückgabe eines NULL-Wertes
     */
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

    /**
     * @return Rückgabe des Vornamens mit get
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * @return Rückgabe des Geburtstages mit get
     */
    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    /**
     * @return Rückgabe der Adresse mit get
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @return  Rückgabe der Konten mit get in einer ArrayList
     */
    public ArrayList<Konto> getKonten() {
        return konten;
    }

    /**
     * @return Rückgabe der Strings: Nachnamen, Vornamen, Adresse, Geburtsdatums, Konten, Tagesgeld und Giro
     */
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

    /**
     * @return Rückgabe der String Werte der String: Name, Vorname, Adresse, Geburtstagsdatum, Konten
     */
    public String getStringValue(){
        return name+"|"+vorname+"|"+adresse+"|"+geburtsdatum+"|"+konten;
    }
}
