package de.hsw;

import java.math.BigInteger;
import java.security.CodeSigner;
import java.util.Random;



public class Starter {
    public static void main(String[] args) throws Exception {
     //   System.out.println(generateIBANDE());
        Konto konto = new Konto();
        konto.generateIBANDE();
    }



    public static String generateIBANDE(){
        String iban = "DE";
        Random random = new Random();
        int blz = random.nextInt(10000000, 99999999);
        long ktn = random.nextLong(0, 9999999999L);



        return iban;
    }


}
