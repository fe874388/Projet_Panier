/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.projet.controler;

import fr.ufrsciencestech.projet.view.*;
import fr.ufrsciencestech.projet.model.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controleur implements ActionListener {
    private Modele model;
    private VueGraphiqueListe vue;
    private Fruit currentFruit;
    private Panier p = new Panier(20);

    public Controleur(Modele model, VueGraphiqueListe vue){
        this.model = model;
        this.vue = vue;
        model.addPropertyChangeListener(vue);
        currentFruit = (Fruit) vue.getjComboBox().getSelectedItem();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){   //Invoked when an action occurs
        if (e.getActionCommand().equals("RAZP")){
            this.p=new Panier(20);
            model.update(2);
            vue.getjTextArea().setText("Liste des fruit(s) dans mon Panier :\n"+p.toString());
            vue.getAffiche().setText("0");
        }else if (((Component) e.getSource()).getName().equals("ComboBox")){
            currentFruit = (Fruit) vue.getjComboBox().getSelectedItem();
            System.out.println("**- CHANGEMENT DANS LA COMBOBOX Fruit Courant : " + currentFruit + " -**");
        }
        else if(((Component)e.getSource()).getName().equals("Plus")) {
            try {
                p.ajout(currentFruit);
                model.update(1);
                vue.getjTextArea().setText("Liste des fruit(s) dans mon Panier :\n"+p.toString());
            } catch (PanierPleinException ex) {
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(((Component)e.getSource()).getName().equals("Moins"))
            try {
                p.retrait();
                model.update(-1);
                vue.getjTextArea().setText("Liste des fruit(s) dans mon Panier :\n"+p.toString());
        } catch (PanierVideException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }    	
        
        //System.out.println("trigger :->  "+((Component)e.getSource()));
        //if(((Component)e.getSource()).getName()){
        //    this.p=new Panier(20);
        // }
    }
    
    
    public void setPanier(Panier pa){
        this.p = pa;
    }

    public Panier getPanier(){
        return this.p;
    }
    
    public void setcurrentFruit(Fruit c){
        this.currentFruit = c;
    }
    public Fruit getcurrentFruit(){
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
    public static JTextArea findJTextAreaByName(Container container, String name) {
        for (Component component : container.getComponents()) {
            if (component instanceof JTextArea && component.getName() != null &&
                    component.getName().equals(name)) {
                return (JTextArea) component;
            } else if (component instanceof Container) {
                JTextArea textArea = findJTextAreaByName((Container) component, name);
                if (textArea != null) {
                    return textArea;
                }
            }
        }
        return null;
    }
    
    }

