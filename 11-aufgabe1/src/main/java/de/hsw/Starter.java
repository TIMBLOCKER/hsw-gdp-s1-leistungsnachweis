package de.hsw;

import com.sun.source.tree.Tree;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

//In dieser Klasse wird die Main Methode gestartet
public class Starter {

    //TODO: huynh Exception handling
    //Todo camelCase

    //In der Main Methode gibt der User die Spielrunden Anzahl ein
    public static void main(String[] args) throws Exception {
        //Eingabe der Anzahl der Spielrunde
        System.out.println("\n" +
                " ██╗░░░░░░█████╗░████████╗████████╗░█████╗ ░██████╗░░░██╗██╗\n" +
                " ██║░░░░░██╔══██╗╚══██╔══╝╚══██╔══╝██╔══██╗ ╚════██╗░██╔╝██║\n" +
                " ██║░░░░░██║░░██║░░░██║░░░░░░██║░░░██║░░██║ ░░███╔═╝██╔╝░██║\n" +
                " ██║░░░░░██║░░██║░░░██║░░░░░░██║░░░██║░░██║ ██╔══╝░░███████║\n" +
                " ███████╗╚█████╔╝░░░██║░░░░░░██║░░░╚█████╔╝ ███████╗╚════██║\n" +
                " ╚══════╝░╚════╝░░░░╚═╝░░░░░░╚═╝░░░░╚════╝░ ╚══════╝░░░░░╚═╝");


        System.out.println("Willkommen beim Lottozahlen Generator für das Spiel 6 aus 49! \uD83C\uDF40 \n");
        System.out.println("Wie viele Spielfelder möchten Sie ausgefüllt haben?: ");
        Scanner scanner = new Scanner(System.in);
        Lottozahlen lottozahlen = new Lottozahlen();
        int anzahl = scanner.nextInt();
        Set<Integer> set = new TreeSet<>();
        if (anzahl < 13 && anzahl > 0) {
            lottozahlen.lottoscheinZahlen(set, anzahl);
            System.out.println("");
            lottozahlen.superZahl();
        } else {
            System.out.println("Fehler - ungültige Spielfeld Eingabe!");
            // Überprüfung ob Spielrundenanzahl > 12 mittels if, da nicht mehr als 12 möglich ist
        }
    }

}

