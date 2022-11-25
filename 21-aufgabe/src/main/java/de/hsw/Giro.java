package de.hsw;

public class Giro extends Konto {

    double maxDispo, maxAuszahlung;

    public Giro(String iban, double saldo, double maxDispo, double maxAuszahlung) {
        super(iban);
        this.maxDispo = -1000;
        this.maxAuszahlung = maxAuszahlung;
    }

    public Giro(double maxDispo, double maxAuszahlung) {
        super();
        this.maxDispo = maxDispo;
        this.maxAuszahlung = maxAuszahlung;
    }

    public Giro() {
        super();
        this.maxDispo = 1000;
        this.maxAuszahlung = 1000;
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

    public String getType() {
        return "Giro";
    }
}
