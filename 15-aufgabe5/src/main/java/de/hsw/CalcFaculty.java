package de.hsw;

import java.math.BigInteger;
import java.util.Scanner;

public class CalcFaculty {

    //todo Sprechende Variablennamen

    /**
     * Startermethode
     */
    public void facultyStarter(){
        try {
            System.out.println("Von welcher Zahl möchtest du die Fakultät rechnen?");
            long eingabe = eingabe();
            System.out.println();
            BigInteger fak = facultyCalc(eingabe);
            System.out.println("Die Fakultät deiner Zahl ist: " + fak);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param eingabe Die Methode nimmt eine Zahl als Eingabe
     * @return und gibt die Fakultät der Zahl zurück
     */
    public BigInteger facultyCalc(long eingabe){
        if (eingabe == 0){
            return BigInteger.valueOf(1);
        }else {
            BigInteger zahl = BigInteger.valueOf(1);
            for (long i = 1; i <= eingabe; i++) {
                zahl = zahl.multiply(BigInteger.valueOf(i));
            }
            return zahl;
        }
    }

    /**
     * @return Methode für die Systemeingabe
     */
    public int eingabe(){
        Scanner scanner = new Scanner(System.in);
        int s = filterInput(scanner.next());
        return s;
    }

    /**
     * @param eingabe Diese Methode nimmt eine Stringeingabe
     * @return und gibt diese zurück, wenn keine fehlerhaften Eingaben sind.
     * @throws IllegalArgumentException Dies Execption wird nur dann geworfen wenn fehlerhafte Eingaben sind.
     */
    public int filterInput(String eingabe)throws IllegalArgumentException{
        if (eingabe.matches("^(?i:[.,]+)$")){

            throw new IllegalArgumentException("Deine Eingabe ist keine ganze Zahl!");

        }else if (Integer.parseInt(eingabe) < 0) {

            throw new IllegalArgumentException("Deine Eingabe ist Negativ!");

        }else if (Integer.parseInt(eingabe) >= 0){

            return Integer.parseInt(eingabe);

        }throw new IllegalArgumentException("Deine Eingabe ist keine Zahl!");
    }
}
