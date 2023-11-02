package fr.ufrsciencestech.projet.view;

import fr.ufrsciencestech.projet.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette classe représente une boîte de dialogue permettant d'ajouter un fruit à la liste de fruits.
 * @author TD2 Groupe 11
 */
public class AjoutFruit extends JDialog {
    public JTextField nomField, prixField, paysField;
    public JButton confirmerButton, annulerButton;
    public VueGraphiqueListe parent;

    /**
     * Crée une nouvelle instance de la boîte de dialogue d'ajout de fruit.
     * @param parent La vue graphique parente à laquelle cette boîte de dialogue est attachée.
     */
    public AjoutFruit(final VueGraphiqueListe parent) {
        super(parent, "Ajouter un fruit", true);
        this.parent = parent;

        initUI();
        initLayout();

        pack();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    /**
     * Initialise l'instance de la boîte de dialogue d'ajout de fruit.
     */
    private void initUI() {
        nomField = new JTextField(10);
        prixField = new JTextField(10);
        paysField = new JTextField(10);

        confirmerButton = new JButton("Confirmer");
        annulerButton = new JButton("Annuler");

        confirmerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterFruit();
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
     * Ajoute un fruit à la liste de fruits de la vue parente (vue principale).
     */
    public void ajouterFruit() {
        try {
            String nom = nomField.getText();
            double prix = Double.parseDouble(prixField.getText());
            String pays = paysField.getText();

            Fruit fruit = createFruit(nom, prix, pays);
            parent.ajouterFruit(fruit);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Le prix doit être un nombre valide.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Crée une instance de fruit en fonction des informations saisies.
     * @param nom  Le nom du fruit.
     * @param prix Le prix du fruit.
     * @param pays Le pays de provenance du fruit.
     * @return Une instance de Fruit ou l'une de ses sous-classes.
     * @throws IllegalArgumentException Si le nom du fruit n'est pas reconnu.
     */
    public Fruit createFruit(String nom, double prix, String pays) {
        switch (nom.toLowerCase()) {
            case "orange":
                return new Orange(prix, pays);
            case "ananas":
                return new Ananas(prix, pays);
            case "banane":
                return new Banane(prix, pays);
            case "cerise":
                return new Cerise(prix, pays);
            case "kiwi":
                return new Kiwi(prix, pays);
            case "fraise":
                return new Fraise(prix, pays);
            default:
                throw new IllegalArgumentException("Fruit non disponible");
        }
    }

    /**
     * Annule l'ajout de fruit et ferme la boîte de dialogue.
     */
    private void annulerAjout() {
        dispose();
    }
}
