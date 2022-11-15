package de.hsw;
import java.io.File;
import java.util.Scanner;

public class Code {

    public String eingabe(){
        Scanner scanner = new Scanner(System.in); //Konsoleneingabe.
        String eingabe = scanner.next();
        return eingabe;
    }

    public void dateiumbenennen(String altedatei, String neuedatei){

        File fileold = new File(altedatei); // Zu bennenende File unter angegebenem Namen finden.
        File filenew = new File(neuedatei); // Neuen File-Namen festlegen.

        if (fileold.exists()) { // Wenn zu bennende File existiert ->
            System.out.println(fileold.renameTo(filenew)); // Umbenennen der File in neuen Namen.
        }
        else {
            System.out.println("File does not exist"); // Falls File nicht existent gib ... aus.
        }
    }
}



