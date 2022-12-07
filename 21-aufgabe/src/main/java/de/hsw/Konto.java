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

    @XmlAttribute
    String iban;
    @XmlAttribute
    private double saldo, maxDispo;


    public Konto(){
        this.iban = generateIBANDE(Bank.BANKLEITZAHL);
        this.saldo = 0;
        this.maxDispo = 0;
    }

    public Konto(String blz) {
        this.iban = generateIBANDE(blz);
        this.saldo = 0;
        this.maxDispo = 0;
    }

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

    public String generateIBANDE(String blz) {
        Random random = new Random();
        String ktn = String.valueOf(String.format("%1$010d",random.nextLong(0, 9999999999L)));
        String pruefziffer = String.valueOf(String.format("%1$02d",generateChecksum(ktn, blz)));

        return assembleIban(ktn, blz, pruefziffer);
    }

    public long generateChecksum(String ktn, String blz) {

        String lkz = "131400";
        String assemble = blz + ktn + lkz;

        BigInteger intVorModulo = new BigInteger(assemble);

        BigInteger modulo = new BigInteger("97");
        long div = intVorModulo.remainder(modulo).longValue();

        return 98 - div; //long longNachModulo = 98 - div;
    }

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

    public double getMaxDispo() {
        return maxDispo;
    }

    public String getType() {
        return "Konto";
    }

    @Override
    public String toString() {
        return "Konto{" +
                "iban='" + iban + '\'' +
                ", saldo=" + saldo +
                ", maxDispo=" + maxDispo +
                '}';
    }
}
