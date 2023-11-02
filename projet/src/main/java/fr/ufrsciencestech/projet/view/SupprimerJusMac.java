package fr.ufrsciencestech.projet.view;
import fr.ufrsciencestech.projet.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Une boîte de dialogue permettant de supprimer un élément (Jus ou Macedoine) de la liste.
 * @author TD2 Groupe 11
 */
public class SupprimerJusMac extends JDialog {
    public VueGraphiqueListe parent;
    public JComboBox<Fruit> jcb;
    
    /**
     * Constructeur de la classe SupprimerJusMac.
     * @param p La VueGraphiqueListe parente de cette boîte de dialogue.
     */
    public SupprimerJusMac(final VueGraphiqueListe p) {
        super(p, "Supprimer Jus/Macedoine", true);
        this.parent = p;
        // Appelle le constructeur de la classe mère JDialog.
        setLayout(new BorderLayout());
        // Obtient le modèle de la liste des éléments depuis la VueGraphiqueListe parente.
        DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) this.parent.getjComboBox().getModel();
        // Crée un modèle de liste pour les éléments Jus/Macedoine.
        DefaultComboBoxModel<Fruit> modeleListeJusMacedoine = new DefaultComboBoxModel<>();
        for (int i=0; i<model.getSize(); i++){
            if(model.getElementAt(i).getClass().getSimpleName().equals("Macedoine") || model.getElementAt(i).getClass().getSimpleName().equals("Jus")){
                modeleListeJusMacedoine .addElement(model.getElementAt(i));
            }
        }
        // Crée une liste déroulante (combobox) avec les éléments Jus/Macedoine.
        this.jcb = new JComboBox<>(modeleListeJusMacedoine);
        add(jcb, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton supprimerButton = new JButton("Supprimer");
        JButton annulerButton = new JButton("Annuler");
        // Définit les infobulles pour les boutons.
        supprimerButton.setToolTipText("Cliquez pour supprimer l'élément sélectionné.");
        annulerButton.setToolTipText("Cliquez pour annuler l'opération de suppression.");

        // Définit l'action à effectuer lors du clic sur le bouton "Supprimer".
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                Fruit elementSelectionne = (Fruit) jcb.getSelectedItem();
                if (elementSelectionne instanceof Macedoine || elementSelectionne instanceof Jus) {
                    parent.supprimerFruit(elementSelectionne);
                    dispose(); // Ferme la fenêtre de dialogue
                } else {
                    // Afficher un message d'erreur ou prendre une autre action si nécessaire
                    System.out.println("L'élément sélectionné n'est ni une Macedoine ni un Jus.");
                }
            }
        });
        // Définit l'action à effectuer lors du clic sur le bouton "Annuler".
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                System.out.println("Annulé:");
                dispose(); // Ferme la fenêtre de dialogue sans créer de fruit
            }
        });
        // Ajoute les boutons au panneau de boutons.
        buttonPanel.add(supprimerButton);
        buttonPanel.add(annulerButton);
        // Ajoute le panneau de boutons à la partie inférieure de la boîte de dialogue.
        add(buttonPanel, BorderLayout.SOUTH);

        pack();                        // Redimensionne automatiquement la boîte de dialogue en fonction de son contenu.
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // Définit l'opération de fermeture par défaut sur "DISPOSE_ON_CLOSE".
        setVisible(true);        // Rend la boîte de dialogue visible.
    }
}
