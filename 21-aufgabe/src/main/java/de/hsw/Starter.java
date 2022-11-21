package de.hsw;

import java.math.BigInteger;
import java.security.CodeSigner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;


public class Starter {

    public static final String NAME = "HSW Bank";
    public static final String ADRESSE = "Am Stockhof 2";
    public static final int BANKLEITZAHL= 10000000;

    public static boolean end = false;


    public static void main(String[] args) {

        Bank bank = new Bank(NAME, ADRESSE, BANKLEITZAHL);

        System.out.println("Wilkommen bei der");
        System.out.println("\n" +
                " /$$   /$$  /$$$$$$  /$$      /$$       /$$$$$$$                      /$$      \n" +
                "| $$  | $$ /$$__  $$| $$  /$ | $$      | $$__  $$                    | $$      \n" +
                "| $$  | $$| $$  \\__/| $$ /$$$| $$      | $$  \\ $$  /$$$$$$  /$$$$$$$ | $$   /$$\n" +
                "| $$$$$$$$|  $$$$$$ | $$/$$ $$ $$      | $$$$$$$  |____  $$| $$__  $$| $$  /$$/\n" +
                "| $$__  $$ \\____  $$| $$$$_  $$$$      | $$__  $$  /$$$$$$$| $$  \\ $$| $$$$$$/ \n" +
                "| $$  | $$ /$$  \\ $$| $$$/ \\  $$$      | $$  \\ $$ /$$__  $$| $$  | $$| $$_  $$ \n" +
                "| $$  | $$|  $$$$$$/| $$/   \\  $$      | $$$$$$$/|  $$$$$$$| $$  | $$| $$ \\  $$\n" +
                "|__/  |__/ \\______/ |__/     \\__/      |_______/  \\_______/|__/  |__/|__/  \\__/\n");

       while(!end){

           System.out.println("--- Menü: Was möchten Sie tun? ---\n");
           System.out.println("[1]. Kunden hinzufügen");
           System.out.println("[2]. Konto eröffnen");
           System.out.println("[3]. Kunden löschen");
           System.out.println("[4]. Konto auflösen");
           System.out.println("[5]. Geld einzahlen");
           System.out.println("[6]. Geld auszahlen");
           System.out.println("[7]. Überweisung tätigen");

           switch (promiseIntFromConsole()){
               case 1:
                   break;
               case 2:
                   break;
               case 3:
                   break;
               case 4:
                   break;
               case 5:
                   break;
               case 6:
                   break;
               case 7:

               default:
                   System.out.println("\033[3mFehler: Bitte eine Zahl aus dem Menü eingeben!\033[0m");
           }

        end = true;

       }



    }



        public static void addKundeToBank(){
            System.out.println("Bitte die Kundendaten eingeben:");
            System.out.println("Vorname:");
            String vorname = promiseStringFromConsole();
            System.out.println("Nachname:");
            String nachname = promiseStringFromConsole();
            System.out.println("Adresse:");
            String adresse = promiseStringFromConsole();
            System.out.println("Geburtsdatum:");
            String geburtsdatum = promiseStringFromConsole();
        }

    public static int promiseIntFromConsole(){
        Scanner mainScanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("=>");
                return mainScanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\033[3mFehler: Bitte eine Zahl eingeben!\033[0m");
            }
        }
    }

    public static String promiseStringFromConsole(){
        Scanner mainScanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("=>");
                return mainScanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\033[3mFehler: Bitte einen String eingeben!\033[0m");
            }
        }
    }

    public static LocalDate promiseLocalDateFromConsole(){
        Scanner mainScanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("[Format: dd.mm.yyyy]=>");
                String eingabe = mainScanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.GERMANY);

            } catch (InputMismatchException e) {
                System.out.println("\033[3mFehler: Bitte einen String eingeben!\033[0m");
            }
        }
    }

}
