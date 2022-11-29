package de.hsw;

import java.util.Scanner;

public class Palindromerkennen {
    public void palindromstarten(){

        System.out.println("Gib ein welchen String du auf ein Palindrom testen willst");
        Scanner scanner = new Scanner(System.in);

        try {
            String zeichenlos = zeichen_ersetzen(scanner.nextLine());
            String umgedreht = strumdrehen(zeichenlos);
            vergleich(zeichenlos, umgedreht);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String zeichen_ersetzen(String s) throws IllegalArgumentException{
        if (s.matches("^(?i:[abcdefghijklmnopqrstuvwxyz,.!?;\": ]+)$")) {
            s = s.replaceAll("[^a-zA-Z]", "");
            s = s.replace(" ", "");
            s = s.toLowerCase();
            return s;
        } else {
            throw new IllegalArgumentException("Invalid char");
        }
    }

    public String strumdrehen(String l)throws IllegalArgumentException{
        if (l.matches("^([abcdefghijklmnopqrstuvwxyz]+)$")) {
            String[] arr = new String[l.length()];
            int counter = 0;
            for (int i = l.length()-1; i >= 0; i --){
                arr[counter] = String.valueOf(l.charAt(i));
                counter++;
            }

            String newstring = "";
            for (int i = 0; i < arr.length; i++) {
                newstring += arr[i];

            }
            return newstring;
        }else {
            throw new IllegalArgumentException("Invalide Eingabe");
        }
    }

    private void vergleich(String s1, String s2){
        if (s1.equals(s2)){
            System.out.println("Dein String ist ein Palindrom!");
        }else {
            System.out.println("Leider hast du kein Palindrom gefunden!");
        }
    }
}
