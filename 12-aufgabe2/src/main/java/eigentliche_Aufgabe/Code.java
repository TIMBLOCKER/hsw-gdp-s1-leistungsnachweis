package eigentliche_Aufgabe;

import java.util.Scanner;

public class Code {

    public void dateiumbenennen(){
        System.out.println("Welche Datei soll umbenannt werden? Bsp (C:\\Users\\rathg\\Desktop\\lol.txt)");

        String altedatei = eingabe();

        System.out.println("In welches Format soll die Datei umgewandelt werden? Bsp. (.html)");
        String neuedatei = eingabe();



        String arr = zerschnippel(altedatei);

        arr += neuedatei;

        System.out.println(arr);

    }

    public String zerschnippel(String param) {
        return param.substring(0, param.lastIndexOf('.'));
    }

    public String eingabe(){
        Scanner scanner = new Scanner(System.in);
        String eingabe = scanner.next();
        return eingabe;
    }
}


