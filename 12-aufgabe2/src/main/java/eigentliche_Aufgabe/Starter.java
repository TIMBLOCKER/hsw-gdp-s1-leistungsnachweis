package eigentliche_Aufgabe;

public class Starter {

    public static void main(String[] args) {
        Code code = new Code();

        System.out.println("Welche Datei soll umbenannt werden? Bsp (C:\\Users\\rathg\\Desktop\\lol.txt)");
        String altedatei = code.eingabe(); // Alten Dateinamen speichern.

        System.out.println("In welches Format soll die Datei umgewandelt werden? Bsp. (.html)");
        String neuedatei = code.eingabe(); // Neue Endung speichern.

        String ausgabe = code.dateiumbenennen(altedatei, neuedatei); // Fuktion Start; übergabe von Eingabe-Variablen.

        System.out.println(ausgabe); // Ausgabe Dateipfad mit neuer Endung.
    }

}
