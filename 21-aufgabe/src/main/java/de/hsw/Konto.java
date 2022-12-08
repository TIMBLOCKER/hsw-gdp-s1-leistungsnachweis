package de.hsw;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.glassfish.jaxb.core.v2.TODO;

import java.math.BigInteger;
import java.util.Random;

@XmlRootElement(name = "konto")
@XmlAccessorType(XmlAccessType.FIELD)
public class Konto {

    @XmlAttribute
    String iban;
    @XmlAttribute
    private double saldo, maxDispo;


    /**
     * Kontruktor für diue Iban --> generate Iban, Saldo und den maximalen Wert der Überziehung
     */
    public Konto(){
        this.iban = generateIBANDE(Bank.BANKLEITZAHL);
        this.saldo = 0;
        this.maxDispo = 0;
    }
 //TODO: 0 Usage??? Was macht es?
    public Konto(String blz) {
        this.iban = generateIBANDE(blz);
        this.saldo = 0;
        this.maxDispo = 0;
    }

    /**
     * @param iban Iban
     * @param saldo Wert des Kontos
     * Konstruktor Iban und Saldo
     */
    public Konto(String iban, double saldo) {
        this.iban = iban;
        this.saldo = saldo;
    }

    public String getIban() {
        return iban;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * @param blz Bankleitzahl
     * Methode zur Generierung der Iban
     * @return Rückgabe der Kontonummer, Bankleitzahl und Prüfziffer an assembleIban
     */
    public String generateIBANDE(String blz) {
        Random random = new Random();
        String ktn = String.valueOf(String.format("%1$010d",random.nextLong(0, 9999999999L)));
        String pruefziffer = String.valueOf(String.format("%1$02d",generateChecksum(ktn, blz)));

        return assembleIban(ktn, blz, pruefziffer);
    }

    /**
     * @param ktn Kontonummer
     * @param blz Bankleitzahl
     * generiert die Checksumme
     * @return gibt den Checksum Wert zurück
     */
    public long generateChecksum(String ktn, String blz) {

        String lkz = "131400";
        String assemble = blz + ktn + lkz;

        BigInteger intVorModulo = new BigInteger(assemble);

        BigInteger modulo = new BigInteger("97");
        long div = intVorModulo.remainder(modulo).longValue();

        return 98 - div; //long longNachModulo = 98 - div;
    }

    /**
     * @param ktn Kontonummer
     * @param blz Bankleitzahl
     * @param pz Prüfziffer
     * fügt die Iban zusammen
     * @return Rückgabe der zusammengebauten Iban
     */
    public String assembleIban(String ktn, String blz, String pz) {
        String iban = "DE" + pz + blz + ktn;

        StringBuilder ibanBuilder = new StringBuilder(iban);
        ibanBuilder.insert(4, " ");
        ibanBuilder.insert(9, " ");
        ibanBuilder.insert(14, " ");
        ibanBuilder.insert(19, " ");
        ibanBuilder.insert(24, " ");

        return ibanBuilder.toString();
    }

    /**
     * @return Rückgabe des maximalen Überziehung-Wertes
     */
    public double getMaxDispo() {
        return maxDispo;
    }

    /**
     * @return Rückgabe des Kontotypen
     */
    public String getType() {
        return "Konto";
    }

    /**
     * @return Rückgabe der String Werte der Strings Iban, Saldo und maxDispo
     */
    @Override
    public String toString() {
        return "Konto{" +
                "iban='" + iban + '\'' +
                ", saldo=" + saldo +
                ", maxDispo=" + maxDispo +
                '}';
    }
}
