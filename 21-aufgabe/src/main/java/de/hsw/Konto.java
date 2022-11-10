package de.hsw;

import java.math.BigInteger;
import java.util.Random;

public class Konto {

    String iban;
    double saldo;

    public Konto(String iban, double saldo) {
        this.iban = iban;
        this.saldo = saldo;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String generateIBANDE(){
        String iban = "DE";
        Random random = new Random();
        int blz = random.nextInt(10000000, 99999999);
        int ktn = random.nextInt(0, 999999999);

        String zahl = blz + ktn + "1314";

        long  ibanzahl = Long.parseLong(zahl);

        long modulo = ibanzahl % 97;
        System.out.println(modulo);
        return "";
    }

}
