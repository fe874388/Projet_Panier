package fr.ufrsciencestech.projet.view;

import fr.ufrsciencestech.projet.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Classe ModifierFruit qui nous servira a modifié un fruit
 * @author TD2 GROUPE 11
 */
public class ModifierFruit extends JDialog {
    public VueGraphiqueListe parent;
    public JList<Fruit> fruitList;
    public JRadioButton macedoineRadioButton;
    public JRadioButton jusRadioButton;
    public JButton creerButton;
    public JButton annulerButton;
    
    /**
     * Constructeur de la boîte de dialogue de modification d'un fruit
     * @param p La vue graphique parente.
     */
    public ModifierFruit(final VueGraphiqueListe p) {
        super(p, "Modifier un Fruit", true);
        this.parent = p;

        initUI();
        initLayout();

        pack();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    /**
     * Initialisation des composants graphiques
     */
    private void initUI() {
        DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) parent.getjComboBox().getModel();
        DefaultListModel<Fruit> listModel = new DefaultListModel<>();
        for (int i = 0; i < model.getSize(); i++) {
            if (!(model.getElementAt(i) instanceof Macedoine) && !(model.getElementAt(i) instanceof Jus)) {
                listModel.addElement(model.getElementAt(i));
            }
        }

        fruitList = new JList<>(listModel);
        fruitList.setSelectedIndex(0);
        fruitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    /**
     * Mise en page des composants graphiques
     */
    private void initLayout() {
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(fruitList);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        creerButton = new JButton("Modifer");
        annulerButton = new JButton("Annuler");

        creerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Modificationdufruit();
            }
        });

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annulerModif();
            }
        });

        buttonPanel.add(creerButton);
        buttonPanel.add(annulerButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Fonction qui modifier le fruit selectionné par l'utilisateur sur l'IHM
     */
    public void Modificationdufruit() {
        Fruit selectedFruit = fruitList.getSelectedValue();
        ModificationduFruit modJDialog = new ModificationduFruit(this, selectedFruit);
        selectedFruit.setOrigine(modJDialog.fruit.getOrigine());
        selectedFruit.setPrix(modJDialog.fruit.getPrix());
        dispose();
    }
    
    /**
     * Fonction qui permet d'annuler la saisie et de fermer la fenetre de dialogue
     */
    public void annulerModif() {
        dispose();
    }
}
