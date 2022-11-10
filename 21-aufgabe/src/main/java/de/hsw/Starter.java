package de.hsw;

import java.math.BigInteger;
import java.util.Random;

public class Starter {
    public static void main(String[] args) throws Exception {
     //   System.out.println(generateIBANDE());
    }

    /*

    public static String generateIBANDE(){
        String iban = "DE";
        Random random = new Random();
        int blz = random.nextInt(10000000, 99999999);
        long ktn = random.nextLong(0, 9999999999L);

        String zahl = String.format("%08d", blz)+ String.format("%010d", ktn) + "131400";

        BigInteger ibanzahl = new BigInteger(zahl);
        BigInteger modulozahl = new BigInteger(97);
        long modulo = ibanzahl.remainder(97).longValue();
        long pruefziffer = 98 - modulo;

        iban += pruefziffer + ibanzahl + "";

        return iban;
    }
*/

}
