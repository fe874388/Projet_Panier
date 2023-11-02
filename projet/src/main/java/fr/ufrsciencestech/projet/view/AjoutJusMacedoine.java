package fr.ufrsciencestech.projet.view;
import fr.ufrsciencestech.projet.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Cette classe est une boîte de dialogue (interface graphique)
 * Elle sert a créer et ajouter une macédoine ou un jus pour l'inserer au catalogue de l'IHM principale
 * @author TD2 Groupe 11
 */
public class AjoutJusMacedoine extends JDialog {
    public VueGraphiqueListe parent;
    public JList<Fruit> fruitList;
    public JRadioButton macedoineRadioButton;
    public JRadioButton jusRadioButton;
    public JButton creerButton;
    public JButton annulerButton;
    /**
     * Constructeur de la boîte de dialogue d'ajout de Jus/Macédoine.
     * @param p La vue graphique parente.
     */
    public AjoutJusMacedoine(final VueGraphiqueListe p) {
        super(p, "Ajouter Jus/Macedoine", true);
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
        macedoineRadioButton = new JRadioButton("Macédoine");
        jusRadioButton = new JRadioButton("Jus");

        ButtonGroup group = new ButtonGroup();
        macedoineRadioButton.setSelected(true);
        group.add(macedoineRadioButton);
        group.add(jusRadioButton);

        DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) parent.getjComboBox().getModel();
        DefaultListModel<Fruit> listModel = new DefaultListModel<>();
        for (int i = 0; i < model.getSize(); i++) {
            if (!(model.getElementAt(i) instanceof Macedoine) && !(model.getElementAt(i) instanceof Jus)) {
                listModel.addElement(model.getElementAt(i));
            }
        }

        fruitList = new JList<>(listModel);
        fruitList.setSelectedIndex(0);
        fruitList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }
    
    /**
     * Mise en page des composants graphiques
     */
    private void initLayout() {
        setLayout(new BorderLayout());

        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        radioButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        radioButtonPanel.add(macedoineRadioButton);
        radioButtonPanel.add(jusRadioButton);

        JScrollPane scrollPane = new JScrollPane(fruitList);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        creerButton = new JButton("Créer");
        annulerButton = new JButton("Annuler");

        creerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creerJusMacedoine();
            }
        });

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annulerAjout();
            }
        });

        buttonPanel.add(creerButton);
        buttonPanel.add(annulerButton);

        add(radioButtonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Fonction qui créer la Macedoine ou le Jus en fonction des éléments selectionnés par l'utilisateur sur l'IHM
     */
    private void creerJusMacedoine() {
        List<Fruit> selectedFruits = fruitList.getSelectedValuesList();
        if (macedoineRadioButton.isSelected()) {
            Macedoine macedoine = new Macedoine();
            for (Fruit fruit : selectedFruits) {
                macedoine.ajoute(fruit);
            }
            parent.ajouterJusMacedoine(macedoine);
        } else if (jusRadioButton.isSelected()) {
            Jus jus = new Jus();
            for (Fruit fruit : selectedFruits) {
                jus.ajoute(fruit);
            }
            parent.ajouterJusMacedoine(jus);
        }
        dispose();
    }
    
    /**
     * Fonction qui permet d'annuler la saisie et de fermer la fenetre de dialogue
     */
    private void annulerAjout() {
        dispose();
    }
}
