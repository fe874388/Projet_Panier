package fr.ufrsciencestech.test.view;

import fr.ufrsciencestech.projet.model.*;
import fr.ufrsciencestech.projet.view.AjoutFruit;
import fr.ufrsciencestech.projet.view.VueGraphiqueListe;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * Classe AjoutFruitTest qui nous servira a testé la classe AjoutFruit
 * @author TD2 GROUPE 11
 */
public class AjoutFruitTest {
    private VueGraphiqueListe parent;
    private AjoutFruit ajoutFruit;

    @Before
    public void setUp() {
        SwingUtilities.invokeLater(() -> {
            try {
                parent = new VueGraphiqueListe();
            } catch (PanierPleinException ex) {
                Logger.getLogger(AjoutFruitTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            ajoutFruit = new AjoutFruit(parent);
        });
    }
    @After
    public void tearDown() {
        SwingUtilities.invokeLater(() -> {
            ajoutFruit.dispose();
            parent.dispose();
        });
    }

    @Test
    public void testInitUI() {
        SwingUtilities.invokeLater(() -> {
            assertNotNull(ajoutFruit.nomField);
            assertNotNull(ajoutFruit.prixField);
            assertNotNull(ajoutFruit.paysField);
            assertNotNull(ajoutFruit.confirmerButton);
            assertNotNull(ajoutFruit.annulerButton);

        // Vérifie si les champs de texte sont vides initialement
            assertEquals("", ajoutFruit.nomField.getText());
            assertEquals("", ajoutFruit.prixField.getText());
            assertEquals("", ajoutFruit.paysField.getText());
            this.ajoutFruit.dispose();
        });
    }

    @Test
    public void testCreateFruit() {
        SwingUtilities.invokeLater(() -> {
            Fruit fruit = ajoutFruit.createFruit("orange", 2.5, "France");
            assertTrue(fruit instanceof Orange);
            assertEquals(2.5, fruit.getPrix(), 0.001);
            assertEquals("France", fruit.getOrigine());
            ajoutFruit.dispose();
        });
    }
    
    @Test
    public void testCreateFruitNonDisponible() {
        SwingUtilities.invokeLater(() -> {
            try {
                ajoutFruit.createFruit("pomme", 1.0, "Canada");
                fail("IllegalArgumentException devrait être lancée pour un fruit non disponible");
            } catch (IllegalArgumentException e) {
                assertEquals("Fruit non disponible", e.getMessage());
            }
            ajoutFruit.dispose();
        });
    }
}
