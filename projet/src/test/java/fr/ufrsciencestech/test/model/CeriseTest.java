package fr.ufrsciencestech.test.model;

import fr.ufrsciencestech.projet.model.Cerise;
import static junit.framework.Assert.*;
import org.junit.*;

/**
 * Classe CeriseTest qui nous servira a testé la classe Cerise
 * @author TD2 GROUPE 11
 */
public class CeriseTest {
     @Test
    public void testCreationCerise() {
        System.out.println("testCreationCerise()");
        Cerise cerise = new Cerise();
        assertEquals(0.6, cerise.getPrix(), 0.01);
        assertEquals("Italie", cerise.getOrigine());
    }

    @Test
    public void testCeriseAvecParametres() {
        System.out.println("testCeriseAvecParametres()");
        Cerise cerise = new Cerise(0.7, "France");
        assertEquals(0.7, cerise.getPrix(), 0.01);
        assertEquals("France", cerise.getOrigine());
    }

    @Test
    public void testCerisePrixNegatif() {
        System.out.println("testCerisePrixNegatif()");
        Cerise cerise = new Cerise(-0.5, "Grèce");
        assertEquals(0.5, cerise.getPrix(), 0.01);
        assertEquals("Grèce", cerise.getOrigine());
    }

    @Test
    public void testCeriseOrigineVide() {
        System.out.println("testCeriseOrigineVide()");
        Cerise cerise = new Cerise(0.6, "");
        assertEquals(0.6, cerise.getPrix(), 0.01);
        assertEquals("Italie", cerise.getOrigine());
    }

    @Test
    public void testCeriseToString() {
        System.out.println("testCeriseToString()");
        Cerise cerise = new Cerise(0.6, "Italie");
        String expectedString = "Cerise de Italie a 0.6 €";
        assertEquals(expectedString, cerise.toString());
    }

    @Test
    public void testCeriseEquals() {
        System.out.println("testCeriseEquals()");
        Cerise cerise1 = new Cerise(0.6, "Italie");
        Cerise cerise2 = new Cerise(0.6, "Italie");
        Cerise cerise3 = new Cerise(0.7, "France");
        assertTrue(cerise1.equals(cerise2));
        assertFalse(cerise1.equals(cerise3));
    }

    @Test
    public void testCeriseIsSeedless() {
        System.out.println("testCeriseIsSeedless()");
        Cerise cerise = new Cerise(0.6, "Italie");
        assertFalse(cerise.isSeedless());
    }
}
