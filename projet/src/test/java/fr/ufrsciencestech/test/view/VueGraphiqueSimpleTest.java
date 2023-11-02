package fr.ufrsciencestech.test.view;

import fr.ufrsciencestech.projet.controler.Controleur;
import fr.ufrsciencestech.projet.view.VueGraphiqueSimple;
import javax.swing.SwingUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe VueGraphiqueSimpleTest qui nous servira a testé la classe VueGraphiqueSimple
 * @author TD2 GROUPE 11
 */
public class VueGraphiqueSimpleTest {
    private VueGraphiqueSimple vue;

    @Before
    public void setUp(){
        SwingUtilities.invokeLater(() -> {
            vue = new VueGraphiqueSimple();
        });
    }

    @After
    public void tearDown(){
        SwingUtilities.invokeLater(() -> {
            vue.dispose();
        });
    }
    
    @Test
    public void testIncrement() {
        SwingUtilities.invokeLater(() -> {
            // Simulez un clic sur le bouton d'incrémentation
            vue.getInc().doClick();
            // Vérifiez que le label a été mis à jour correctement
            assertEquals("1", vue.getAffiche().getText());
        });
    }

    @Test
    public void testDecrement() {
        SwingUtilities.invokeLater(() -> {
            // Simulez un clic sur le bouton de décrémentation
            vue.getDec().doClick();
            // Vérifiez que le label a été mis à jour correctement
            assertEquals("-1", vue.getAffiche().getText());
        });
    }
}
