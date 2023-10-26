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
import java.util.ArrayList;
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
    private JComboBox jComboBox;
    private JTextArea jTextArea;
    private JScrollPane scrollPane;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenu menuPanier;
    private JMenuItem menuItem;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;
    
    private JMenu menuItemType;
    private JMenuItem menuItemRAZp;
    private JMenuItem menuItemRAZc;

    private JMenuItem menuItemtype1;
    private JMenuItem menuItemtype2;
    private JMenuItem menuItemtype3;
    private int option=0;
    
    private Controleur controleur;

    public VueGraphiqueListe() throws PanierPleinException {
        super("Panier Swing");

        FabriqueFruit fruit = new FabriqueFruit();
        Fruit orange = fruit.creerFruit("orange",0.99,"Espagne");
        Fruit ananas = fruit.creerFruit("ananas",2.0,"USA");
        Fruit banane = fruit.creerFruit("banane",1.0,"Inde");
        Fruit cerise = fruit.creerFruit("cerise",2.2,"France");
        Fruit fraise = fruit.creerFruit("fraise",1.99,"France");
        Fruit kiwi = fruit.creerFruit("kiwi",1.5,"Chine");
        
        Fruit ma = new Jus(orange,banane);
        Fruit[] fruits={orange,ananas,banane,cerise,fraise,kiwi,ma};
        DefaultComboBoxModel<Fruit> modelCombo = new DefaultComboBoxModel<>(fruits);
        
        this.jComboBox = new JComboBox<>(modelCombo);
        
        // Création du menu bar
        menuBar = new JMenuBar();
        menuPanier = new JMenu("Panier");
        menu = new JMenu("Fruit");
        menuItem = new JMenuItem("Ajouter Fruit");
        menuItem4 = new JMenuItem("Ajouter Jus/Macedoine");
        menuItem2 = new JMenuItem("Supprimer Fruit/Jus/Macedoine");
        menuItem3 = new JMenuItem("Boycotter un Pays");
        
        menuItemType = new JMenu("Modifier le type du Panier");
        menuItemRAZp = new JMenuItem("Reinitialiser le Panier");
        menuItemRAZc = new JMenuItem("Reinitialiser le Catalogue");
        
        menuItemtype1 = new JMenuItem("Panier à moins de 2€/fruit");
        menuItemtype2 = new JMenuItem("Fruits locaux (France)");
        menuItemtype3 = new JMenuItem("Fruit seulement");
        
        menuPanier.add(menuItemType);
        menuItemType.add(menuItemtype1);
        menuItemType.add(menuItemtype2);
        menuItemType.add(menuItemtype3);
        menuPanier.add(menuItemRAZp);
        menuPanier.add(menuItemRAZc);
                
                
        menu.add(menuItem);
        menu.add(menuItem4);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menuBar.add(menuPanier);
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
        
        this.jComboBox.setSelectedIndex(0);
        jPanel1.add(jComboBox);
        jPanel1.add(inc);
        jPanel1.add(dec);
        
        add(jPanel1, BorderLayout.NORTH);
        add(jPanel2, BorderLayout.CENTER);
        
        inc.setName("Plus");
        dec.setName("Moins");
        this.jComboBox.setName("ComboBox");
        affiche.setName("Affichage");
        jTextArea.setText("Liste des fruit(s) dans mon Panier :\n");
        menuItem.setName("menuItemAjoutFruit");
        
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AjoutFruit ajoutFruitDialog = new AjoutFruit(VueGraphiqueListe.this);
                
                if(option==1){Option1();}
                if(option==2){Option2();}
                if(option==3){Option3();}
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
            public void actionPerformed(ActionEvent e) {
                AjoutJusMacedoine AjoutJusMacedoineDialog = new AjoutJusMacedoine(VueGraphiqueListe.this);  
                if(option==1){Option1();}
                if(option==2){Option2();}
                if(option==3){Option3();}
            }
        });
        
        menuItemRAZc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultComboBoxModel<Fruit> modelCombo = new DefaultComboBoxModel<>(fruits);
                getjComboBox().setModel(modelCombo);
            }
        });
        
        menuItemtype1.addActionListener(new ActionListener() {                  //Panier à moins de 2€/fruit
            @Override
            public void actionPerformed(ActionEvent e) {
                option=1;
                Option1();
            }
        });
        
        menuItemtype2.addActionListener(new ActionListener() {                   //Fruits locaux (France)
            @Override
            public void actionPerformed(ActionEvent e) {
                option=2;
                Option2();
            }
        });
        
        menuItemtype3.addActionListener(new ActionListener() {                      //Fruit seulement
            @Override
            public void actionPerformed(ActionEvent e) {
                option=3;
                Option3();
            }
        });
                        
                
    
        this.pack();
        this.setSize(1000, 800);
        
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getWidth()/2, dim.height/2 - this.getWidth()/2);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
    
    public void addControleur(Controleur c){
        getInc().addActionListener(c);
        getDec().addActionListener(c);
        getjComboBox().addActionListener(c);
        getmenuItemRAZPanier().setActionCommand("RAZP");
        getmenuItemRAZPanier().addActionListener(c);
    }


    @Override
    public void update(Observable m, Object compteur) {
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
        }
    }

    public void ajouterFruit(Fruit f) {
        DefaultComboBoxModel<Fruit> models = (DefaultComboBoxModel<Fruit>) getjComboBox().getModel();
        models.addElement(f);
        repaint();
        revalidate();
        //jComboBox.addItem(f);
    }
    
    public void supprimerFruit(Fruit elementSelectionne) {
        DefaultComboBoxModel<Fruit> models = (DefaultComboBoxModel<Fruit>) getjComboBox().getModel();
        models.removeElement(elementSelectionne);
        repaint();
        revalidate();
    }
    
    public void Boycottepays(String elementSelectionne) {
        DefaultComboBoxModel<Fruit> models = (DefaultComboBoxModel<Fruit>) getjComboBox().getModel();
        for (int i=0; i<models.getSize(); i++){
            if(models.getElementAt(i).getOrigine().toLowerCase().equals(elementSelectionne.toLowerCase())){
                models.removeElementAt(i);
                i--;
            }
        }
        if(models.getSize()>0)
            getjComboBox().setSelectedIndex(0);
        repaint();
        revalidate();
    }
    public void ajouterJusMacedoine(Fruit f) {
        DefaultComboBoxModel<Fruit> models = (DefaultComboBoxModel<Fruit>) getjComboBox().getModel();
        models.addElement(f);
        repaint();
        revalidate();
        //getjComboBox().setSelectedItem(jm);
        //jComboBox.addItem(f);
        //jComboBox.setModel(mode);
    }

        
    public JMenuItem getjmenuItem() {
        return this.menuItem;
    }
    
    public JMenuItem getmenuItemRAZPanier() {
        return this.menuItemRAZp;
    }
        
    public JComboBox getjComboBox() {
        return this.jComboBox;
    }
    public JTextArea getjTextArea() {
        return this.jTextArea;
    }
    
    public void Option1() {
        DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) getjComboBox().getModel();
        DefaultComboBoxModel<Fruit> modeleListeFruit = new DefaultComboBoxModel<>();
        
        for (int i = 0; i < model.getSize(); i++) {
            if(model.getElementAt(i).getPrix()<2.0){
                modeleListeFruit.addElement(model.getElementAt(i));
            }
        }
        getjComboBox().setModel(modeleListeFruit);
        repaint();
        revalidate();
    }
    
    public void Option2() {
        DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) getjComboBox().getModel();
        DefaultComboBoxModel<Fruit> modeleListeFruit = new DefaultComboBoxModel<>();
        
        for (int i = 0; i < model.getSize(); i++) {
            if(model.getElementAt(i).getOrigine().toLowerCase().equals("france")){
                modeleListeFruit.addElement(model.getElementAt(i)); // Ajoutez le fruit au modèle de fruits uniques
            }
        }
        getjComboBox().setModel(modeleListeFruit);
        repaint();
        revalidate();
    }
    
    public void Option3() {
        DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) getjComboBox().getModel();
        DefaultComboBoxModel<Fruit> modeleListeFruit = new DefaultComboBoxModel<>();
        for (int i=0; i<model.getSize(); i++){
            if(!(model.getElementAt(i).getClass().getSimpleName().equals("Macedoine") || model.getElementAt(i).getClass().getSimpleName().equals("Jus"))){
                modeleListeFruit.addElement(model.getElementAt(i));
            }
        }
        getjComboBox().setModel(modeleListeFruit);
        repaint();
        revalidate();
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
