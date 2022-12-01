package de.hsw;

import java.util.Scanner;

public class Palindromerkennen {

    /**
     * Startermethode
     */
    public void palindromStarter(){
        System.out.println("Gib ein welchen String du auf ein Palindrom testen willst");
        Scanner scanner = new Scanner(System.in);
        try {
            String zeichenlos = replaceZeichen(scanner.nextLine());
            String umgedreht = flipString(zeichenlos);
            if(isPalindrom(zeichenlos, umgedreht)){
                //TODO Hier palindrom code
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    //Todo: Variablennamen sprechend
    //Todo: rathg fragen, was das tut
    public String replaceZeichen(String s) throws IllegalArgumentException{
        if (s.matches("^(?i:[abcdefghijklmnopqrstuvwxyz,.!?;\": ]+)$")) {
            s = s.replaceAll("[^a-zA-Z]", "");
            s = s.replace(" ", "");
            s = s.toLowerCase();
            return s;
        } else {
            throw new IllegalArgumentException("Invalid char");
        }
    }

    /**
     * @param eingabe Diese Methode nimmt einen Eingabestring und
     * @return  gibt diesen umgedreht zurück
     * @throws IllegalArgumentException Wenn die eingabe inkorrekt ist, wird dieser Fehler geschmissen.
     */
    public String flipString(String eingabe)throws IllegalArgumentException{
        if (eingabe.matches("^([abcdefghijklmnopqrstuvwxyz]+)$")) {
            String[] arr = new String[eingabe.length()];
            int counter = 0;
            for (int i = eingabe.length()-1; i >= 0; i --){
                arr[counter] = String.valueOf(eingabe.charAt(i));
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

    /**
     * @param s1 Diese Methode nimmt zwei Strings und vergleicht diese.
     * @param s2 Die Strings sind die Ordiginaleingabe und der umgedrehte String.
     *           Wenn beide gleich sind, handelt es sich um ein Palindrom
     * @return Dies wird zurückgegeben und dem Nutzer in die Konsole gedruckt.
     */
    private boolean isPalindrom(String s1, String s2){
        if (s1.equals(s2)){
            System.out.println("Dein String ist ein Palindrom!");
            return true;
        }else {
            System.out.println("Leider hast du kein Palindrom gefunden!");
        }
        return false;
    }
}
