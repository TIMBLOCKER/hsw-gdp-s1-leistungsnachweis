package de.hsw;

import java.util.Scanner;

public class Starter {



    public static void main(String[] args) throws Exception {
        System.out.println("Willkommen beim Lottozahlen Generator für das Spiel 6 aus 49! ");

        System.out.println(" ");

        System.out.println("Wie viele Spielfelder möchten Sie ausgefüllt haben?: ");
        Scanner scanner = new Scanner(System.in);
        int anzahl = scanner.nextInt();
        if (anzahl < 13 && anzahl > 0) {
            lottoAbfrage(anzahl);
        } else {
            System.out.println("Fehler - ungültige Spielfeld Eingabe!");
        }

    }



    public static void lottoAbfrage(int anzahl) {
        Lottozahlen lottozahlen = new Lottozahlen();
        lottozahlen.lottoscheinZahlen(anzahl);

    }

}
