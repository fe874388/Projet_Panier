package fr.ufrsciencestech.projet.view;

import fr.ufrsciencestech.projet.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Cette classe représente une boîte de dialogue permettant la modification d'un fruit.
 * @author TD2 Groupe 11
 */
public class ModificationduFruit extends JDialog {
    public JTextField nomField, prixField, paysField;
    public JButton confirmerButton, annulerButton;
    public ModifierFruit parent;
    public Fruit fruit;

    /**
     * Crée une nouvelle instance de la boîte de dialogue de modification de fruit.
     * @param parent La vue graphique parente à laquelle cette boîte de dialogue est attachée.
     */
    public ModificationduFruit(final ModifierFruit parent, Fruit f) {
        super(parent, "Modifier un fruit", true);
        this.parent = parent;
        this.fruit=f;
        
        initUI();
        initLayout();

        pack();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    /**
     * Initialise l'instance de la boîte de dialogue modification de fruit.
     */
    private void initUI() {
        nomField = new JTextField(10);
        prixField = new JTextField(10);
        paysField = new JTextField(10);
        
        nomField.setText(this.fruit.getClass().getSimpleName());
        nomField.setEditable(false);
        prixField.setText(String.valueOf(this.fruit.getPrix()));
        paysField.setText(this.fruit.getOrigine());
        
        confirmerButton = new JButton("Confirmer");
        annulerButton = new JButton("Annuler");

        confirmerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModFruit();
            }
        });

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annulerAjout();
            }
        });
    }

    /**
     * Gere la mise en page de la boîte de dialogue en ajoutant des composants au conteneur.
     * Cette méthode définit les dispositions des champs de texte, des étiquettes et des boutons dans la boîte de dialogue.
     */
    private void initLayout() {
        setLayout(new BorderLayout());
        // Crée un panneau pour les champs de texte
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajoute des étiquettes et des champs de texte pour le nom, le prix et le pays de provenance
        inputPanel.add(new JLabel("Nom du fruit:"));
        inputPanel.add(nomField);

        inputPanel.add(new JLabel("Prix du fruit:"));
        inputPanel.add(prixField);

        inputPanel.add(new JLabel("Pays de provenance:"));
        inputPanel.add(paysField);
        // Crée un panneau pour les boutons (Confirmer et Annuler)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(confirmerButton);
        buttonPanel.add(annulerButton);

        // Ajoute le panneau des champs de texte au centre de la boîte de dialogue
        add(inputPanel, BorderLayout.CENTER);
        // Ajoute le panneau des boutons en bas de la boîte de dialogue
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Modifie le fruit selectionnée avec une nouvelle origine et un nouveau prix
     */
    public void ModFruit() {
        try {
            double prix = Double.parseDouble(prixField.getText());
            String pays = paysField.getText();
            this.fruit.setPrix(prix);
            this.fruit.setOrigine(pays);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Le prix doit être un nombre valide.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
        }
    }


    /**
     * Annule la modification du fruit et ferme la boîte de dialogue.
     */
    public void annulerAjout() {
        dispose();
    }
}
