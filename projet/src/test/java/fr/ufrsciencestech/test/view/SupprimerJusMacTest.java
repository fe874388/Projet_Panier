package fr.ufrsciencestech.test.view;

import fr.ufrsciencestech.projet.model.*;
import fr.ufrsciencestech.projet.view.*;
import fr.ufrsciencestech.test.view.*;
import fr.ufrsciencestech.test.view.PanierPleinTest.TestUtils;
import javax.swing.*;
import org.junit.Before;
import org.junit.After;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Classe SupprimerJusMacTest qui nous servira a testé la classe SupprimerJusMac
 * @author TD2 GROUPE 11
 */
public class SupprimerJusMacTest {
    private VueGraphiqueListe mockParent;
    private SupprimerJusMac dialog;

    @Before
    public void setUp() {
        SwingUtilities.invokeLater(() -> {
            mockParent = mock(VueGraphiqueListe.class);
            JComboBox mockComboBox = new JComboBox();
            mockComboBox.setModel(new DefaultComboBoxModel<>(new Fruit[]{new Jus(), new Macedoine()}));
            when(mockParent.getjComboBox()).thenReturn(mockComboBox);
            dialog = new SupprimerJusMac(mockParent);
        });
    }

    @After
    public void tearDown(){
        SwingUtilities.invokeLater(() -> {
            dialog.dispose();
            mockParent.dispose();
        });
    }
    
    @Test
    public void testComboBoxContainsOnlyJusAndMacedoine() {
        SwingUtilities.invokeLater(() -> {
            JComboBox comboBox = (JComboBox) TestUtils.getChildNamed(dialog, "jcb");
            assertNotNull(comboBox);

            for (int i = 0; i < comboBox.getItemCount(); i++) {
                Fruit item = (Fruit) comboBox.getItemAt(i);
                assertTrue(item instanceof Jus || item instanceof Macedoine);
            }
        });
    }

    @Test
    public void testRemoveButtonFunctionality() {
        SwingUtilities.invokeLater(() -> {
            JComboBox comboBox = (JComboBox) TestUtils.getChildNamed(dialog, "jcb");
            assertNotNull(comboBox);

            JButton removeButton = (JButton) TestUtils.getChildNamed(dialog, "Supprimer");
            assertNotNull(removeButton);

            Fruit selectedFruit = (Fruit) comboBox.getSelectedItem();
            removeButton.doClick();

            verify(mockParent).supprimerFruit(selectedFruit);
        });
    }
}
