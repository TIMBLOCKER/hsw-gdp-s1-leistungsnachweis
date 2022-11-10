package de.hsw;

public class Tagesgeld extends Konto{

    double zinsen;

    public Tagesgeld(String iban, double saldo, double zinsen) {
        super(iban, saldo);
        this.zinsen = zinsen;
    }

    public double getZinsen() {
        return zinsen;
    }

    public void setZinsen(double zinsen) {
        this.zinsen = zinsen;
    }
}
