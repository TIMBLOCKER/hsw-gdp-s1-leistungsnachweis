package de.hsw;

public class Starter{
    public static void main(String[] args) {
        Code code = new Code();

        System.out.println("Gib ein welchen String du auf ein palindrom testen willst");
        String seingabe = code.eingabe();

        String zeichenlos = code.zeichen_ersetzen(seingabe);
        String umgedreht = code.strumdrehen(zeichenlos);
        code.vergleich(zeichenlos, umgedreht);

    }
}