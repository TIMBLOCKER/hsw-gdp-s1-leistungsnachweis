package de.hsw;

 import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StarterTest {

    @Test
    void testSuperZahlMin() {
       Lottozahlen lottozahlen = new Lottozahlen();
        Long seed = 1L;
        assertEquals(4, lottozahlen.superZahl(seed));

    }
    @Test
    void testSuperZahlMax() {
        Lottozahlen lottozahlen = new Lottozahlen();
        Long seed = 1L;
        assertEquals(4, lottozahlen.superZahl(seed));
    }

    @Test
    void testZusatzZahl(){
        
    }


    
}
