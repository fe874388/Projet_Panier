/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.panier.model;

//import java.util.Observable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 *
 * @author celine
 */
public class Modele {//extends Observable{
    private int compteur;   //compteur toujours positif
    private ArrayList<Panier> paniers = new ArrayList<Panier>();
    
    PropertyChangeSupport pcs = new  PropertyChangeSupport(this);
	
    public void addObserver(PropertyChangeListener l) {
	pcs.addPropertyChangeListener("value", l);
    }
    
    public Modele(){
        compteur = 0;
        Panier p=new Panier(10);
        paniers.add(p);
    }
    
    public void update(int incr) {
        int old = this.compteur;
        compteur += incr;
        if(compteur < 0)
            compteur = 0;
        
        pcs.firePropertyChange("value", old, this.compteur);
        //setChanged();                //marks this Observable object as having been changed; the hasChanged method will now return true
        //notifyObservers(getCompteur());   //if this object has changed, as indicated by the hasChanged method, then notify all of its observers and then call the clearChanged method to indicate that this object has no longer changed
    }

    /**
     * @return the compteur
     */
    public int getCompteur() {
        return compteur;
    }

    /**
     * @param compteur the compteur to set
     */
    public void setCompteur(int compteur) {
        Orange o1 = new Orange();
        int old = this.compteur;
        this.compteur = compteur;
        if(this.compteur < 0)
            this.compteur = 0;
        else{
            paniers.get(0).ajout(o1);
            System.out.println(paniers.get(0).toString());
        }
        pcs.firePropertyChange("value", old, this.compteur);
        //setChanged();                //marks this Observable object as having been changed; the hasChanged method will now return true
        //notifyObservers(getCompteur());   //if this object has changed, as indicated by the hasChanged method, then notify all of its observers and then call the clearChanged method to indicate that this object has no longer changed
    }
    
    public ArrayList<Panier> getPaniers() {
        return paniers;
    }
        
}
