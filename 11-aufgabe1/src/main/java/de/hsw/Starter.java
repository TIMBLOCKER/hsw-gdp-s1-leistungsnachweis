package de.hsw;

//In dieser Klasse wird die Main Methode gestartet
public class Starter {


    //In der Main Methode gibt der User die Spielrunden Anzahl ein
    public static void main(String[] args) {
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
        Lottozahlen lottozahlen = new Lottozahlen();
        int anzahl = lottozahlen.eingabe();
        lottozahlen.lottoscheinZahlen(anzahl);
        lottozahlen.superZahl();
    }
}
