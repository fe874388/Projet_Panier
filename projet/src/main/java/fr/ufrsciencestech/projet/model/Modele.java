/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.projet.model;

import java.util.Observable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Modele extends Observable{
    private int compteur;   //compteur toujours positif
    private Panier panier=new Panier(20);
    private Fruit o;
    
    PropertyChangeSupport pcs = new  PropertyChangeSupport(this);

    public Modele(){
        compteur = 0;
    }

    /**
     * @return the compteur
     */
    public int getCompteur() {
        return compteur;
    }

    public Panier getPanier() {
        return panier;
    }


    public void setCompteur(int newCounter) {
            int oldCounter = this.compteur;
            this.compteur = newCounter;
            pcs.firePropertyChange("compteur", oldCounter, newCounter);
            setChanged();
            notifyObservers(getCompteur());
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
    
}

