package de.hsw;
import java.io.File;
import java.util.Scanner;

public class Code {

    public void dateiumbenennen(){
        System.out.println("Welche Datei soll umbenannt werden? Bsp (engel.pdf)");
        String altedatei = eingabe();

        System.out.println("In was / welches Format soll die Datei umbenannt/umgewandelt werden? Bsp. (hallo.html)");
        String neuedatei = eingabe();


        File fileold = new File(altedatei);
        File filenew = new File(neuedatei);

        if (fileold.exists()) {
            System.out.println(fileold.renameTo(filenew));
        }
        else {
            System.out.println("File does not exist");
        }
    }

    public String eingabe(){
        Scanner scanner = new Scanner(System.in);
        String eingabe = scanner.next();
        return eingabe;
    }
}



