/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.projet.model;

//import java.util.Observable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Modele {//extends Observable{
    private int compteur;   //compteur toujours positif
    private Panier panier=new Panier(20);
    private Fruit o;
    
    PropertyChangeSupport pcs = new  PropertyChangeSupport(this);
	
    public void addObserver(PropertyChangeListener l) {
	pcs.addPropertyChangeListener("value", l);
    }
    
    public Modele(){
        compteur = 0;
    }
    public void update(int incr, Panier p) throws PanierPleinException {
        int old = this.compteur;
        compteur += incr;
        if(compteur < 0)
            compteur = 0;
        
        pcs.firePropertyChange("value", old, this.compteur);
        panier.setFruits(p.getFruits());
        
        System.out.println("Un/Une "+p.getFruit(p.getTaillePanier())+" a ete ajoute au Panier");
        //setChanged();                //marks this Observable object as having been changed; the hasChanged method will now return true
        //notifyObservers(getCompteur());   //if this object has changed, as indicated by the hasChanged method, then notify all of its observers and then call the clearChanged method to indicate that this object has no longer changed
    }
    
    public void update1(int incr, Panier p) throws PanierVideException {
        int old = this.compteur;
        compteur += incr;
        if(compteur < 0)
            compteur = 0;
        
        pcs.firePropertyChange("value", old, this.compteur);
        panier.setFruits(p.getFruits());
        System.out.println("Le dernier fruit ajoute a été retier du Panier");
        //setChanged();                //marks this Observable object as having been changed; the hasChanged method will now return true
        //notifyObservers(getCompteur());   //if this object has changed, as indicated by the hasChanged method, then notify all of its observers and then call the clearChanged method to indicate that this object has no longer changed
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
        
    /**
     * @param compteur the compteur to set
     */
    public void setCompteur(int compteur) {
        int old = this.compteur;
        this.compteur = compteur;
        if(this.compteur < 0)
            this.compteur = 0;
        
        pcs.firePropertyChange("value", old, this.compteur);
        //setChanged();                //marks this Observable object as having been changed; the hasChanged method will now return true
        //notifyObservers(getCompteur());   //if this object has changed, as indicated by the hasChanged method, then notify all of its observers and then call the clearChanged method to indicate that this object has no longer changed
    }
    
}
