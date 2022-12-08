package de.hsw;

import de.hsw.jaxbUtils.Bankdaten;
import de.hsw.jaxbUtils.ConvertBank;
import de.hsw.jaxbUtils.Kontendaten;
import de.hsw.jaxbUtils.Kundendaten;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ConvertBankTest {

    @Test
    void bankToBankdaten() {
        ConvertBank convertBank = new ConvertBank();
        Starter starter = new Starter();
        Bank bank = new Bank();
        Giro konto = new Giro();

        bank.addKunde("Müller", "Peter", "Regenbogenstr. 5", LocalDate.of(2000, 10, 10));
        bank.addKonto(konto);

        ArrayList<Kundendaten> kundendatenArrayList = new ArrayList<>();
        ArrayList<Kontendaten> kontendatenarraylist = new ArrayList<>();

        Kontendaten kontendaten = new Kontendaten(konto.getIban(), konto.getSaldo(), konto.getMaxDispo(), "GI");
        kontendatenarraylist.add(kontendaten);
        Kundendaten kundendaten = new Kundendaten(0, "Müller", "Peter", "Regenbogenstr. 5", LocalDate.of(2000, 10, 10), kontendatenarraylist);
        kundendatenArrayList.add(kundendaten);
        Bankdaten bankdaten = new Bankdaten(bank.getName(), bank.getAdresse(), bank.getBlz(), kundendatenArrayList);
        assertThat(bankdaten).isEqualToComparingFieldByFieldRecursively(convertBank.bankToBankdaten(bank));
        //Hier keine weitere Testung möglich, dass die Objekte nicht eindeutig gleich sind.
    }

}