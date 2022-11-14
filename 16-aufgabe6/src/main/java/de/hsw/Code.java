package de.hsw;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Code {

    public static int eingabefilter(String ausgabe){
        Scanner scanner = new Scanner(System.in);
        System.out.println(ausgabe);
        while(true){
            try {
                return scanner.nextInt();
            } catch(InputMismatchException e){ //Falls man keine Ganzzahl eingegeben hat wird der Fehler hier abgefangen
                System.out.println(scanner.next() + " besteht nicht ausschließlich aus Zahlen! Wiederholen Sie die Eingabe:");
            }
        }
    }

    public long pruefziffer(String kontonummer,String bankleitzahl, String laenderkennziffer){
        String zsmfuegung = bankleitzahl + kontonummer + laenderkennziffer;

        BigInteger vormodulo = new BigInteger(zsmfuegung);

        BigInteger faktor = new BigInteger("97");
        long div = vormodulo.remainder(faktor).longValue();
        long nachmodulo = 98 - div;

        return nachmodulo;
    }

    public String ibanzsmfuegen(String bankleitzahl, String kontonummer, String pruefziffer){
        String iban = "DE" + pruefziffer + bankleitzahl + kontonummer;
        String ibanfertig = iban.subSequence(0, 4) + " " + iban.subSequence(4, 8) + " " + iban.substring(8, 12) + " " + iban.substring(12, 16) + " " + iban.substring(16, 20) + " " + iban.substring(20, 22);
        return ibanfertig;
    }

}
