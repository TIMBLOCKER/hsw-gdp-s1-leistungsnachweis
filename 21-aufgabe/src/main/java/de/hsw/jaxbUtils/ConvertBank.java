package de.hsw.jaxbUtils;

import de.hsw.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ConvertBank  {

    public ConvertBank() {
    }

    /**
     * @param bank Bank
     * Bank wird in Bankdaten umgewandelt für XML
     * @return Rückgabe der Bankdaten
     */
    public  Bankdaten bankToBankdaten(Bank bank){
        ArrayList<Kundendaten> kundendatenArrayList = new ArrayList<>();
        int i = 0;
        for (Kunde k: bank.getKunden()) {
            ArrayList<Kontendaten> kontendatenArrayList = new ArrayList<>();
            for (Konto konto:k.getKonten()) {
                if (konto instanceof Tagesgeld){
                    kontendatenArrayList.add(new Kontendaten(konto.getIban(), konto.getSaldo(), konto.getMaxDispo(), "TG"));
                }else{
                    kontendatenArrayList.add(new Kontendaten(konto.getIban(), konto.getSaldo(), konto.getMaxDispo(), "GI"));
                }
            }
            Kundendaten kundendaten = new Kundendaten(i, k.getName(), k.getVorname(), k.getAdresse(), k.getGeburtsdatum(), kontendatenArrayList);
            kundendatenArrayList.add(kundendaten);
            i++;
        }
       return new Bankdaten(bank.getName(), bank.getAdresse(), bank.getBlz(), kundendatenArrayList);

    }

    /**
     * @param bankdaten Daten der Bank
     * Bankdaten wird zur Bank umgewandelt für die XML
     * @return Rückgabe der Bankdaten (Name, Adresse,Blz, Konten, Kunden)
     */
    public  Bank bankdatenToBank(Bankdaten bankdaten){
        HashMap<String, Konto> konten = new HashMap<>(); //HashMap ist zur speicherung der Daten in einer Datentabelle
        ArrayList<Kunde> kunden = new ArrayList<>();
        ArrayList<Kundendaten> kundendatenArrayList = bankdaten.getKundenDaten();
        for (Kundendaten k: kundendatenArrayList) {
            ArrayList<Kontendaten> kontendatenArrayList = k.getKonten();
            ArrayList<Konto> kontList = new ArrayList<>();
            for (Kontendaten kd: kontendatenArrayList) {
                if (kd.getType().equals("TG")){
                    Tagesgeld tg = new Tagesgeld(kd.getIban(), kd.getSaldo());
                    konten.put(kd.getIban(), tg);
                    kontList.add(tg);
                }else{
                    Giro gi = new Giro(kd.getIban(), kd.getSaldo());
                    konten.put(kd.getIban(), gi);
                    kontList.add(gi);
                }
            }
            Kunde kunde = new Kunde(k.getName(), k.getVorname(), k.getAdresse(), k.getGeburtsdatum(), kontList);
            kunden.add(kunde);
        }
        return new Bank(bankdaten.getName(), bankdaten.getAdresse(), bankdaten.getBlz(), konten, kunden);
    }

}
