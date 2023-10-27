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


public class RetirerFruit extends JDialog {
    private Fruit fruit;
    private JList<Fruit> listfruit;
    private Panier panier;
    private Modele model;
    List<Fruit>  listeF = new ArrayList<>();
    private int valide;
    
    public RetirerFruit(VueGraphiqueListe vue,Panier p, Modele m){
        super(vue, "Retirer des éléments du panier", true);
        this.valide=0;
        this.panier=p;
        this.model=m;
        initUI();
        initLayout();

        pack();
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        // Centre la fenêtre sur l'écran
        this.setLocation(dim.width/2-this.getWidth()/2, dim.height/2 - this.getWidth()/3);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initUI() {
        setLayout(new BorderLayout());

        DefaultListModel<Fruit> modeleListeFruit = new DefaultListModel<>();
        for (int i = 0; i < this.panier.getTaillePanier(); i++) {
            Fruit fruit = this.panier.getFruit(i);
            modeleListeFruit.addElement(fruit);
        }
        
        this.listfruit = new JList<>(modeleListeFruit);
        this.listfruit.setSelectedIndex(0);

        JScrollPane scrollPane = new JScrollPane(this.listfruit);
        this.listfruit.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

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

        supprimerButton.setToolTipText("");
        annulerButton.setToolTipText("");

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

    private void annulerSuppression() {
        dispose();
    }
    public int getvalide() {
        return this.valide;
    }
    
    public void setvalide(int x) {
        this.valide=x;
    }
        
    public void setListF(List<Fruit> f) {
        this.listeF = f;
    }
    
    public List<Fruit> getListF() {
        return this.listeF;
    }
        
        
    public Panier getPanier() {
        return this.panier;
    }
        
}
