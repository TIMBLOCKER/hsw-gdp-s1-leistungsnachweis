package de.hsw.jaxbUtils;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "KONTENDATEN")
@XmlAccessorType(XmlAccessType.FIELD)
public class Kontendaten {

    @XmlAttribute
    String iban;
    @XmlAttribute
    private double saldo, maxDispo;

    @XmlAttribute
    private String type;

    public Kontendaten(String iban, double saldo, double maxDispo, String type) {
        this.iban = iban;
        this.saldo = saldo;
        this.maxDispo = maxDispo;
        this.type = type;
    }

    public Kontendaten() {}

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getMaxDispo() {
        return maxDispo;
    }

    public void setMaxDispo(double maxDispo) {
        this.maxDispo = maxDispo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
