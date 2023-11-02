package fr.ufrsciencestech.test.model;

import fr.ufrsciencestech.projet.model.*;
import static junit.framework.Assert.*;
import org.junit.*;

/**
 * Classe PanierTest qui nous servira a testé la classe Panier
 * @author TD2 GROUPE 11
 */
public class PanierTest {
     @Test
    public void testCreationPanierVide() {
        System.out.println("testCreationPanierVide()");
        Panier panier = new Panier();
        assertEquals(0, panier.getTaillePanier());
        assertTrue(panier.estVide());
        assertFalse(panier.estPlein());
    }

    @Test
    public void testAjoutFruitAuPanier() {
        System.out.println("testAjoutFruitAuPanier()");
        Panier panier = new Panier();
        Banane banane = new Banane(0.5, "Martinique");
        try {
            panier.ajout(banane);
        } catch (PanierPleinException e) {
            fail("Le panier ne devrait pas être plein.");
        }
        assertEquals(1, panier.getTaillePanier());
        assertFalse(panier.estVide());
        assertFalse(panier.estPlein());
    }

    @Test
    public void testRetraitFruitDuPanier() {
        System.out.println("testRetraitFruitDuPanier()");
        Panier panier = new Panier();
        Banane banane = new Banane(0.5, "Martinique");
        try {
            panier.ajout(banane);
        } catch (PanierPleinException e) {
            fail("Ajout impossible car le panier est plein !");
        }
        try {
            panier.retrait();
        } catch (PanierVideException e) {
            fail("Suppression impossible car le panier est vide !");
        }
        assertEquals(0, panier.getTaillePanier());
        assertTrue(panier.estVide());
        assertFalse(panier.estPlein());
    }

    @Test
    public void testPrixTotalDuPanier() {
        System.out.println("testPrixTotalDuPanier()");
        Panier panier = new Panier();
        Banane banane = new Banane(0.5, "Martinique");
        Orange orange = new Orange(0.6, "Espagne");
        try {
            panier.ajout(banane);
            panier.ajout(orange);
        } catch (PanierPleinException e) {
            fail("Le panier ne devrait pas être plein.");
        }
        assertEquals(1.1, panier.getPrix(), 0.01);
    }

    @Test
    public void testBoycottOrigine() {
        System.out.println("testBoycottOrigine()");
        Panier panier = new Panier();
        Banane banane = new Banane(0.5, "Martinique");
        Orange orange = new Orange(0.6, "Espagne");
        Cerise cerise = new Cerise(0.7, "Italie");
        try {
            panier.ajout(banane);
            panier.ajout(orange);
            panier.ajout(cerise);
        } catch (PanierPleinException e) {
            fail("Le panier ne devrait pas être plein.");
        }
        panier.boycotteOrigine("Espagne");
        assertEquals(2, panier.getTaillePanier());
        assertTrue(panier.getFruits().contains(banane));
        assertTrue(panier.getFruits().contains(cerise));
        assertFalse(panier.getFruits().contains(orange));
    }

    @Test
    public void testEqualsPanier() {
        System.out.println("testEqualsPanier()");
        Panier panier1 = new Panier();
        Panier panier2 = new Panier();
        assertTrue(panier1.equals(panier2));

        Banane banane = new Banane(0.5, "Martinique");
        try {
            panier1.ajout(banane);
        } catch (PanierPleinException e) {
            fail("Le panier ne devrait pas être plein.");
        }
        assertFalse(panier1.equals(panier2));
    }
    
    @Test
    public void testPanierPlein() throws PanierPleinException {
        System.out.println("testPanierPlein()");
        Panier panier = new Panier();
        Banane banane = new Banane(0.5, "Martinique");
        for(int i=0;i<=10;i++){
            if (panier.getFruits().size() < panier.getContenanceMax()) {
                panier.ajout(banane);
        } 
        }
        assertTrue(panier.estPlein());
    }
}
