package fr.ufrsciencestech.test.model;

import fr.ufrsciencestech.projet.model.Kiwi;
import static junit.framework.Assert.*;
import org.junit.*;

/**
 * Classe KiwiTest qui nous servira a testé la classe Kiwi
 * @author TD2 GROUPE 11
 */
public class KiwiTest {
    @Test
    public void testCreationKiwi() {
        System.out.println("testCreationKiwi()");
        Kiwi kiwi = new Kiwi();
        assertEquals(0.5, kiwi.getPrix(), 0.01);
        assertEquals("Espagne", kiwi.getOrigine());
    }

    @Test
    public void testKiwiAvecParametres() {
        System.out.println("testKiwiAvecParametres()");
        Kiwi kiwi = new Kiwi(0.7, "Nouvelle-Zélande");
        assertEquals(0.7, kiwi.getPrix(), 0.01);
        assertEquals("Nouvelle-Zélande", kiwi.getOrigine());
    }

    @Test
    public void testKiwiPrixNegatif() {
        System.out.println("testKiwiPrixNegatif()");
        Kiwi kiwi = new Kiwi(-0.5, "Italie");
        assertEquals(0.5, kiwi.getPrix(), 0.01);
        assertEquals("Italie", kiwi.getOrigine());
    }

    @Test
    public void testKiwiOrigineVide() {
        System.out.println("testKiwiOrigineVide()");
        Kiwi kiwi = new Kiwi(0.6, "");
        assertEquals(0.6, kiwi.getPrix(), 0.01);
        assertEquals("Espagne", kiwi.getOrigine());
    }

    @Test
    public void testKiwiToString() {
        System.out.println("testKiwiToString()");
        Kiwi kiwi = new Kiwi(0.6, "Espagne");
        String expectedString = "Kiwi de Espagne a 0.6 €";
        assertEquals(expectedString, kiwi.toString());
    }

    @Test
    public void testKiwiEquals() {
        System.out.println("testKiwiEquals()");
        Kiwi kiwi1 = new Kiwi(0.6, "Espagne");
        Kiwi kiwi2 = new Kiwi(0.6, "Espagne");
        Kiwi kiwi3 = new Kiwi(0.7, "Nouvelle-Zélande");
        assertTrue(kiwi1.equals(kiwi2));
        assertFalse(kiwi1.equals(kiwi3));
    }

    @Test
    public void testKiwiIsSeedless() {
        System.out.println("testKiwiIsSeedless()");
        Kiwi kiwi = new Kiwi(0.6, "Espagne");
        assertFalse(kiwi.isSeedless());
    }

}
