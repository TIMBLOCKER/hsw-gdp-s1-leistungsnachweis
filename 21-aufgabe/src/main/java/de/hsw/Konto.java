package de.hsw;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.math.BigInteger;
import java.util.Random;

@XmlRootElement(name = "konto")
@XmlAccessorType(XmlAccessType.FIELD)
public class Konto {

    Random random;
    @XmlAttribute
    String iban;
    @XmlAttribute
    private double saldo, maxDispo;


    /**
     * Kontruktor für diue Iban --> generate Iban, Saldo und den maximalen Wert der Überziehung
     */
    public Konto() {
        random = new Random();
        this.iban = generateIBANDE(Bank.BANKLEITZAHL);
        this.saldo = 0;
        this.maxDispo = 0;
    }

    public Konto(long seed) {
        random = new Random(seed);
        this.iban = generateIBANDE(Bank.BANKLEITZAHL);
        this.saldo = 0;
        this.maxDispo = 0;
    }


    /**
     * @param iban  Iban
     * @param saldo Wert des Kontos
     *              Konstruktor Iban und Saldo
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
     *            Methode zur Generierung der Iban
     * @return Rückgabe der Kontonummer, Bankleitzahl und Prüfziffer an assembleIban
     */
    public String generateIBANDE(String blz) {
        String ktn = String.valueOf(String.format("%1$010d", random.nextLong(0, 9999999999L + 1)));
        String pruefziffer = String.valueOf(String.format("%1$02d", generateChecksum(ktn, blz)));

        return assembleIban(ktn, blz, pruefziffer);
    }


    /**
     * @param handoverktn Kontonummer
     * @param handoverblz Bankleitzahl
     *            generiert die Checksumme
     * @return gibt den Checksum Wert zurück
     */
    public long generateChecksum(String handoverktn, String handoverblz) {
        String ktn = filterktn(handoverktn);
        String blz = filterblz(handoverblz);

        String lkz = "131400";
        String assemble = blz + ktn + lkz;

        BigInteger intVorModulo = new BigInteger(assemble);

        BigInteger modulo = new BigInteger("97");
        long div = intVorModulo.remainder(modulo).longValue();

        return 98 - div; //long longNachModulo = 98 - div;
    }

    /**
     * @param handoverktn Kontonummer
     * @param handoverblz Bankleitzahl
     * @param handoverpz  Prüfziffer
     *            fügt die Iban zusammen
     * @return Rückgabe der zusammengebauten Iban
     */
    public String assembleIban(String handoverktn, String handoverblz, String handoverpz) {
        String ktn = filterktn(handoverktn);
        String blz = filterblz(handoverblz);
        String pz = filterpz(handoverpz);

        String iban = "DE" + pz + blz + ktn;

        StringBuilder ibanBuilder = new StringBuilder(iban);
        ibanBuilder.insert(4, " ");
        ibanBuilder.insert(9, " ");
        ibanBuilder.insert(14, " ");
        ibanBuilder.insert(19, " ");
        ibanBuilder.insert(24, " ");

        return ibanBuilder.toString();
    }

    public String filterktn(String handoverktn) {
        long zahlenfilter = Long.parseLong(handoverktn);
        if (zahlenfilter >= 0) {
            if (handoverktn.length() != 10) {
                throw new IllegalArgumentException("Das ist keine gültige Kontonummer!");
            } else {
                return handoverktn;
            }
        } else {
            throw new IllegalArgumentException("Das ist keine gültige Kontonummer!");
        }
    }

    public String filterblz(String handoverblz) {
        long zahlenfilter = Long.parseLong(handoverblz);
        if (zahlenfilter >= 0) {
            if (handoverblz.length() == 8) {
                if (handoverblz.matches("[1-8][0-9]{7}")){
                    return handoverblz;
                }
                else {
                    throw new IllegalArgumentException("Das ist keine gültige Bankleitzahl!");
                }
            } else {
                throw new IllegalArgumentException("Das ist keine gültige Bankleitzahl!");
            }
        } else {
            throw new IllegalArgumentException("Das ist keine gültige Bankleitzahl!");
        }
    }

    public String filterpz(String handoverpz) {
        int zahlenfilter = Integer.parseInt(handoverpz);
        if (zahlenfilter >= 0) {
            if (handoverpz.length() != 2)
                throw new IllegalArgumentException("Das ist keine gültige Prüfziffer!");
         else {
                return handoverpz;
            }
        } else{
            throw new IllegalArgumentException("Das ist keine gültige Prüfziffer!");
        }
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
