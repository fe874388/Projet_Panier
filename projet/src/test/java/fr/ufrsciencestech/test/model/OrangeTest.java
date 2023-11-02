package fr.ufrsciencestech.test.model;

import fr.ufrsciencestech.projet.model.Orange;
import static junit.framework.Assert.*;
import org.junit.*;

/**
 * Classe OrangeTest qui nous servira a testé la classe Orange
 * @author TD2 GROUPE 11
 */
public class OrangeTest {
    @Test
    public void testCreationOrange() {
        System.out.println("testCreationOrange()");
        Orange orange = new Orange();
        assertEquals(0.5, orange.getPrix(), 0.01);
        assertEquals("Espagne", orange.getOrigine());
    }

    @Test
    public void testOrangeAvecParametres() {
        System.out.println("testOrangeAvecParametres()");
        Orange orange = new Orange(0.7, "Maroc");
        assertEquals(0.7, orange.getPrix(), 0.01);
        assertEquals("Maroc", orange.getOrigine());
    }

    @Test
    public void testOrangePrixNegatif() {
        System.out.println("testOrangePrixNegatif()");
        Orange orange = new Orange(-0.5, "Espagne");
        assertEquals(0.5, orange.getPrix(), 0.01);
        assertEquals("Espagne", orange.getOrigine());
    }

    @Test
    public void testOrangeOrigineVide() {
        System.out.println("testOrangeOrigineVide()");
        Orange orange = new Orange(0.6, "");
        assertEquals(0.6, orange.getPrix(), 0.01);
        assertEquals("Espagne", orange.getOrigine());
    }

    @Test
    public void testOrangeToString() {
        System.out.println("testOrangeToString()");
        Orange orange = new Orange(0.6, "Espagne");
        String expectedString = "Orange de Espagne a 0.6 €";
        assertEquals(expectedString, orange.toString());
    }

    @Test
    public void testOrangeEquals() {
        System.out.println("testOrangeEquals()");
        Orange orange1 = new Orange(0.6, "Espagne");
        Orange orange2 = new Orange(0.6, "Espagne");
        Orange orange3 = new Orange(0.7, "Maroc");
        assertTrue(orange1.equals(orange2));
        assertFalse(orange1.equals(orange3));
    }

    @Test
    public void testOrangeIsSeedless() {
        System.out.println("testOrangeIsSeedless()");
        Orange orange = new Orange(0.6, "Espagne");
        assertFalse(orange.isSeedless());
    }
}
