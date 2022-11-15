package de.hsw;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Bank {

    String name, adresse;
    int blz;
    HashMap<String, Konto> konten = new HashMap<String, Konto>();
    ArrayList<Kunde> kunden = new ArrayList<Kunde>();


    public Bank(String name, String adresse, int blz) {
        this.name = name;
        this.adresse = adresse;
        this.blz = blz;
    }

    public Kunde addKunde(String name, String vorname, String adresse, LocalDate geburtstag){
        Kunde kunde = new Kunde(name, vorname, adresse, geburtstag);
        kunden.add(kunde);
        return kunde;
    }

    public boolean deleteKunde(Kunde kunde){
        return kunden.remove(kunde);
    }


}
