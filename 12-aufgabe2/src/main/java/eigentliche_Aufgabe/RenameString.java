package eigentliche_Aufgabe;

import java.util.Scanner;

public class RenameString {

    /**
     * Startermethode
     */
    public void renameStringStarter(){
        System.out.println("Welche Datei soll umbenannt werden? Bsp (C:\\Users\\rathg\\Desktop\\helloWorld.txt)");
        String altedatei = eingabe(); // Alten Dateinamen speichern.

        System.out.println("In welches Format soll die Datei umgewandelt werden? Bsp. (.html)");
        String neuedatei = eingabe(); // Neue Endung speichern.

        String ausgabe = changeString(altedatei, neuedatei); // Fuktion Start; übergabe von Eingabe-Variablen.
        System.out.println(ausgabe); // Ausgabe Dateipfad mit neuer Endung.
    }

    /**
     * @param input Die Methode erhält einen String
     * @return und gibt den Teil vor dem ersten Punkt zurück.
     */
    public String cutString(String input) {
        return input.substring(0, input.lastIndexOf('.')); // Rückgabe, des bis zum letzten Punkt abgeschnittenen Strings
    }

    /**
     * @param fileNameOld Eingabe des alten Strings
     * @param fileNameNew Eingabe des neuen Strings
     * @return Umbenannter String des neuen Dateinamens
     */
    public String changeString(String fileNameOld, String fileNameNew){
        String s = cutString(fileNameOld); // Rückgabe in String s speichern
        s += fileNameNew; // Neue Endung an Abgeschnittenen String hängen
        return s;
    }

    /**
     * Handling der Konsoleneingabe
     * @return String der in der Konsole eingegeben wurde.
     */
    public String eingabe(){
        Scanner scanner = new Scanner(System.in);
        String eingabe = scanner.next();
        return eingabe;
    }
}
