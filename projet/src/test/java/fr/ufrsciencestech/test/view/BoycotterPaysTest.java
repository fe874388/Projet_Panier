package fr.ufrsciencestech.test.view;

import fr.ufrsciencestech.projet.model.Fruit;
import fr.ufrsciencestech.projet.model.PanierPleinException;
import fr.ufrsciencestech.projet.view.BoycotterPays;
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
 * Classe BoycotterPaysTest qui nous servira a testé la classe BoycotterPays
 * @author TD2 GROUPE 11
 */
public class BoycotterPaysTest {
    private BoycotterPays boycotterPays;
    private VueGraphiqueListe parent;

    @Before
    public void setUp() {
        SwingUtilities.invokeLater(() -> {
            try {
                parent = new VueGraphiqueListe();
            } catch (PanierPleinException ex) {
                Logger.getLogger(AjoutJusMacedoineTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            boycotterPays = new BoycotterPays(parent);
        });
    }
    
    @After
    public void tearDown() {
        SwingUtilities.invokeLater(() -> {
            boycotterPays.dispose();
            parent.dispose();
        });
    }

    @Test
    public void testBoycotterPays() {
        SwingUtilities.invokeLater(() -> {
            // Simulez la sélection d'un pays
            boycotterPays.jcb.setSelectedIndex(0); // Supposons que l'indice 0 représente un pays
            String boycottedCountry = (String) boycotterPays.jcb.getSelectedValue();
            // Simulez un clic sur le bouton "Boycotter"
            boycotterPays.boycotterButton.doClick();

            // Vérifiez que le pays a été boycotté
            boolean isCountryBoycotted = true;
            DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) parent.getjComboBox().getModel();
            for (int i = 0; i < model.getSize(); i++) {
                if (boycottedCountry.equals(model.getElementAt(i).getOrigine())) {
                    isCountryBoycotted = false;
                    break;
                }
            }
            assertTrue(isCountryBoycotted);
        });
    }
}
