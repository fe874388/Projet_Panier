package fr.ufrsciencestech.test.model;

import fr.ufrsciencestech.projet.model.Fraise;
import static junit.framework.Assert.*;
import org.junit.*;

/**
 * Classe FraiseTest qui nous servira a testé la classe Fraise
 * @author TD2 GROUPE 11
 */
public class FraiseTest {
    @Test
    public void testCreationFraise() {
        System.out.println("testCreationFraise()");
        Fraise fraise = new Fraise();
        assertEquals(0.5, fraise.getPrix(), 0.01);
        assertEquals("Espagne", fraise.getOrigine());
    }

    @Test
    public void testFraiseAvecParametres() {
        System.out.println("testFraiseAvecParametres()");
        Fraise fraise = new Fraise(0.7, "France");
        assertEquals(0.7, fraise.getPrix(), 0.01);
        assertEquals("France", fraise.getOrigine());
    }

    @Test
    public void testFraisePrixNegatif() {
        System.out.println("testFraisePrixNegatif()");
        Fraise fraise = new Fraise(-0.5, "Italie");
        assertEquals(0.5, fraise.getPrix(), 0.01);
        assertEquals("Italie", fraise.getOrigine());
    }

    @Test
    public void testFraiseOrigineVide() {
        System.out.println("testFraiseOrigineVide()");
        Fraise fraise = new Fraise(0.6, "");
        assertEquals(0.6, fraise.getPrix(), 0.01);
        assertEquals("Espagne", fraise.getOrigine());
    }

    @Test
    public void testFraiseToString() {
        System.out.println("testFraiseToString()");
        Fraise fraise = new Fraise(0.6, "Espagne");
        String expectedString = "Fraise de Espagne a 0.6 €";
        assertEquals(expectedString, fraise.toString());
    }

    @Test
    public void testFraiseEquals() {
        System.out.println("testFraiseEquals()");
        Fraise fraise1 = new Fraise(0.6, "Espagne");
        Fraise fraise2 = new Fraise(0.6, "Espagne");
        Fraise fraise3 = new Fraise(0.7, "France");
        assertTrue(fraise1.equals(fraise2));
        assertFalse(fraise1.equals(fraise3));
    }

    @Test
    public void testFraiseIsSeedless() {
        System.out.println("testFraiseIsSeedless()");
        Fraise fraise = new Fraise(0.6, "Espagne");
        assertTrue(fraise.isSeedless());
    }
}
