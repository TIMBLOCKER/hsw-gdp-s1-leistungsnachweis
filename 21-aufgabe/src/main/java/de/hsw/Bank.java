package de.hsw;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@XmlRootElement(name = "bank")
@XmlType(propOrder = {"name", "adresse", "blz", "kunden", "konten"})
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

    public Kunde deleteKundeAtPosition(int position){
        return kunden.remove(position);
    }

    public Konto addKonto(Konto konto){
        String iban = konto.getIban();
        return konten.putIfAbsent(iban , konto);
    }

    public Konto deleteKontofromIBAN(String iban){
        return konten.remove(iban);
    }

    public boolean deleteKonto(Konto konto){
        String iban = konto.getIban();
      return konten.remove(iban, konto);
    }

    public boolean assignKonto(Kunde kunde, String iban){
        int kundeIndex = kunden.indexOf(kunde);
        Kunde kunde1 = kunden.get(kundeIndex);
        Konto konto = konten.get(iban);
        return kunde1.addKonto(konto);
    }

    public boolean unAssignKonto(Kunde kunde, String iban){
        int kundeIndex = kunden.indexOf(kunde);
        Kunde kunde1 = kunden.get(kundeIndex);
        Konto konto = konten.get(iban);
        return kunde1.deleteKonto(konto);
    }

    public boolean transferMoney(String from, String to, double amount){
        Konto konto1 = konten.get(from);
        Konto konto2 = konten.get(to);

        double saldo1 = konto1.getSaldo();
        double saldo2 = konto2.getSaldo();

        if ((saldo1-amount) > konto1.getMaxDispo()){
            konto2.setSaldo(saldo2+amount);
            konto1.setSaldo(saldo1-amount);
            return true;
        }
        return false;
    }


    public boolean addMoney(String iban, double amount){
        Konto konto1 = konten.get(iban);
        double saldo1 = konto1.getSaldo();

        saldo1 += amount;

        return false;
    }


    public boolean outputMoney(String iban, double amount){
        Konto konto = konten.get(iban);
        double saldo = konto.getSaldo();

        if (konto instanceof Giro && amount < ((Giro) konto).getMaxAuszahlung()) {
            konto.setSaldo(saldo-amount);
            return true;
        }

        return false;
    }

    public ArrayList<Kunde> getKunden() {
        return kunden;
    }
}
