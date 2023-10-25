/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.projet.view;

import fr.ufrsciencestech.projet.controler.Controleur;
import fr.ufrsciencestech.projet.model.Modele;
import fr.ufrsciencestech.projet.model.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;


public class VueGraphiqueListe extends JFrame implements VueG{
    private JButton inc;
    private JButton dec;
    private JLabel affiche;
    private JLabel affiche2;
    
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JComboBox<Fruit> jComboBox;
    private JTextArea jTextArea;
    private JScrollPane scrollPane;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;

    private Controleur controleur;

    public VueGraphiqueListe() throws PanierPleinException {
        super("Panier Swing");

        FabriqueFruit fruit = new FabriqueFruit();
        Fruit ananas = fruit.creerFruit("ananas",2.0,"USA");
        Fruit banane = fruit.creerFruit("banane",1.0,"Inde");
        Fruit cerise = fruit.creerFruit("cerise",2.2,"France");
        Fruit fraise = fruit.creerFruit("fraise",1.99,"France");
        Fruit kiwi = fruit.creerFruit("kiwi",1.5,"Chine");
        Fruit orange = fruit.creerFruit("orange",0.99,"Espagne");
        
        Fruit[] fruits={ananas,banane,cerise,fraise,kiwi,orange};
        DefaultComboBoxModel<Fruit> modelCombo = new DefaultComboBoxModel<>();
        for (Fruit fru : fruits) {
            modelCombo.addElement(fru);
        }
        this.jComboBox = new JComboBox<>(modelCombo);
        
      
        // Création du menu bar
        menuBar = new JMenuBar();
        menu = new JMenu("Fruit");
        menuItem = new JMenuItem("Ajouter Fruit");
        menuItem4 = new JMenuItem("Ajouter Jus/Macedoine");
        menuItem2 = new JMenuItem("Supprimer Fruit/Jus/Macedoine");
        menuItem3 = new JMenuItem("Boycotter un Pays");
        menu.add(menuItem);
        menu.add(menuItem4);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menuBar.add(menu);
        setJMenuBar(menuBar);
              
        inc = new JButton("+");
        dec = new JButton("-");
        
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jTextArea = new JTextArea(18,30);
        JScrollPane scrollPane= new JScrollPane(jTextArea);

        affiche = new JLabel("0", JLabel.CENTER);
        affiche2 = new JLabel("Fruit(s) total dans le panier", JLabel.CENTER);
        jPanel2.add(scrollPane);
        jPanel2.add(affiche);
        jPanel2.add(affiche2);
        
        jComboBox.setSelectedIndex(0);
        jPanel1.add(jComboBox);
        jPanel1.add(inc);
        jPanel1.add(dec);
        
        add(jPanel1, BorderLayout.NORTH);
        add(jPanel2, BorderLayout.CENTER);
        
        inc.setName("Plus");
        dec.setName("Moins");
        jComboBox.setName("ComboBox");
        affiche.setName("Affichage");
        jTextArea.setText("Liste des fruit(s) dans mon Panier :\n");
        menuItem.setName("menuItemAjoutFruit");
        
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AjoutFruit ajoutFruitDialog = new AjoutFruit(VueGraphiqueListe.this);
            }
        });
        
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e0) {
                SupprimerFruit supprFruitDialog = new SupprimerFruit(VueGraphiqueListe.this);          
            }
        });
        
        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e5) {
                BoycotterPays BoycottePaysDialog = new BoycotterPays(VueGraphiqueListe.this);          
            }
        });
        
        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e6) {
                AjoutJusMacedoine AjoutJusMacedoineDialog = new AjoutJusMacedoine(VueGraphiqueListe.this);          
            }
        });
    
        this.pack();
        this.setSize(600, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
    
    public void addControleur(Controleur c){
        getInc().setActionCommand("Plus");
        getInc().addActionListener(c);
        getDec().setActionCommand("Moins");
        getDec().addActionListener(c);
        getDec().setActionCommand("Affichage");
        getDec().addActionListener(c);
        getjComboBox().setActionCommand("ComboBox");
        getjComboBox().addActionListener(c);
    }


    @Override
    public void update(Observable m, Object compteur) {
            if (m instanceof Modele) {
                Modele modele = (Modele) m;

                // Met à jour l'affichage du compteur
                affiche.setText(String.valueOf(modele.getCompteur()).toString());

                // Met à jour la liste des fruits dans le panier
                jTextArea.setText("Liste des fruit(s) dans mon Panier :\n");
                for (Fruit fruit : modele.getPanier().getFruits()) {
                    jTextArea.append(fruit.toString() + "\n");
                }
            }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("propertyChange method called!");
        if("value".equals(evt.getPropertyName())) {
            if (evt.getSource() instanceof Modele) {
                Modele m = (Modele) evt.getSource();
                m.setCompteur((Integer) evt.getNewValue());
                getAffiche().setText(String.valueOf(m.getCompteur()));
            }
        /*
            jTextArea.setText("Liste des fruit(s) dans mon Panier :\n");
            for (Fruit fruit : controleur.getPanier().getFruits()) {
                jTextArea.append(fruit.toString() + "\n");
            }
        */
        }
    }

    public void ajouterFruit(Fruit f) {
        DefaultComboBoxModel<Fruit> modela = (DefaultComboBoxModel<Fruit>) jComboBox.getModel();
        modela.addElement(f);    
        //jComboBox.addItem(f);
    }
    
    public void supprimerFruit(Fruit elementSelectionne) {
        DefaultComboBoxModel<Fruit> models = (DefaultComboBoxModel<Fruit>) jComboBox.getModel();
        models.removeElement(elementSelectionne);
        repaint();
    }
    
    public void Boycottepays(String elementSelectionne) {
        DefaultComboBoxModel<Fruit> models = (DefaultComboBoxModel<Fruit>) jComboBox.getModel();
        for (int i=0; i<models.getSize(); i++){
            if(models.getElementAt(i).getOrigine().toLowerCase().equals(elementSelectionne.toLowerCase())){
                models.removeElementAt(i);
                i--;
            }
        }
        if(models.getSize()>0)
            jComboBox.setSelectedIndex(0);
        repaint();
    }
    public void ajouterJusMacedoine(Fruit jm) {
        DefaultComboBoxModel<Fruit> modela = (DefaultComboBoxModel<Fruit>) jComboBox.getModel();
        modela.addElement(jm);    
        //jComboBox.addItem(f);
    }
    

    public JMenuItem getjmenuItem() {
        return this.menuItem;
    }
    public JComboBox getjComboBox() {
        return this.jComboBox;
    }
     
    /**
     * @return the inc
     */
    public JButton getInc() {
        return inc;
    }

    /**
     * @param inc the inc to set
     */
    public void setInc(JButton inc) {
        this.inc = inc;
    }

    /**
     * @return the dec
     */
    public JButton getDec() {
        return dec;
    }

    /**
     * @param dec the dec to set
     */
    public void setDec(JButton dec) {
        this.dec = dec;
    }

    /**
     * @return the affiche
     */
    public JLabel getAffiche() {
        return affiche;
    }

    /**
     * @param affiche the affiche to set
     */
    public void setAffiche(JLabel affiche) {
        this.affiche = affiche;
    }

}
