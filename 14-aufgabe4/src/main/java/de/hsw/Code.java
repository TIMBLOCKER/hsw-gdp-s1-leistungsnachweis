package de.hsw;

import java.util.Scanner;

public class Code {

    public String eingabe() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String zeichen_ersetzen(String s) {
        if (s.matches("^(?i:[abcdefghijklmnopqrstuvwxyz,.!?;\":]+)$")) {
            s = s.replaceAll("[^a-zA-Z]", "");
            s = s.replace(" ", "");
            s = s.toLowerCase();
            return s;
        } else {
            throw new IllegalArgumentException("Invalid char");
        }
    }

    public String strumdrehen(String s) {
        String[] arr = new String[s.length()];
        int counter = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            arr[counter] = String.valueOf(s.charAt(i));
            counter++;
        }

        String newstring = "";
        for (int i = 0; i < arr.length; i++) {
            newstring += arr[i];

        }
        return newstring;
    }

    public void vergleich(String s1, String s2) {
        if (s1.equals(s2)) {
            System.out.println("Dein String ist ein Palindrom!");
        } else {
            System.out.println("Leider hast du kein Palindrom gefunden!");
        }
    }
}

