package fr.ufrsciencestech.test.view;

import fr.ufrsciencestech.projet.model.Fruit;
import fr.ufrsciencestech.projet.model.FabriqueFruit;
import fr.ufrsciencestech.projet.model.Jus;
import fr.ufrsciencestech.projet.model.PanierPleinException;
import fr.ufrsciencestech.projet.view.VueGraphiqueListe;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe VuegraphiqueListeTest qui nous servira a testé la classe VuegraphiqueListe
 * @author TD2 GROUPE 11
 */
public class VuegraphiqueListeTest {
      private VueGraphiqueListe vue;

    @Before
    public void setUp(){
        SwingUtilities.invokeLater(() -> {
            try {
                vue = new VueGraphiqueListe();
            } catch (PanierPleinException ex) {
                Logger.getLogger(VuegraphiqueListeTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    @After
    public void tearDown(){
        SwingUtilities.invokeLater(() -> {
            vue.dispose();
        });
    }

    @Test
    public void testAjouterFruit() {
        SwingUtilities.invokeLater(() -> {
            FabriqueFruit fabrique = new FabriqueFruit();
            Fruit pomme = fabrique.creerFruit("pomme", 1.5, "France");
            vue.ajouterFruit(pomme);
            assertEquals(pomme, vue.dernierFruitAjoute);
        });

    }

    @Test
    public void testSupprimerFruit() {
        SwingUtilities.invokeLater(() -> {
            FabriqueFruit fabrique = new FabriqueFruit();
            Fruit pomme = fabrique.creerFruit("pomme", 1.5, "France");
            vue.ajouterFruit(pomme);
            vue.supprimerFruit(pomme);
            // Vérifiez que le fruit n'est plus dans la liste (vous devrez peut-être ajouter des méthodes pour cela)
            assertEquals(pomme, vue.dernierFruitAjoute);
        });
    }

    @Test
    public void testBoycottepays() {
        SwingUtilities.invokeLater(() -> {
            FabriqueFruit fabrique = new FabriqueFruit();
            Fruit pomme = fabrique.creerFruit("pomme", 1.5, "France");
            Fruit orange = fabrique.creerFruit("orange", 1.0, "Espagne");
            vue.ajouterFruit(pomme);
            vue.ajouterFruit(orange);
            vue.boycotterPays("France");
        });  
    }

    @Test
    public void testAjouterJusMacedoine() {
        SwingUtilities.invokeLater(() -> {
            FabriqueFruit fabrique = new FabriqueFruit();
            Fruit pomme = fabrique.creerFruit("pomme", 1.5, "France");
            vue.ajouterJusMacedoine(pomme);
            DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) vue.getjComboBox().getModel();
            boolean found = false;
            for (int i = 0; i < model.getSize(); i++) {
                Fruit item = model.getElementAt(i);
                if (item.equals(pomme) ) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);
        });  
    }

    @Test
    public void testOption1() {
        SwingUtilities.invokeLater(() -> {
            FabriqueFruit fabrique = new FabriqueFruit();
            Fruit pomme = fabrique.creerFruit("pomme", 1.5, "France");
            Fruit orange = fabrique.creerFruit("orange", 1.0, "Espagne");
            vue.ajouterFruit(pomme);
            vue.ajouterFruit(orange);
            // Appliquez l'option 1 (Panier à moins de 2€/fruit)
            vue.Option1();
            // Assurez-vous que seuls les fruits répondant à l'option 1 sont affichés
            DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) vue.getjComboBox().getModel();
            for (int i = 0; i < model.getSize(); i++) {
                assertTrue(model.getElementAt(i).getPrix() < 2.0);
            }
        }); 
    }

    @Test
    public void testOption2() {
        SwingUtilities.invokeLater(() -> {
            FabriqueFruit fabrique = new FabriqueFruit();
            Fruit pomme = fabrique.creerFruit("pomme", 1.5, "France");
            Fruit orange = fabrique.creerFruit("orange", 1.0, "Espagne");
            vue.ajouterFruit(pomme);
            vue.ajouterFruit(orange);

            // Appliquez l'option 2 (Fruits locaux (France))
            vue.Option2();

            // Assurez-vous que seuls les fruits locaux (France) sont affichés
            DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) vue.getjComboBox().getModel();
            for (int i = 0; i < model.getSize(); i++) {
                assertTrue(model.getElementAt(i).getOrigine().equalsIgnoreCase("France"));
            }
        });
    }

    @Test
    public void testOption3() {
        SwingUtilities.invokeLater(() -> {
            FabriqueFruit fabrique = new FabriqueFruit();
            Fruit pomme = fabrique.creerFruit("pomme", 1.5, "France");
            Fruit orange = fabrique.creerFruit("orange", 1.0, "Espagne");
            Jus jus = new Jus(pomme, orange);
            vue.ajouterFruit(pomme);
            vue.ajouterFruit(orange);
            vue.ajouterJusMacedoine(jus);

            // Appliquez l'option 3 (Fruit seulement)
            vue.Option3();

            // Assurez-vous que seuls les fruits (pas de jus/macedoine) sont affichés
            DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) vue.getjComboBox().getModel();
            for (int i = 0; i < model.getSize(); i++) {
                assertFalse(model.getElementAt(i).getClass().getSimpleName().equals("Macedoine"));
                assertFalse(model.getElementAt(i).getClass().getSimpleName().equals("Jus"));
            }
        });
    }
}
