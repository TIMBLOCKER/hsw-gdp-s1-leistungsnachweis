package de.hsw;

public class Giro extends Konto{

    double maxDispo, maxAuszahlung;

    public Giro(String iban, double saldo, double maxDispo, double maxAuszahlung) {
        super(iban, saldo);
        this.maxDispo = maxDispo;
        this.maxAuszahlung = maxAuszahlung;
    }

    public double getMaxDispo() {
        return maxDispo;
    }

    public void setMaxDispo(double maxDispo) {
        this.maxDispo = maxDispo;
    }

    public double getMaxAuszahlung() {
        return maxAuszahlung;
    }

    public void setMaxAuszahlung(double maxAuszahlung) {
        this.maxAuszahlung = maxAuszahlung;
    }
}
