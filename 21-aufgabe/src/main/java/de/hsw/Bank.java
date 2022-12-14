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
public class Bank  {

    private static final String NAME = "HSW Bank";
    private static final String ADRESSE = "Am Stockhof 2";
    public static final String BANKLEITZAHL = "10000000";

    @XmlAttribute
    String name, adresse;
    @XmlAttribute
    String blz;

    @XmlAttribute
    HashMap<String, Konto> konten = new HashMap<>();

    @XmlAttribute
    ArrayList<Kunde> kunden = new ArrayList<>();


    public String getName() {
        return name;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getBlz() {
        return blz;
    }

    /**
     * Konstruktor für den Namen, Adresse und Bankleitzahl der Bank --> HSW Bank...
     */
    public Bank(){
        this.name = NAME;
        this.adresse = ADRESSE;
        this.blz = BANKLEITZAHL;
    }


    /**
     * @param name Name der Bank
     * @param adresse Adresse der Bank
     * @param blz Bankleitzahl
     * @param konten Konten
     * @param kunden Kunden
     * Konstruktor für Namen, Adresse, Bankleitzahl, Konten und Kunden der Bank
     */
    public Bank(String name, String adresse, String blz, HashMap<String, Konto> konten, ArrayList<Kunde> kunden) {
        this.name = name;
        this.adresse = adresse;
        this.blz = blz;
        this.konten = konten;
        this.kunden = kunden;
    }

    /**
     * @param name Nachname
     * @param vorname Vorname
     * @param adresse Adresse
     * @param geburtstag Geburtstag
     * fügt einen Kunden hinzu mit add
     * @return gibt den Kunden zurück
     */
    public Kunde addKunde(String name, String vorname, String adresse, LocalDate geburtstag) {
        Kunde kunde = new Kunde(name, vorname, adresse, geburtstag);
        kunden.add(kunde);
        return kunde;
    }


    public Kunde deleteKundeAtPosition(int position) {
        return kunden.remove(position);
    }

    public void addKonto(Konto konto) {
        String iban = konto.getIban();
        konten.putIfAbsent(iban, konto);
    }

    /**
     * @param iban Iban
     * get Kunden von der Iban
     * @return Rückgabe des Kundens oder eines Nullwertes
     */
    public Kunde getKundefromIBAN(String iban) {
        for (Kunde kunde : kunden) {
            for (Konto konto : kunde.getKonten()) {
                if (konto.getIban().equals(iban))
                    return kunde;
            }
        }
        return null;
    }

    /**
     * @param iban Iban
     * @return Rückgabe der Iban eines Kontos mit GET
     */
    public Konto getKontofromIBAN(String iban) {
        return konten.get(iban);
    }

    /**
     * @param konto Konto
     * Methode um das Konto zu löschen
     * @return Rückgabe der Iban zur Löschung des Kontos
     */
    public void deleteKonto(Konto konto) {
        String iban = konto.getIban();
        konten.remove(iban, konto);
    }

    /**
     * @param kunde Kunde
     * @param iban Iban
     * @return Rückgabe des Kontos um das Konto zu erstellen
     */
    public boolean assignKonto(Kunde kunde, String iban) {
        int kundeIndex = kunden.indexOf(kunde);
        Kunde kunde1 = kunden.get(kundeIndex);
        Konto konto = konten.get(iban);
        return kunde1.addKonto(konto);
    }


    /**
     * @param kunde Kunde
     * @param iban Iban
     * Methode zum aufruf ein Konto zu löschen
     * @return gibt den Wert für die Kontolöschung zurück
     */
    public Konto unAssignKonto(Kunde kunde, String iban) {
        int kundeIndex = kunden.indexOf(kunde);
        Kunde kunde1 = kunden.get(kundeIndex);
        Konto konto = konten.get(iban);
        return kunde1.deleteKonto(konto);
    }


    /**
     * @param from Versender des Geldes
     * @param to Empfänger des Geldes
     * @param amount Betrag des transferierten Geldes
     * Methode um Geld von Konten zu transferieren
     * @return Rückgabe boolean false
     * @throws NullPointerException Wenn eins der oder beide Konten nicht gefunden werden konnten wird die Exception geworfen
     */
    public boolean transferMoney(String from, String to, double amount) throws IndexOutOfBoundsException{
        if (!from.equals(to)) {
            Konto konto1, konto2;
            try {
                konto1 = konten.get(from);
                konto2 = konten.get(to);
            } catch (IndexOutOfBoundsException e) { //Exception Handling --> IndexOutOfBounds Exception wird abgefangen
                throw new IndexOutOfBoundsException(e.getMessage());
            }

            if (konto1 != null && konto2 != null) {

                double saldo1 = konto1.getSaldo();
                double saldo2 = konto2.getSaldo();

                if ((saldo1 - amount) > konto1.getMaxDispo()) {
                    konto2.setSaldo(saldo2 + amount);
                    konto1.setSaldo(saldo1 - amount);
                    return true;
                }else{
                    System.out.println("Fehler: Das Saldo war nicht ausreichend!");
                }
                return false;
            }else{
                System.out.println("Fehler: Mindestens ein Konto konnte nicht gefunden werden!");
            }
            return false;
        }
        System.out.println("Fehler: Debitor und Creditor dürfen nicht gleich sein!");
        return false;
    }


    /**
     * @param iban Iban
     * @param amount Betrag der hinzugefügt werden soll
     * Methode, um Geld einzuzahlen
     * @return Rückgabe boolean False Wert
     */
    public boolean addMoney(String iban, double amount) {
        Konto konto1 = konten.get(iban);
        if (konto1 != null) {
            double saldo1 = konto1.getSaldo();
            saldo1 += amount;
            konto1.setSaldo(saldo1);
            konten.replace(iban, konto1);
            return true;
        }
       return false;
    }


    /**
     * @param iban Iban Nummer
     * @param amount Anzahl des abgehobenen Betrages
     * Methode zur auszahlung von einem Geldbetrag
     * @return boolean False Wert
     */
    public boolean outputMoney(String iban, double amount) {
       try{
           Konto konto = konten.get(iban);
           double saldo = konto.getSaldo();

           if (konto instanceof Giro){
               Giro giro = (Giro) konto;
               if ( amount <= giro.getMaxAuszahlung() && (saldo-amount) > giro.getMaxDispo()){
                   konto.setSaldo(saldo - amount);
                   return true;
               }
               return false;
           }
       }catch (NullPointerException e){
           return false;
       }
        return false;
    }

    /**
     * @return Rückgabe der Kunden in einer ArrayListe
     */
    public ArrayList<Kunde> getKunden() {
        return kunden;
    }

    public int getKontenSize() {
        return konten.size();
    }

    /**
     * @return Rückgabe vom Namen, Adresse, Bankleitzahl
     */
    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", adresse='" + adresse + '\'' +
                ", blz='" + blz + '\'' +
                ", konten=" + konten.entrySet() +
                ", kunden=" + kunden.toString() +
                '}';
    }


}
