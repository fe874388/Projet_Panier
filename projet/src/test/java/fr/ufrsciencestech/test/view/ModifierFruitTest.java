package fr.ufrsciencestech.test.view;

import static org.mockito.Mockito.*;
import fr.ufrsciencestech.projet.model.Fruit;
import fr.ufrsciencestech.projet.view.ModificationduFruit;
import fr.ufrsciencestech.projet.view.ModifierFruit;
import fr.ufrsciencestech.projet.view.VueGraphiqueListe;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.swing.*;

/**
 * Classe ModifierFruitTest qui nous servira a testé la classe ModifierFruit
 * @author TD2 GROUPE 11
 */
public class ModifierFruitTest {
    @Mock
    private VueGraphiqueListe mockParent;
    @Mock
    private JList<Fruit> mockFruitList;
    @Mock
    private JComboBox<Fruit> mockComboBox;
    @Mock
    private ModificationduFruit mockModifDialog;

    private ModifierFruit modifierFruitDialog;

    @Before
    public void setUp() {
        SwingUtilities.invokeLater(() -> {
            MockitoAnnotations.initMocks(this);
            when(mockParent.getjComboBox()).thenReturn(mockComboBox);
            when(mockComboBox.getModel()).thenReturn(new DefaultComboBoxModel<>());
            modifierFruitDialog = new ModifierFruit(mockParent);
            modifierFruitDialog.fruitList = mockFruitList; // Remplacer par le mock
        });
    }

    @Test
    public void testModificationdufruit() {
        SwingUtilities.invokeLater(() -> {
            // Simuler un fruit sélectionné
            Fruit selectedFruit = mock(Fruit.class);
            when(mockFruitList.getSelectedValue()).thenReturn(selectedFruit);

            // Simuler la boîte de dialogue de modification
            when(mockModifDialog.fruit).thenReturn(selectedFruit);

            // Appeler la méthode à tester
            modifierFruitDialog.Modificationdufruit();

            // Vérifier que la boîte de dialogue de modification a été affichée
            verify(mockModifDialog).setVisible(true);

            // Vérifier que les propriétés du fruit ont été mises à jour
            verify(selectedFruit).setOrigine(anyString());
            verify(selectedFruit).setPrix(anyDouble());
        });
    }

    @Test
    public void testAnnulerModif() {
        SwingUtilities.invokeLater(() -> {
            // Appeler la méthode à tester
            modifierFruitDialog.annulerModif();

            // Vérifier que la boîte de dialogue a été fermée
            verify(modifierFruitDialog).dispose();
        });
    }
}
