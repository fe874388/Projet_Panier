package fr.ufrsciencestech.projet.view;

import fr.ufrsciencestech.projet.model.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Une boîte de dialogue pour retirer des fruits d'un panier.
 * @author TD2 Groupe 11
 */
public class RetirerFruit extends JDialog {
    public Fruit fruit;
    public JList<Fruit> listfruit;
    public Panier panier;
    public Modele model;
    public List<Fruit>  listeF = new ArrayList<>();
    public int valide;
    
    /**
     * Constructeur de la classe RetirerFruit.
     * @param vue La vue graphique principale.
     * @param p Le panier depuis lequel retirer les fruits.
     * @param m Le modèle à mettre à jour.
     */
    public RetirerFruit(VueGraphiqueListe vue,Panier p, Modele m){
        super(vue, "Retirer des éléments du panier", true);
        this.valide=0;
        this.panier=p;
        this.model=m;
        initUI();
        initLayout();

        pack();
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getWidth()/2, dim.height/2 - this.getWidth()/3);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Initialise l'interface utilisateur (UI) de la boîte de dialogue pour retirer des fruits du panier.
     * Cela inclut la configuration du modèle de liste, la création de la liste de fruits et l'ajout d'un JScrollPane.
     */
    private void initUI() {
        setLayout(new BorderLayout());
        // Crée un modèle de liste pour les fruits dans le panier.
        DefaultListModel<Fruit> modeleListeFruit = new DefaultListModel<>();
        for (int i = 0; i < this.panier.getTaillePanier(); i++) {
            Fruit fruit = this.panier.getFruit(i);
            modeleListeFruit.addElement(fruit);
        }
        // Crée une JList avec le modèle de liste de fruits.
        this.listfruit = new JList<>(modeleListeFruit);
        this.listfruit.setSelectedIndex(0);
        // Ajoute la liste à un JScrollPane pour permettre le défilement.
        JScrollPane scrollPane = new JScrollPane(this.listfruit);
        this.listfruit.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }
    
    /**
     * Initialise la mise en page de la boîte de dialogue, y compris les composants GUI comme les boutons.
     */
    private void initLayout() {
        setLayout(new BorderLayout());

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        comboBoxPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        comboBoxPanel.add(listfruit);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton supprimerButton = new JButton("Retirer");
        JButton annulerButton = new JButton("Annuler");

        // Ajoute des tooltips pour expliquer le but des boutons.
        supprimerButton.setToolTipText("Cliquez pour retirer le fruit sélectionné.");
        annulerButton.setToolTipText("Cliquez pour annuler l'opération de retrait.");

        // Configure les actions à effectuer lorsque les boutons sont cliqués.
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    supprimerFruit();
                } catch (PanierPleinException ex) {
                    Logger.getLogger(RetirerFruit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annulerSuppression();
            }
        });

        buttonPanel.add(supprimerButton);
        buttonPanel.add(annulerButton);

        add(comboBoxPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Retire les fruits sélectionnés du panier.
     * @throws PanierPleinException Si le panier est plein après le retrait.
     */
    private void supprimerFruit() throws PanierPleinException {
        setvalide(1);
        List<Fruit> selectedFruits = this.listfruit.getSelectedValuesList();
        try {
            this.panier.retraitFruit(selectedFruits);
        } catch (PanierVideException ex) {
            Logger.getLogger(RetirerFruit.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(Fruit fruit : selectedFruits) {
            this.model.update(-1);
        }
        dispose();
    }
    
    /**
     * Annule l'opération de retrait et ferme la boîte de dialogue.
     */
    private void annulerSuppression() {
        dispose();
    }

    /**
     * Obtient la valeur de la variable "valide".
     * @return La valeur de la variable "valide".
     */
    public int getvalide() {
        return this.valide;
    }

    /**
     * Définit la valeur de la variable "valide".
     * @param x La nouvelle valeur de la variable "valide".
     */
    public void setvalide(int x) {
        this.valide=x;
    }

    /**
     * Définit la liste de fruits.
     * @param f La liste de fruits à définir.
     */
    public void setListF(List<Fruit> f) {
        this.listeF = f;
    }

    /**
     * Obtient la liste de fruits.
     * @return La liste de fruits.
     */
    public List<Fruit> getListF() {
        return this.listeF;
    }

    /**
     * Obtient le panier depuis lequel les fruits sont retirés.
     * @return Le panier.
     */
    public Panier getPanier() {
        return this.panier;
    }
        
}
