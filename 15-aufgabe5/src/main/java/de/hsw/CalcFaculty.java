package de.hsw;

import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CalcFaculty {

    //todo Sprechende Variablennamen! Done!

    /**
     * Startermethode
     */
    public void facultyStarter(){
        try {
            System.out.println("Von welcher Zahl möchtest du die Fakultät rechnen?");
            long input = promiseStringFromConsole();
            System.out.println();
            String faculty = facultyCalc(input);
            System.out.println("Die Fakultät deiner Zahl ist: " + faculty);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handling der Konsoleneingabe und Eingabefilter
     * @return String der in der Konsole eingegeben wurde.
     */
    public long promiseStringFromConsole() {
        Scanner mainScanner = new Scanner(System.in);
        while (true){
            try {
                System.out.println("↓");
                long input = mainScanner.nextLong();
                if (input>=0){
                    return input;
                }
                System.out.println("\033[30m\033[41mFehler: Bitte nur positive Zahlen eingeben!\033[0m");
            } catch (InputMismatchException e) {
                System.out.println("\033[30m\033[41mFehler: Bitte eine korrekte Zahl eingeben!\033[0m");
                mainScanner.nextLine();
            }
        }
    }

    /**
     * @param handover Die Methode nimmt eine Zahl als Eingabe.
     * @return Die Methode gibt für die Ausnahme 0 die Fakultät 1 als String zurück und gibt die Fakultät der Zahl als String zurück.
     * @throws IllegalArgumentException wenn die übergebene Zahl negativ ist.
     */
    public String facultyCalc(long handover) throws IllegalArgumentException{
        if (handover == 0){
            return "1";
        } else if (handover > 0) {
            BigInteger calculationinteger = BigInteger.valueOf(1);
            for (long i = 1; i <= handover; i++) {
                calculationinteger = calculationinteger.multiply(BigInteger.valueOf(i));
            }
            return calculationinteger.toString();
        }else {
            throw new IllegalArgumentException("\033[30m\033[41mFehler:Invalide Eingabe!\033[0m");
        }
    }
}
