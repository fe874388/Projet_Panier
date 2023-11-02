package fr.ufrsciencestech.test.model;

import fr.ufrsciencestech.projet.model.Ananas;
import static junit.framework.Assert.*;
import org.junit.*;

/**
 * Classe AnanasTest qui nous servira à testé la classe Ananas
 * @author TD2 GROUPE 11
 */
public class AnanasTest {
    @Test
    public void testCreationAnanas() {
        System.out.println("testCreationAnanas()");
        Ananas ananas = new Ananas();
        assertEquals(0.5, ananas.getPrix(), 0.01); // Vérifiez que le prix est correct.
        assertEquals("Espagne", ananas.getOrigine()); // Vérifiez que l'origine est correcte.
    }

    @Test
    public void testAnanasAvecParametres() {
        System.out.println("testAnanasAvecParametres()");
        Ananas ananas = new Ananas(0.7, "Costa Rica");
        assertEquals(0.7, ananas.getPrix(), 0.01);
        assertEquals("Costa Rica", ananas.getOrigine());
    }

    @Test
    public void testAnanasPrixNegatif() {
        System.out.println("testAnanasPrixNegatif()");
        Ananas ananas = new Ananas(-0.5, "Brésil");
        assertEquals(0.5, ananas.getPrix(), 0.01); // Le prix ne peut pas être négatif, il doit être corrigé.
        assertEquals("Brésil", ananas.getOrigine());
    }

    @Test
    public void testAnanasOrigineVide() {
        System.out.println("testAnanasOrigineVide()");
        Ananas ananas = new Ananas(0.6, "");
        assertEquals(0.6, ananas.getPrix(), 0.01);
        assertEquals("Espagne", ananas.getOrigine()); // L'origine ne peut pas être vide, elle doit être corrigée.
    }

    @Test
    public void testAnanasToString() {
        System.out.println("testAnanasToString()");
        Ananas ananas = new Ananas(0.6, "Espagne");
        String expectedString = "Ananas de Espagne a 0.6 €";
        assertEquals(expectedString, ananas.toString());
    }

    @Test
    public void testAnanasEquals() {
        System.out.println("testAnanasEquals()");
        Ananas ananas1 = new Ananas(0.6, "Espagne");
        Ananas ananas2 = new Ananas(0.6, "Espagne");
        Ananas ananas3 = new Ananas(0.7, "Costa Rica");
        assertTrue(ananas1.equals(ananas2));
        assertFalse(ananas1.equals(ananas3));
    }

   @Test
    public void testAnanasIsSeedless() {
        System.out.println("testAnanasIsSeedless()");
        Ananas ananas = new Ananas(0.6, "Espagne");
        assertTrue(ananas.isSeedless());
    }
}
