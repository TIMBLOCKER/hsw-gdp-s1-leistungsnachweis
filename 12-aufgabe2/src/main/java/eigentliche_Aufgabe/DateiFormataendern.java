package eigentliche_Aufgabe;

import java.util.Scanner;

public class DateiFormataendern {

    public void dateiformatstarten(){

        System.out.println("Welche Datei soll umbenannt werden? Bsp (C:\\Users\\rathg\\Desktop\\lol.txt)");
        String altedatei = eingabe(); // Alten Dateinamen speichern.

        System.out.println("In welches Format soll die Datei umgewandelt werden? Bsp. (.html)");
        String neuedatei = eingabe(); // Neue Endung speichern.

        String ausgabe = dateiumbenennen(altedatei, neuedatei); // Fuktion Start; übergabe von Eingabe-Variablen.

        System.out.println(ausgabe); // Ausgabe Dateipfad mit neuer Endung.
    }
    public String eingabe(){
        Scanner scanner = new Scanner(System.in); // Konsoleneingabe
        String eingabe = scanner.next();
        return eingabe;
    }
    public String zerschnippel(String s) {
        return s.substring(0, s.lastIndexOf('.')); // Rückgabe, des bis zum letzten Punkt abgeschnittenen Strings
    }
    public String dateiumbenennen(String altedatei, String neuedatei){
        String s = zerschnippel(altedatei); // Rückgabe in String s speichern
        s += neuedatei; // Neue Endung an Abgeschnittenen String hängen
        return s;
    }
}
