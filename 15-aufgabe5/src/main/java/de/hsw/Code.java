package de.hsw;
import java.util.Scanner;

public class Code {

    public int eingabe(){
        Scanner scanner = new Scanner(System.in);
        int s = filter(scanner.nextInt());
        return s;
    }

    public int filter(int eingabe){
        int s = -1;
        while(s == -1){
            if (eingabe < 0) {
                System.out.println("Diese Eingabe ist nicht möglich! Versuche es erneut!");
            }else {
                s = eingabe;
            }
        }
        return s;
    }

    public void fakberechnen(int eingabe){
        if (eingabe == 0){
            System.out.println("Die Fakultät deiner Zahl ist: 1");
        }else {
            long zahl = 1;
            for (long i = 1; i <= eingabe; i++) {
                zahl = zahl * i;
            }
            System.out.println("Die Fakultät deiner Zahl ist: " + zahl);
        }
    }

}
