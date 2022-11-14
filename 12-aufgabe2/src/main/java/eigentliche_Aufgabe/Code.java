package eigentliche_Aufgabe;

import java.util.Scanner;

public class Code {
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


