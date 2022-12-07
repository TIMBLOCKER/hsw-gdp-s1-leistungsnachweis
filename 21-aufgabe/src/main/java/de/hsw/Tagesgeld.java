package de.hsw;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Tagesgeld extends Konto {

    @XmlAttribute
    double zinsen;

    public Tagesgeld() {
        this.zinsen = 0.75;
    }

    public Tagesgeld(String iban, double saldo) {
        super(iban, saldo);
        this.zinsen = 0.75;
    }

    public double getZinsen() {
        return zinsen;
    }

    public String getType() {
        return "Tagesgeld";
    }

    @Override
    public String toString() {
        return "Tagesgeld{" +
                "zinsen=" + zinsen +
                ", iban='" + iban + '\'' +
                '}';
    }
}
