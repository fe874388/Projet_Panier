package fr.ufrsciencestech.test.view;

import static org.mockito.Mockito.*;

import fr.ufrsciencestech.projet.model.Fruit;
import fr.ufrsciencestech.projet.view.ModificationduFruit;
import fr.ufrsciencestech.projet.view.ModifierFruit;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;

/**
 * Classe ModificationFruitTest qui nous servira a testé la classe ModificationFruit
 * @author TD2 GROUPE 11
 */
public class ModificationFruitTest {
    @Mock
    private ModifierFruit mockParent;
    @Mock
    private Fruit mockFruit;

    private ModificationduFruit modificationduFruitDialog;

    @Before
    public void setUp() {
        SwingUtilities.invokeLater(() -> {
            MockitoAnnotations.initMocks(this);
            when(mockFruit.getPrix()).thenReturn(1.0);
            when(mockFruit.getOrigine()).thenReturn("France");
            modificationduFruitDialog = new ModificationduFruit(mockParent, mockFruit);
        });
    }

    @Test
    public void testModFruit() {
        SwingUtilities.invokeLater(() -> {
            // Simuler les entrées utilisateur
            modificationduFruitDialog.prixField.setText("2.0");
            modificationduFruitDialog.paysField.setText("Espagne");

            // Appeler la méthode à tester
            modificationduFruitDialog.ModFruit();

            // Vérifier que les propriétés du fruit ont été mises à jour
            verify(mockFruit).setPrix(2.0);
            verify(mockFruit).setOrigine("Espagne");
        });
    }

    @Test
    public void testAnnulerAjout() {
        SwingUtilities.invokeLater(() -> {
            // Appeler la méthode à tester
            modificationduFruitDialog.annulerAjout();

            // Vérifier que la boîte de dialogue a été fermée
            verify(modificationduFruitDialog).dispose();
        });
    }
}