package de.hsw;

import java.util.Scanner;

public class Fakultatberechnen {

    public void fakultaetstarten(){
        try {
            System.out.println("Von welcher Zahl möchtest du die Fakultät rechnen?");
            int eingabe = eingabe();
            System.out.println();
            long fak = fakberechnen(eingabe);
            System.out.println("Die Fakultät deiner Zahl ist: " + fak);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public int eingabe(){
        Scanner scanner = new Scanner(System.in);
        int s = filter(scanner.next());
        return s;
    }

    public int filter(String eingabe)throws IllegalArgumentException{

        if (eingabe.matches("^(?i:[.,]+)$")){
            throw new IllegalArgumentException("Deine Eingabe ist keine ganze Zahl!");
        }

        else if (Integer.parseInt(eingabe) < 0) {
            throw new IllegalArgumentException("Deine Eingabe ist Negativ!");
        }

        else if (Integer.parseInt(eingabe) >= 0){
            return Integer.parseInt(eingabe);
        }

        throw new IllegalArgumentException("Deine Eingabe ist keine Zahl!");
    }

    public long fakberechnen(int eingabe){
        if (eingabe == 0){
            return 1;
        }else {
            long zahl = 1;
            for (long i = 1; i <= eingabe; i++) {
                zahl = zahl * i;
            }
            return zahl;
        }
    }
}
