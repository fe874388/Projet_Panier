package fr.ufrsciencestech.test.model;

import fr.ufrsciencestech.projet.model.Banane;
import static junit.framework.Assert.*;
import org.junit.*;

/**
 * Classe BananeTest qui nous servira a testé la classe Banane
 * @author TD2 GROUPE 11
 */
public class BananeTest {
    @Test
    public void testCreationBanane() {
        System.out.println("testCreationBanane()");
        Banane banane = new Banane();
        assertEquals(0.5, banane.getPrix(), 0.01);
        assertEquals("Espagne", banane.getOrigine());
    }

    @Test
    public void testBananeAvecParametres() {
        System.out.println("testBananeAvecParametres()");
        Banane banane = new Banane(0.7, "Costa Rica");
        assertEquals(0.7, banane.getPrix(), 0.01);
        assertEquals("Costa Rica", banane.getOrigine());
    }

    @Test
    public void testBananePrixNegatif() {
        System.out.println("testBananePrixNegatif()");
        Banane banane = new Banane(-0.5, "Brésil");
        assertEquals(0.5, banane.getPrix(), 0.01);
        assertEquals("Brésil", banane.getOrigine());
    }

    @Test
    public void testBananeOrigineVide() {
        System.out.println("testBananeOrigineVide()");
        Banane banane = new Banane(0.6, "");
        assertEquals(0.6, banane.getPrix(), 0.01);
        assertEquals("Espagne", banane.getOrigine());
    }

    @Test
    public void testBananeToString() {
        System.out.println("testBananeToString()");
        Banane banane = new Banane(0.6, "Espagne");
        String expectedString = "Banane de Espagne a 0.6 €";
        assertEquals(expectedString, banane.toString());
    }

    @Test
    public void testBananeEquals() {
        System.out.println("testBananeEquals()");
        Banane banane1 = new Banane(0.6, "Espagne");
        Banane banane2 = new Banane(0.6, "Espagne");
        Banane banane3 = new Banane(0.7, "Costa Rica");
        assertTrue(banane1.equals(banane2));
        assertFalse(banane1.equals(banane3));
    }

    @Test
    public void testBananeIsSeedless() {
        System.out.println("testBananeIsSeedless()");
        Banane banane = new Banane(0.6, "Espagne");
        assertTrue(banane.isSeedless());
    }
}
