package de.hsw;

import java.time.LocalDate;
import java.util.ArrayList;

public class Kunde {

    String name, vorname, adresse;
    LocalDate geburtsdatum;

    ArrayList<Konto> konten = new ArrayList<>();
    int tgeld = 0, giro = 0;

    public Kunde() {
        this.name = "Mustermann";
        this.vorname = "Max";
        this.adresse = "Musterstraße 1";
        this.geburtsdatum = LocalDate.of(2000, 1,1);
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
}
