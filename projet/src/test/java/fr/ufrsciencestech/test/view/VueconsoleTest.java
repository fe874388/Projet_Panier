package fr.ufrsciencestech.test.view;

import fr.ufrsciencestech.projet.model.Modele;
import fr.ufrsciencestech.projet.view.VueConsole;
import org.junit.Before;
import org.junit.Test;
import java.beans.PropertyChangeEvent;
import javax.swing.SwingUtilities;
import org.junit.After;
import static org.junit.Assert.*;

/**
 * Classe VueconsoleTest qui nous servira a testé la classe Vueconsole
 * @author TD2 GROUPE 11
 */
public class VueconsoleTest {
    private VueConsole vueConsole;
    private Modele modele;

    @Before
    public void setUp() {
        SwingUtilities.invokeLater(() -> {
            vueConsole = new VueConsole();
            modele = new Modele();
        });
    }
    
    @After
    public void tearDown(){
       
    }

    @Test
    public void testPropertyChange() {
        SwingUtilities.invokeLater(() -> {
            PropertyChangeEvent evt = new PropertyChangeEvent(modele, "compteur", 0, 5);
            vueConsole.propertyChange(evt);

            String expectedTrace = "Nouvelle valeur : 5";
            assertEquals(expectedTrace, vueConsole.getTrace());
        });
    }

   
}

