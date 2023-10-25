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
    private Fruit currentFruit;
    private Panier p = new Panier(20);

    public Controleur(Modele model, VueGraphiqueListe vue) {
        this.model = model;
        this.vue = vue;
        model.addPropertyChangeListener(vue);
    }

    @Override
    public void actionPerformed(ActionEvent e){   //Invoked when an action occurs
        if ("ComboBox".equals(((Component) e.getSource()).getName())){
            currentFruit = (Fruit) vue.getjComboBox().getSelectedItem();
            System.out.println("**--- Changement dans la  ComboBox Fruit Courant : " + currentFruit + " ---**");
        }
        if("Plus".equals(((Component)e.getSource()).getName())) {
            try {
                p.ajout(currentFruit);
                model.update(1);
               // vue.ajouterFruit(currentFruit);
            } catch (PanierPleinException ex) {
                throw new RuntimeException(ex);
            }
        }
        if("Moins".equals(((Component)e.getSource()).getName())) {
            try {
                p.retrait();
                model.update(-1);
            } catch (PanierVideException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void setPanier(Panier pa){
        this.p = pa;
    }

    public Panier getPanier(){
        return this.p;
    }
    
    public void setCurrentFruit(Fruit c){
        this.currentFruit = c;
    }
    public Fruit getCurrentFruit(){
        return currentFruit;
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

