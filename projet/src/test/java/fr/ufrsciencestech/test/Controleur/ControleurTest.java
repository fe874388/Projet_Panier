package fr.ufrsciencestech.test.Controleur;

import fr.ufrsciencestech.projet.controler.Controleur;
import fr.ufrsciencestech.projet.model.*;
import fr.ufrsciencestech.projet.view.AjoutFruit;
import fr.ufrsciencestech.projet.view.VueGraphiqueListe;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe ControleurTest qui nous servira a tester la Classe Controleur
 * @author TD2 GROUPE 11
 */
public class ControleurTest {
    private Controleur controleur;
    private Modele modele;
    private VueGraphiqueListe vue;
    private AjoutFruit vue1;
    private Panier panier;
    private Banane banane;

    @Before
    public void setup(){
        SwingUtilities.invokeLater(() -> {
            modele = new Modele();
            try {
                vue = new VueGraphiqueListe();
            } catch (PanierPleinException ex) {
                Logger.getLogger(ControleurTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            Banane banane = new Banane(0.6, "France"); 
            panier = new Panier(20);
            controleur = new Controleur(modele, vue);
            controleur.setPanier(panier);
            controleur.setcurrentFruit(banane);
        });
    }
    
    @After
    public void tearDown(){
        SwingUtilities.invokeLater(() -> {
            vue.dispose();
        });
    }

    @Test
    public void testAjoutFruitAuPanier(){
        SwingUtilities.invokeLater(() -> {
            System.out.println("testAjoutFruitAuPanier()");
            try {
                panier.ajout(banane); // Ajoute un fruit au panier pour le test.
            } catch (PanierPleinException ex) {
                Logger.getLogger(ControleurTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            JButton bouton = new JButton("Ajouter au panier");
            ActionEvent actionevent = new ActionEvent (bouton,0,"Ajouter"); 
            controleur.actionPerformed(actionevent); // Cela simule une action, par exemple un clic sur un bouton d'ajout.
            assertEquals(0, panier.getTaillePanier());
        });
    }

    @Test
    public void testRetraitFruitDuPanier(){
        SwingUtilities.invokeLater(() -> {
            System.out.println("testRetraitFruitDuPanier()");
            try {
                panier.retrait(); //retrait du dernier fruit du panier pour le test.
            } catch (PanierVideException ex) {
                Logger.getLogger(ControleurTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            JButton bouton = new JButton("retrait du panier");
            ActionEvent actionevent = new ActionEvent (bouton,0,"Retirer");
            controleur.actionPerformed(actionevent); // Cela simule une action, par exemple un clic sur un bouton de retrait.
            assertEquals(1, panier.getTaillePanier());
        });
    }

    @Test
    public void testChangementFruitCourant() {
        SwingUtilities.invokeLater(() -> {
            System.out.println("testChangementFruitCourant()");
            Ananas ananas = new Ananas(0.6, "Espagne");// Créez un nouveau fruit fictif pour le test.
            vue.getjComboBox().setSelectedItem(ananas);
            JButton bouton = new JButton("modif au panier");
            ActionEvent actionevent = new ActionEvent (bouton,0,"RAZP");
            controleur.actionPerformed(actionevent); // Cela simule une action, par exemple un changement de la sélection dans une liste déroulante.
            assertEquals(ananas, controleur.getcurrentFruit());
        });
    }
    
    @Test
    public void testSetPanier() {
        SwingUtilities.invokeLater(() -> {
            System.out.println("testSetPanier()");
            Panier nouveauPanier = new Panier(30);
            controleur.setPanier(nouveauPanier);
            assertEquals(nouveauPanier, controleur.getPanier());
        });
    }

    @Test
    public void testSetCurrentFruit() {
        SwingUtilities.invokeLater(() -> {
            System.out.println("testSetCurrentFruit()");
            Orange orange = new Orange(0.5, "Espagne");
            controleur.setcurrentFruit(orange);
            assertEquals(orange, controleur.getcurrentFruit());
        });
    }

    @Test
    public void testSetModele() {
        SwingUtilities.invokeLater(() -> {
            System.out.println("testSetModele()");
            Modele nouveauModele = new Modele();
            controleur.setModele(nouveauModele);
            assertEquals(nouveauModele, controleur.getModele());
        });
    }

    @Test
    public void testSetVue(){
        SwingUtilities.invokeLater(() -> {
            System.out.println("testSetVue()");
            VueGraphiqueListe nouvelleVue;
                try {
                    nouvelleVue = new VueGraphiqueListe();
                    controleur.setVue(nouvelleVue);
                    assertEquals(nouvelleVue, controleur.getVue());
                } catch (PanierPleinException ex) {
                    Logger.getLogger(ControleurTest.class.getName()).log(Level.SEVERE, null, ex);
                }
        });
    }
    
    @Test
    public void testRemplirCylindre(){
        SwingUtilities.invokeLater(() -> {
            System.out.println("testRemplirCylindre()");
            // Créez une instance de VueGraphiqueListe fictive
            VueGraphiqueListe vueFictive;
            try {
                vueFictive = new VueGraphiqueListe();
                vueFictive.getPanierProgressBar().setMinimum(0);
                vueFictive.getPanierProgressBar().setMaximum(20); // À ajuster selon vos besoins
                Panier panierFictif = new Panier(10);
                controleur.remplirCylindre(vueFictive);
                assertEquals(0, vueFictive.getPanierProgressBar().getValue()); // À ajuster selon vos besoins
                assertEquals("0%", vueFictive.getPanierProgressBar().getString()); // À ajuster selon vos besoins
            } catch (PanierPleinException ex) {
                Logger.getLogger(ControleurTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @Test
    public void testSetPrixTotal(){
        SwingUtilities.invokeLater(() -> {
            System.out.println("testSetPrixTotal()");
            VueGraphiqueListe vueFictive;
            try {
                vueFictive = new VueGraphiqueListe();
                // Créez un panier fictif et définissez un prix total fictif
                Panier panierFictif = new Panier(10); 
                panierFictif.ajout(new Orange(0.5, "Espagne")); // Par exemple, ajoutez une Orange fictive
                System.out.println(panierFictif.getPrix());
                double prixTotalFictif = 0.5; 
                controleur.setPrixTotal(vueFictive);
                assertEquals("Prix Total : " + prixTotalFictif + " €", vueFictive.getAffichePrix().getText());
            } catch (PanierPleinException ex) {
                Logger.getLogger(ControleurTest.class.getName()).log(Level.SEVERE, null, ex);
            } 
        });
    }

    @Test
    public void testAfficherErreurPanierPlein(){
        SwingUtilities.invokeLater(() -> {
            System.out.println("testAfficherErreurPanierPlein()");
            VueGraphiqueListe vueFictive;
            try {
                vueFictive = new VueGraphiqueListe();
                JButton boutonFictif = new JButton("Afficher Erreur");
                boutonFictif.setActionCommand("Erreur"); // Assurez-vous que le bouton a une action "Erreur"
                boutonFictif.addActionListener(controleur);
                controleur.afficherErreurPanierPlein(vueFictive);
                // Vérifiez si la boîte de dialogue s'affiche correctement (par exemple, vérifiez si une boîte de dialogue est visible)
                Component[] components = vueFictive.getComponents();
                for (Component component : components) {
                    if (component instanceof JDialog) {
                        JDialog dialog = (JDialog) component;
                        if (dialog.isDisplayable()) {
                            return; // La boîte de dialogue est visible
                        }
                    }
                }
                fail("La boîte de dialogue d'erreur n'a pas été affichée.");
            } catch (PanierPleinException ex) {
                Logger.getLogger(ControleurTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
