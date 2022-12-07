package de.hsw;



import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Bank {

    private static final String NAME = "HSW Bank";
    private static final String ADRESSE = "Am Stockhof 2";
    public static final String BANKLEITZAHL = "10000000";

    @XmlAttribute
    String name, adresse;
    @XmlAttribute
    String blz;


    HashMap<String, Konto> konten = new HashMap<String, Konto>();

    @XmlAttribute
    ArrayList<Kunde> kunden = new ArrayList<Kunde>();


    public Bank(){
        this.name = NAME;
        this.adresse = ADRESSE;
        this.blz = BANKLEITZAHL;
    }

    public Bank(String name, String adresse, String blz) {
        this.name = name;
        this.adresse = adresse;
        this.blz = blz;
    }

    public Kunde addKunde(String name, String vorname, String adresse, LocalDate geburtstag) {
        Kunde kunde = new Kunde(name, vorname, adresse, geburtstag);
        kunden.add(kunde);
        return kunde;
    }

    public boolean deleteKunde(Kunde kunde) {
        return kunden.remove(kunde);
    }

    public Kunde deleteKundeAtPosition(int position) {
        return kunden.remove(position);
    }

    public Konto addKonto(Konto konto) {
        String iban = konto.getIban();
        return konten.putIfAbsent(iban, konto);
    }

    public Konto deleteKontofromIBAN(String iban) {
        return konten.remove(iban);
    }

    public Kunde getKundefromIBAN(String iban) {
        for (Kunde kunde : kunden) {
            for (Konto konto : kunde.getKonten()) {
                if (konto.getIban().equals(iban))
                    return kunde;
            }
        }
        return null;
    }

    public Konto getKontofromIBAN(String iban) {
        return konten.get(iban);
    }

    public boolean deleteKonto(Konto konto) {
        String iban = konto.getIban();
        return konten.remove(iban, konto);
    }

    public boolean assignKonto(Kunde kunde, String iban) {
        int kundeIndex = kunden.indexOf(kunde);
        Kunde kunde1 = kunden.get(kundeIndex);
        Konto konto = konten.get(iban);
        return kunde1.addKonto(konto);
    }


    public Konto unAssignKonto(Kunde kunde, String iban) {
        int kundeIndex = kunden.indexOf(kunde);
        Kunde kunde1 = kunden.get(kundeIndex);
        Konto konto = konten.get(iban);
        return kunde1.deleteKonto(konto);
    }


    public boolean transferMoney(String from, String to, double amount) throws NullPointerException{
        Konto konto1, konto2;
        try {
             konto1 = konten.get(from);
             konto2 = konten.get(to);
        }catch (NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }


        double saldo1 = konto1.getSaldo();
        double saldo2 = konto2.getSaldo();

        if ((saldo1 - amount) > konto1.getMaxDispo()) {
            konto2.setSaldo(saldo2 + amount);
            konto1.setSaldo(saldo1 - amount);
            return true;
        }
        return false;
    }


    public boolean addMoney(String iban, double amount) {
        Konto konto1 = konten.get(iban);
        double saldo1 = konto1.getSaldo();

        saldo1 += amount;

        return false;
    }


    public boolean outputMoney(String iban, double amount) {
        Konto konto = konten.get(iban);
        double saldo = konto.getSaldo();

        if (konto instanceof Giro && amount < ((Giro) konto).getMaxAuszahlung()) {
            konto.setSaldo(saldo - amount);
            return true;
        }

        return false;
    }

    public ArrayList<Kunde> getKunden() {
        return kunden;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", adresse='" + adresse + '\'' +
                ", blz='" + blz + '\'' +
                ", konten=" + konten.entrySet().toString() +
                ", kunden=" + kunden.toString() +
                '}';
    }


}
