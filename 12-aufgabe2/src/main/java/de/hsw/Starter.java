package de.hsw;

public class Starter {

    public static void main(String[] args) {
        Code code = new Code();

        System.out.println("Welche Datei soll umbenannt werden? Bsp (engel.pdf)");
        String altedatei = code.eingabe();

        System.out.println("In was / welches Format soll die Datei umbenannt/umgewandelt werden? Bsp. (hallo.html)");
        String neuedatei = code.eingabe();

        code.dateiumbenennen(altedatei, neuedatei);
    }

}