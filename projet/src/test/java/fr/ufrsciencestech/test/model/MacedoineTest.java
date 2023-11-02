package fr.ufrsciencestech.test.model;

import fr.ufrsciencestech.projet.model.Macedoine;
import fr.ufrsciencestech.projet.model.Banane;
import fr.ufrsciencestech.projet.model.Orange;
import static junit.framework.Assert.*;
import org.junit.*;

/**
 * Classe MacedoineTest qui nous servira a testé la classe Macedoine
 * @author TD2 GROUPE 11
 */
public class MacedoineTest {
    @Test
    public void testCreationMacedoineVide() {
        System.out.println("testCreationMacedoineVide()");
        Macedoine macedoine = new Macedoine();
        assertEquals(0.0, macedoine.getPrix(), 0.01);
        assertTrue(macedoine.getFruits().isEmpty());
    }

    @Test
    public void testCreationMacedoineAvecUnFruit() {
        System.out.println("testCreationMacedoineAvecUnFruit()");
        Banane banane = new Banane(0.5, "Martinique");
        Macedoine macedoine = new Macedoine(banane);
        assertEquals(0.5, macedoine.getPrix(), 0.01);
        assertEquals(1, macedoine.getFruits().size());
        assertTrue(macedoine.getFruits().contains(banane));
    }

    @Test
    public void testCreationMacedoineAvecDeuxFruits() {
        System.out.println("testCreationMacedoineAvecDeuxFruits()");
        Banane banane = new Banane(0.5, "Martinique");
        Orange orange = new Orange(0.6, "Espagne");
        Macedoine macedoine = new Macedoine(banane, orange);
        assertEquals(1.1, macedoine.getPrix(), 0.01);
        assertEquals(2, macedoine.getFruits().size());
        assertTrue(macedoine.getFruits().contains(banane));
        assertTrue(macedoine.getFruits().contains(orange));
    }

    @Test
    public void testAjoutFruitAMacedoine() {
        System.out.println("testAjoutFruitAMacedoine()");
        Macedoine macedoine = new Macedoine();
        Banane banane = new Banane(0.5, "Martinique");
        macedoine.ajoute(banane);
        assertEquals(0.5, macedoine.getPrix(), 0.01);
        assertEquals(1, macedoine.getFruits().size());
        assertTrue(macedoine.getFruits().contains(banane));
    }

    @Test
    public void testMacedoineToString() {
        System.out.println("testMacedoineToString()");
        Banane banane = new Banane(0.5, "Martinique");
        Orange orange = new Orange(0.6, "Espagne");
        Macedoine macedoine = new Macedoine(banane, orange);
        String expectedString = "Macedoine de (Banane, Orange) a 1.1 €";
        assertEquals(expectedString, macedoine.toString());
    }

    @Test
    public void testMacedoineEquals() {
        System.out.println("testMacedoineEquals()");
        Macedoine macedoine1 = new Macedoine();
        Macedoine macedoine2 = new Macedoine();
        Banane banane = new Banane(0.5, "Martinique");
        macedoine1.ajoute(banane);
        assertFalse(macedoine1.equals(macedoine2));

        Macedoine macedoine3 = new Macedoine(banane);
        assertTrue(macedoine1.equals(macedoine3));
    }

    @Test
    public void testMacedoineIsSeedless() {
        System.out.println("testMacedoineIsSeedless()");
        Macedoine macedoine = new Macedoine();
        assertTrue(macedoine.isSeedless());
    }
}
