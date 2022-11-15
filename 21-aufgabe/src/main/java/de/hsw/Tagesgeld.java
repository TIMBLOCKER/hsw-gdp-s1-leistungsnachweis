package de.hsw;

public class Tagesgeld extends Konto{

    double zinsen;

    public Tagesgeld(String iban,  double zinsen) {
        super(iban);
        this.zinsen = zinsen;
    }

    public Tagesgeld(double zinsen) {
        super();
        this.zinsen = zinsen;
    }

    public double getZinsen() {
        return zinsen;
    }

    public void setZinsen(double zinsen) {
        this.zinsen = zinsen;
    }
}
