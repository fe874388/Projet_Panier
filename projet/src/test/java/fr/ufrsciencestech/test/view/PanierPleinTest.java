package fr.ufrsciencestech.test.view;

import fr.ufrsciencestech.projet.view.PanierPlein;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe PanierPleinTest qui nous servira a testé la classe PanierPlein
 * @author TD2 GROUPE 11
 */
public class PanierPleinTest {
     private PanierPlein panierPlein;

    @Before
    public void setUp() throws Exception {
        SwingUtilities.invokeLater(() -> {
            panierPlein = new PanierPlein("Test Message");
        });
    }

    @After
    public void tearDown(){
        SwingUtilities.invokeLater(() -> {
            panierPlein.dispose();
        });

    }
    @Test
    public void testErrorMessageDisplayed() {
        SwingUtilities.invokeLater(() -> {
            JLabel errorMessageLabel = (JLabel) TestUtils.getChildNamed(panierPlein, "javax.swing.JLabel");
            assertNotNull(errorMessageLabel);
            assertEquals("Test Message", errorMessageLabel.getText());
        });
    }

    @Test
    public void testCloseButtonFunctionality() {
        SwingUtilities.invokeLater(() -> {
            JButton closeButton = (JButton) TestUtils.getChildNamed(panierPlein, "Fermer");
            assertNotNull(closeButton);

            // Simulez un clic sur le bouton "Fermer"
            closeButton.doClick();

            // Vérifiez que la fenêtre est fermée
            assertFalse(panierPlein.isDisplayable());
        });
    }

    // Vous pourriez avoir besoin d'une classe utilitaire pour obtenir des composants par leur nom
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
