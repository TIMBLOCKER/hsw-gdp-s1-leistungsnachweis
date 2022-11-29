package de.hsw;

import java.util.Scanner;

public class Checksummeberechnen {
    public void checksummestarten(){
        System.out.println("Bitte gib deinen String an: ");
        Scanner scanner = new Scanner(System.in);
        String eingabe = scanner.nextLine();
        String s = "", l = "";
        try {
            s = ueberpruefenaufzeichen(eingabe);
            l = ueberpruefeersteziffer(s);

            long checksumme = checksummenberechnung(l);

            String hexa = deziinhexa(checksumme);
            System.out.println("Die Checksumme ist: " + hexa);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public String ueberpruefeersteziffer(String eingabe) throws IllegalArgumentException{
        String firstChar = eingabe.charAt(0) + "";
        if (firstChar.matches("-?\\d+")){
            throw new IllegalArgumentException("Eine Zahl steht an der Front");
        } else {
            return eingabe;
        }
    }

    public String ueberpruefenaufzeichen(String eingabe) throws IllegalArgumentException{
        if (eingabe.matches("^(?i:[abcdefghijklmnopqrstuvwxyz 0123456789]+)$")){
            return eingabe;
        }
        throw new IllegalArgumentException("Dein String enthält illegale Zeichen!");
    }

    public long checksummenberechnung(String eingabe){
        long checksumme = 0;
        eingabe = eingabe.replace(" ", "");
        for (int i = 0; i < eingabe.length(); i++) {
            char c = eingabe.charAt(i);
            if (String.valueOf(c).matches("^(?i:[bcdfghjklmnpqrstvwxyz]+)$")){
                checksumme++;
            }
            if (String.valueOf(c).matches("^(?i:[aeiou]+)$")){
                checksumme--;
            }
            if (String.valueOf(c).matches("^(?i:[0123456789]+)$")){
                checksumme = checksumme * 2;
            }
        }
            return checksumme;
    }

    public String deziinhexa(long dez)throws IllegalArgumentException{
        if (dez < 0){
            throw new IllegalArgumentException("Die Checksumme wäre Negativ! Deine negative Checksumme lautet: " + dez);
        }else {
            String hex = Integer.toHexString((int) dez);
            return hex;
        }
    }
}
