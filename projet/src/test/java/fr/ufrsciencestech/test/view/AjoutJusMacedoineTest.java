package fr.ufrsciencestech.test.view;

import fr.ufrsciencestech.projet.model.Jus;
import fr.ufrsciencestech.projet.model.Macedoine;
import fr.ufrsciencestech.projet.model.PanierPleinException;
import fr.ufrsciencestech.projet.view.AjoutJusMacedoine;
import fr.ufrsciencestech.projet.view.VueGraphiqueListe;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe AjoutJusMacedoineTest qui nous servira a testé la classe AjoutJusMacedoine
 * @author TD2 GROUPE 11
 */
public class AjoutJusMacedoineTest {
    private AjoutJusMacedoine ajoutJusMacedoine;
    private VueGraphiqueListe parent;

    @Before
    public void setUp() {
    SwingUtilities.invokeLater(() -> {
        try {
            parent = new VueGraphiqueListe();
        } catch (PanierPleinException ex) {
            Logger.getLogger(AjoutJusMacedoineTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        ajoutJusMacedoine = new AjoutJusMacedoine(parent);
    });
    
    }
    @After
    public void tearDown() {
        SwingUtilities.invokeLater(() -> {
            ajoutJusMacedoine.dispose();
            parent.dispose();
        });
    }

    @Test
    public void testCreerMacedoine() {
        SwingUtilities.invokeLater(() -> {
        // Simulez la sélection de plusieurs fruits
            ajoutJusMacedoine.fruitList.setSelectedIndices(new int[]{0, 1}); // Supposons que les indices 0 et 1 représentent des fruits

        // Sélectionnez l'option Macédoine
            ajoutJusMacedoine.macedoineRadioButton.setSelected(true);
        // Simulez un clic sur le bouton "Créer"
            ajoutJusMacedoine.creerButton.doClick();

        // Vérifiez que la Macédoine a été ajoutée à la liste
        // Pour cela, nous supposons que la méthode dernierFruitAjoute de VueGraphiqueListe nous donne le dernier fruit ajouté.
        assertTrue(parent.dernierFruitAjoute instanceof Macedoine);
        });
    }

    @Test
    public void testCreerJus() {
        SwingUtilities.invokeLater(() -> {
        // Simulez la sélection de plusieurs fruits
            ajoutJusMacedoine.fruitList.setSelectedIndices(new int[]{0, 1}); // Supposons que les indices 0 et 1 représentent des fruits

        // Sélectionnez l'option Jus
            ajoutJusMacedoine.macedoineRadioButton.setSelected(true);
        // Simulez un clic sur le bouton "Créer"
            ajoutJusMacedoine.creerButton.doClick();

        // Vérifiez que le Jus a été ajouté à la liste
            assertTrue(parent.dernierFruitAjoute instanceof Jus);
        });
    }
}
