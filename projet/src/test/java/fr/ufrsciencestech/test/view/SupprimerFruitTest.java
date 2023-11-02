package fr.ufrsciencestech.test.view;

import fr.ufrsciencestech.projet.model.Fruit;
import fr.ufrsciencestech.projet.model.PanierPleinException;
import fr.ufrsciencestech.projet.view.SupprimerFruit;
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
 * Classe SupprimerFruitTest qui nous servira a testé la classe SupprimerFruit
 * @author TD2 GROUPE 11
 */
public class SupprimerFruitTest {
    private SupprimerFruit supprimerFruit;
    private VueGraphiqueListe parent;

    @Before
    public void setUp() throws Exception {
        SwingUtilities.invokeLater(() -> {
            try {
                parent = new VueGraphiqueListe();
            } catch (PanierPleinException ex) {
                Logger.getLogger(SupprimerFruitTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            supprimerFruit = new SupprimerFruit(parent);
        });

    }

    @After
    public void tearDown(){
        SwingUtilities.invokeLater(() -> {
            supprimerFruit.dispose();
            parent.dispose();
        });
    }
    
    @Test
    public void testSupprimerFruit() {
        SwingUtilities.invokeLater(() -> {
            // Simulez la sélection d'un fruit
            supprimerFruit.jcb.setSelectedIndex(0); // Supposons que l'indice 0 représente un fruit

            Fruit selectedFruit = (Fruit) supprimerFruit.jcb.getSelectedItem();

            // Simulez un clic sur le bouton "Supprimer"
            //JButton supprimerButton = (JButton) TestUtils.getChildNamed(supprimerFruit, "Supprimer");
            supprimerFruit.supprimerButton.doClick();

            // Vérifiez que le fruit a été supprimé
            DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) parent.getjComboBox().getModel();
            assertFalse(model.getIndexOf(selectedFruit) != -1);
        });
    }
}
