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

    public Controleur(Modele model, VueGraphiqueListe vue) throws PanierPleinException {
        this.model = model;
        this.vue = vue;
        model.addPropertyChangeListener(vue);
    }

    @Override
    public void actionPerformed(ActionEvent e){   //Invoked when an action occurs
        currentFruit = (Fruit) vue.getjComboBox().getSelectedItem();
        if ("ComboBox".equals(((Component) e.getSource()).getName())){
            currentFruit = (Fruit) vue.getjComboBox().getSelectedItem();
            System.out.println("**--- Changement dans la  ComboBox Fruit Courant : " + currentFruit + " ---**");
        }
        if("Plus".equals(((Component)e.getSource()).getName())) {
            try {
                this.p.ajout(currentFruit);
                vue.getjTextArea().setText("Liste des fruit(s) dans mon Panier :\n"+p.toString());
                model.update(1);
            } catch (PanierPleinException ex) {
                throw new RuntimeException(ex);
            }
        }
        if("Moins".equals(((Component)e.getSource()).getName())) {
            try {
                this.p.retrait();
                vue.getjTextArea().setText("Liste des fruit(s) dans mon Panier :\n"+p.toString());
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

