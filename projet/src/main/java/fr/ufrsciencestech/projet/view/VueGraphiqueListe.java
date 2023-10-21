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
import java.beans.PropertyChangeEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;


public class VueGraphiqueListe extends JFrame implements VueG{
    private JButton inc;
    private JButton dec;
    private JLabel affiche;
    
    private final JPanel jPanel1;
    private final JComboBox<FruitFactory> jComboBox;
    private final JTextArea jTextArea;

    private Modele model;

    private Panier p;
    
    public VueGraphiqueListe() throws PanierPleinException {
    super("Panier Swing");

    FabriqueFruit fruit = new FabriqueFruit();
    Fruit ananas = fruit.creerFruit("ananas",20,"usa");
    Fruit banane = fruit.creerFruit("banane",20,"france");
    Fruit cerise = fruit.creerFruit("cerise",20,"usa");
    Fruit fraise = fruit.creerFruit("fraise",20,"england");
    Fruit kiwi = fruit.creerFruit("kiwi",20,"usa");
    Fruit orange = fruit.creerFruit("orange",20,"italie");

    // fruits dans le panier
        p.ajout(ananas);
        p.ajout(banane);
        p.ajout(cerise);
        p.ajout(fraise);

        // Création du menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Fruit");
        JMenuItem menuItem = new JMenuItem("Ajouter un Fruit");
        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
      

    inc = new JButton("+");
    dec = new JButton("-");

    jPanel1 = new JPanel();
   
    JPanel textAreaPanel = new JPanel();
    jComboBox = new JComboBox<Fruit>(fruits);
    jTextArea = new JTextArea(18, 30);
    JScrollPane scrollPane = new JScrollPane(jTextArea);

    affiche = new JLabel("0", JLabel.CENTER);

    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    // Ligne 1 : JComboBox et JButton dans jPanel1
    jPanel1.setLayout(new GridBagLayout());
    gbc.gridx = 0;
    gbc.gridy = 0;
    jPanel1.add(jComboBox, gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    jPanel1.add(inc, gbc);

    // Ligne 2 : jPanel1 en haut
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1.0;
    gbc.weighty = 0.0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    add(jPanel1, gbc);

    // Ligne 3 : JScrollPane avec JTextArea
    textAreaPanel.setLayout(new BorderLayout());
    textAreaPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
    
    textAreaPanel.add(scrollPane, BorderLayout.CENTER);
    textAreaPanel.add(affiche, BorderLayout.EAST);
    
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;
    add(textAreaPanel, gbc);
    
     // Ligne 4 : JButton "Moins"
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weightx = 1.0;
    gbc.weighty = 0.0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    add(dec, gbc);

    inc.setName("Plus");
    dec.setName("Moins");
    jComboBox.setName("ComboBox");
    affiche.setName("Affichage");
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
        getjComboBox().setActionCommand("ComboBox");
        getjComboBox().addActionListener(c);
    }

    @Override
    public void update(Observable m, Object compteur) {
        // Vérifie si l'objet observé est du bon type (ici, Model)
        if (m instanceof Modele) {
            Modele model = (Modele) m;
            // Vérifie si le compteur est du bon type (ici, Integer)
            if (compteur instanceof Integer) {
                int newCounterValue = (Integer) compteur;
                model.setCompteur(newCounterValue);
                // Par exemple, si tu as un composant texte nommé getAffiche() dans ta vue :
              //  getAffiche().setText(Integer.toString(newCounterValue));
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        System.out.println("propertyChange method called!");  // Ajoutez cette ligne
        if ("compteur".equals(evt.getPropertyName())) {
            Modele m = (Modele) evt.getSource();
            getAffiche().setText(((Integer)m.getCompteur()).toString());
            jTextArea.setText(m.getPanier().toString());
        }else if ("ajoutfruit".equals(evt.getPropertyName())) {
            // Incrémentez le compteur ici
            int newCounterValue = model.getCompteur() + 1;
            model.setCompteur(newCounterValue);
        }else if ("retraitfruit".equals(evt.getPropertyName())) {
            // Décrémentez le compteur ici
            int newCounterValue = model.getCompteur() + 1;
            model.setCompteur(newCounterValue);
        }
    }
         
     public JComboBox getjComboBox() {
        return jComboBox;
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
