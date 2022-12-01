package de.hsw;

 import static org.junit.jupiter.api.Assertions.assertEquals;
 import static org.junit.jupiter.api.Assertions.assertTrue;

 import org.junit.jupiter.api.Test;

public class StarterTest {

    @Test
    void testSuperZahlMin() {
       Lottozahlen lottozahlen = new Lottozahlen(null);
        Long seed = 1L;
        assertEquals(4, lottozahlen.superZahl());

    }
    @Test
    void testSuperZahlMax() {
        Lottozahlen lottozahlen = new Lottozahlen(null);
        Long seed = 1L;
        assertEquals(4, lottozahlen.superZahl());
    }

    @Test
    void testZusatzZahl(){
     //  Lottozahlen zusatzZahl = new
    }



    
}
