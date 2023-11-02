package fr.ufrsciencestech.test.view;

import fr.ufrsciencestech.projet.model.*;
import fr.ufrsciencestech.projet.view.RetirerFruit;
import fr.ufrsciencestech.projet.view.VueGraphiqueListe;
import java.awt.Component;
import java.awt.Container;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe RetirerFruitTest qui nous servira a testé la classe RetirerFruit
 * @author TD2 GROUPE 11
 */
public class RetirerFruitTest {
    private RetirerFruit retirerFruit;
    private Panier panier;
    private Modele modele;
    private VueGraphiqueListe vue;

    @Before
    public void setUp(){
        SwingUtilities.invokeLater(() -> {
            panier = new Panier(10); // Supposons que le panier a une capacité de 10
            modele = new Modele();
            try {
                vue = new VueGraphiqueListe(); // Supposons que vous avez un constructeur par défaut pour VueGraphiqueListe
            } catch (PanierPleinException ex) {
                Logger.getLogger(RetirerFruitTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            retirerFruit = new RetirerFruit(vue, panier, modele);
        });
    }
    
    @After
    public void tearDown(){
        SwingUtilities.invokeLater(() -> {
            retirerFruit.dispose();
            vue.dispose();
        });
    }

    @Test
    public void testListFruitsDisplayed() {
        SwingUtilities.invokeLater(() -> {
            JList<Fruit> listFruit = (JList<Fruit>) TestUtils.getChildNamed(retirerFruit, "javax.swing.JList");
            assertNotNull(listFruit);
            // Assurez-vous que la liste affiche les fruits du panier
            assertEquals(panier.getTaillePanier(), listFruit.getModel().getSize());
        });
    }

    @Test
    public void testRemoveButtonFunctionality(){
        SwingUtilities.invokeLater(() -> {
            Fruit fruit = new Orange(); // Supposons que Orange est une sous-classe de Fruit
            try {
                panier.ajout(fruit);
            } catch (PanierPleinException ex) {
                Logger.getLogger(RetirerFruitTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            int initialSize = panier.getTaillePanier();

            JButton removeButton = (JButton) TestUtils.getChildNamed(retirerFruit, "Retirer");
            assertNotNull(removeButton);

            // Simulez un clic sur le bouton "Retirer"
            removeButton.doClick();

            // Vérifiez que le fruit a été retiré du panier
            assertEquals(initialSize - 1, panier.getTaillePanier());
        });
    }

    @Test
    public void testCancelButtonFunctionality() {
        SwingUtilities.invokeLater(() -> {
            JButton cancelButton = (JButton) TestUtils.getChildNamed(retirerFruit, "Annuler");
            assertNotNull(cancelButton);

            // Simulez un clic sur le bouton "Annuler"
            cancelButton.doClick();

            // Vérifiez que la fenêtre est fermée
            assertFalse(retirerFruit.isDisplayable());
        });
    }
    
    public static class TestUtils {
        public static Component getChildNamed(Component parent, String name) {
            if (name.equals(parent.getName())) { return parent; }
            if (parent instanceof Container) {
                for (Component child : ((Container) parent).getComponents()) {
                    Component result = getChildNamed(child, name);
                    if (result != null) { return result; }
                }
            }
            return null;
        }
    }
}
