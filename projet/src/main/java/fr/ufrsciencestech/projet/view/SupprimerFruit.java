package fr.ufrsciencestech.projet.view;

import fr.ufrsciencestech.projet.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette classe représente une boîte de dialogue permettant de supprimer un fruit de la liste des fruits.
 * @author TD2 Groupe 11
 */
public class SupprimerFruit extends JDialog {
    public Fruit fruit;
    public VueGraphiqueListe parent;
    public JComboBox<Fruit> jcb;
    public JButton annulerButton;
    public JButton supprimerButton;
    
    /**
     * Constructeur de la boîte de dialogue SupprimerFruit pour supprimer un fruit.
     * @param p La vue graphique parente.
     */
    public SupprimerFruit(final VueGraphiqueListe p) {
        super(p, "Supprimer un fruit", true);
        this.parent = p;

        initUI();
        initLayout();

        pack();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    /**
     * Initialise les composants de la boîte de dialogue.
     */
    private void initUI() {
        DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) parent.getjComboBox().getModel();
        DefaultComboBoxModel<Fruit> modeleListeFruit = new DefaultComboBoxModel<>();
        for (int i=0; i<model.getSize(); i++){
            if(!(model.getElementAt(i).getClass().getSimpleName().equals("Macedoine") || model.getElementAt(i).getClass().getSimpleName().equals("Jus"))){
                modeleListeFruit .addElement(model.getElementAt(i));
            }
        }
        this.jcb = new JComboBox<>(modeleListeFruit);
    }

    /**
     * Initialise la mise en page de la boîte de dialogue.
     */
    private void initLayout() {
        setLayout(new BorderLayout());

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        comboBoxPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        comboBoxPanel.add(jcb);

        // Crée un panneau pour les boutons "Supprimer" et "Annuler"
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Crée le bouton "Supprimer" et définir un conseil (tooltip)
        // Crée le bouton "Annuler" et définir un conseil (tooltip)
        supprimerButton = new JButton("Supprimer");
        annulerButton = new JButton("Annuler");

        supprimerButton.setToolTipText("");
        annulerButton.setToolTipText("");

        // Associe un gestionnaire d'événements au bouton "Supprimer"
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supprimerFruit();
            }
        });
        // Associe un gestionnaire d'événements au bouton "Annuler"
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annulerSuppression();
            }
        });
        // Ajoute les boutons au panneau de boutons
        buttonPanel.add(supprimerButton);
        buttonPanel.add(annulerButton);
        // Ajoute le panneau du sélecteur de fruit en haut et le panneau de boutons en bas
        add(comboBoxPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Supprime le fruit sélectionné de la liste des fruits.
     */
    private void supprimerFruit() {
        Fruit elementSelectionne = (Fruit) jcb.getSelectedItem();
        if (!(elementSelectionne instanceof Macedoine || elementSelectionne instanceof Jus)) {
            parent.supprimerFruit(elementSelectionne);
            dispose();
        } else {
            // Afficher un message d'erreur ou prendre une autre action si nécessaire
            System.out.println("L'élément sélectionné n'est pas un Fruit.");
        }
    }

    /**
     * Annule l'opération de suppression de fruit.
     */
    private void annulerSuppression() {
        System.out.println("Annulé:");
        dispose();
    }
}