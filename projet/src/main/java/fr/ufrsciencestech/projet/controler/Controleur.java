/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.projet.controler;

import fr.ufrsciencestech.projet.view.*;
import fr.ufrsciencestech.projet.model.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controleur implements ActionListener {
    private Modele model;
    private VueGraphiqueListe vue;
    private Fruit CurrentFruit;
    private Panier p= new Panier(20);



    public Controleur(Modele model, VueGraphiqueListe vue) {
        this.model = model;
        this.vue = vue;
        this.model.addPropertyChangeListener(vue);
    }

    @Override
    public void actionPerformed(ActionEvent e){   //Invoked when an action occurs
        if (((Component) e.getSource()).getName().equals("ComboBox")){
            CurrentFruit = (Fruit) vue.getjComboBox().getSelectedItem();
            System.out.println("**- CHANGEMENT DANS LA COMBOBOX Fruit Courant : " + CurrentFruit + " -**");
        }
        if(((Component)e.getSource()).getName().equals("Plus")) {
            vue.getjComboBox().addItem(CurrentFruit);
            vue.update(model,model.getCompteur());
    }
        if(((Component)e.getSource()).getName().equals("Moins"))
            try {
                p.retrait();
                vue.update(model,model.getCompteur());
        } catch (PanierVideException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setPanier(Panier pa){
        this.p = pa;
    }
    
    public void setCurrentFruit(Fruit c){
        this.CurrentFruit = c;
    }
    public Fruit getCurrentFruit(){
        return CurrentFruit;
    }
    public void setModele(Modele m)
    {
        this.model = m;
    }
    public void setVue(VueGraphiqueListe vg)
    {
        this.vue = vg;
    }
    }

