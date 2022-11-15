package de.hsw;

import java.time.LocalDate;
import java.util.ArrayList;

public class Kunde {

    String name, vorname, adresse;
    LocalDate geburtsdatum;
    int bonitaet; //nach Creditreform Bonitätsindex
    ArrayList<Konto> konten = new ArrayList<Konto>();

    public Kunde(String name, String vorname, String adresse, LocalDate geburtsdatum, int bonitaet, ArrayList<Konto> konten) {
        this.name = name;
        this.vorname = vorname;
        this.adresse = adresse;
        this.geburtsdatum = geburtsdatum;
        this.bonitaet = bonitaet;
        this.konten = konten;
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

    public int getBonitaet() {
        return bonitaet;
    }

    public void setBonitaet(int bonitaet) {
        this.bonitaet = bonitaet;
    }

    public ArrayList<Konto> getKonten() {
        return konten;
    }

    public void setKonten(ArrayList<Konto> konten) {
        this.konten = konten;
    }


}
