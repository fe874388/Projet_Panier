package fr.ufrsciencestech.projet.model;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.beans.PropertyChangeSupport;
import java.util.Observable;

/**
 * Panier est un classe qui herite de Observable qui nous sert de support principal pour ce projet.
 * Panier est composeé d'un attribut contenanceMax, d'une ArrayList de Fruit, d'un PropertyChangeSupport et d'un modele
 * Les attributs ArrayList de Fruit et contenanceMax nous servirons pour manipuler le panier
 * Les attributs Modele et PropertyChangeSupport nous servirons respecter l'architecture MVC et les Design Pattern
 * @author TD2 Groupe 11
 */
public class Panier extends Observable{
    private ArrayList<Fruit> fruits  = new ArrayList<Fruit>();  //attribut pour stocker les fruits
    private int contenanceMax=10;        //nb maximum de fruits que peut contenir le panier
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private Modele modele;
    
    /**
     * Constructeur de la classe Panier qui initialise le contenanceMax et l'ArrayList de Fruit
    */
     public Panier(){  //initialise un panier vide ayant une contenance maximale de 10
       this.fruits = new ArrayList<Fruit>();
       this.contenanceMax = contenanceMax;
    }
     
    /**
     * Constructeur de la classe Panier avec un paramètre qui initialise une ArrayList de Fruit et la contenanceMax avec un paramètre
     * @param contMax contenanceMax en paramètre
    */
    public Panier(int contMax){  //initialise un panier vide ayant une certaine contenance maximale (precisee en paramètre)
       this.fruits = new ArrayList<Fruit>();
       this.contenanceMax = contMax;
    }
    
    /**
     * Methode toString qui affiche le contenue du panier: les noms des fruits et leur attributs (origine et prix)
    */
    @Override
    public String toString() {
        String res = "";
        String newLine = System.getProperty("line.separator");
        for (int i = 0; i < fruits.size(); i++) {
            res += fruits.get(i).toString() + newLine;
        }
        return res;
    }
    
    /** 
     * Methode getFruits()
     * @return Renvoie la liste de tout les fruits qui le compose (ArrayList)
     */
    public ArrayList<Fruit> getFruits() {
       return fruits;
    }
    
    /** 
     * Methode setFruits(ArrayList(Fruit) fruits)
     * Remplace la liste de fruit qui compose le Panier par une autre liste en paramètre
     * @param fruits est une ArrayList de fruits
     */
    public void setFruits(ArrayList<Fruit> fruits) { 
      this.fruits = fruits;
    }
    
    /** 
     * Methode getTaillePanier()
     * Renvoie la taille de l'ArrayList de fruit contenant les fruits
     * @return Renvoie la taille du Panier
     */
    public int getTaillePanier(){
        return fruits.size();
    }
    
    /** 
     * Methode getTaillePanier() renvoie la contenanceMax du panier
     * @return Renvoie la taille maximum que peut contenir le Panier
     */
    public int getContenanceMax(){
	return contenanceMax;
    }
    
    /** 
     * Methode setContenanceMax(int x) permet de changer la taille maximum(contenanceMax) du panier
     * @param x Soit la nouvelle taille du Panier 
     */
    public void setContenanceMax(int x){
        this.contenanceMax=x;
    }
    
    /** 
     * Methode getFruit(int i) permet de retourner le fruit contenu dans le panier a l'emplacement n°i (ou null s'il n'y a rien a cet emplacement)
     * @param i Soit l'indice du fruit dans le panier
     * @return Fruit Soit le fruit d'indice i dans le panier
     */
    public Fruit getFruit(int i){
	return fruits.get(i);
    }
    
    /** 
     * Methode setFruit(int i, Fruit f) permet de modifier le fruit contenu dans le panier a l'emplacement n°i par f
     * (s'il y a deja un fruit a cet emplacement alors faire la modification, ne rien faire sinon)
     * @param i Soit l'indice du fruit dans le panier
     * @param f Soit le nouveau fruit remplacant l'ancien fruit
     */
    public void setFruit(int i, Fruit f){
        if(fruits.get(i) != null) {
            fruits.set(i, f);
        }
    }
    
    /** 
     * Methode estVide() qui indique si le panier est vide ou non
     * @return boolean True s'il est vide ou False s'il contient au moins un fruit
     */
    public boolean estVide(){
	return fruits.isEmpty();
    }
    
    /** 
     * Methode estPlein() qui indique si le panier est plein ou non
     * @return boolean True s'il est plein ou False s'il n'est pas plein
     */
    public boolean estPlein(){
	return (fruits.size() == contenanceMax);
    }

    /** 
     * Methode ajout() permet d'ajouter une Orange au panier
     * @deprecated Utiliser plutot ajout(Fruit o) qui permet de choisir le fruit à ajouter en paramètre 
     */
    public void ajout() throws PanierPleinException {
        if (fruits.size() < contenanceMax) {
            Fruit fruitAjoute = new Orange(); 
            fruits.add(fruitAjoute);
        } else {
            throw new PanierPleinException();
        }
    }
    
    /** 
     * Methode ajout(Fruit o) permet d'ajouter un fruit au panier si celui-ci n'est pas plein
     * @param o Permet d'ajouter le fruit entré en paramètre au panier 
     */
    public void ajout(Fruit o) throws PanierPleinException{
        if (fruits.size() < contenanceMax) {
            fruits.add(o);
        }
       else {
            System.out.println("le panier est plein");
            throw new PanierPleinException();
        }
    }
    
    /** 
     * Methode retrait() permet de retirer le dernier fruit de la liste si celui-ci n'est pas vide
     */
    public void retrait() throws PanierVideException{
        if(!fruits.isEmpty()){
            int removedIndex = fruits.size() - 1;
            fruits.remove(removedIndex);
        } else{
            System.out.println("le panier est vide");
        } 
    }
    
    /** 
     * Methode retraitFruit(List(Fruit) fruitsRetirer) permet de retirer tout les fruits de la liste en paramètre du panier
     * S'il y a plusieurs fruits identiques alors on retirera qu'une seul itération de celui-ci
     * @param fruitsRetirer La liste des fruit à retirer
     */
    public void retraitFruit(List<Fruit> fruitsRetirer) throws PanierVideException {
        if (!fruits.isEmpty()){
            for (Fruit fruit : fruitsRetirer) {
                if (fruits.contains(fruit)) {
                    fruits.remove(fruit);
                    System.out.println("Le fruit " + fruit.toString() + " a été retiré du panier.");
                } else {
                    System.out.println("Le fruit " + fruit.toString() + " n'est pas dans le panier.");
                }
            }
        }else{
            throw new PanierVideException();
        }
    }
    
    /** 
     * Methode getPrix()
     * Permet de calculer le prix total du Panier grâce à tout les fruits qui le compose (ArrayList) et de le renvoyé
     * @return Le prix du Panier  
     */ 
    public double getPrix() {
        double prixTotal = 0;
        for (Fruit fruit : fruits) {
            prixTotal += fruit.getPrix();
        }
        return prixTotal;
    }
    
    /** 
     * Methode boycotteOrigine(String origine)
     * Permet de retirer tout les fruits du panier dont l'origine correspond au parametre entrer par l'utilisateur
     * @param origine Le pays a boycotter
     */ 
    public void boycotteOrigine(String origine) {
        ArrayList<Fruit> fruitsRetenus = new ArrayList<>();
        for (Fruit fruit : fruits) {
            if (!fruit.getOrigine().equalsIgnoreCase(origine)) {
                fruitsRetenus.add(fruit);
            }
        }
        fruits = fruitsRetenus;
    }
    
    /**
     * Methode equals qui compare le Panier courant a un autre objet
     * Ce predicat sert à tester si deux Panier sont equivalents (s'ils contiennent exactement les memes fruits)
     * @param o L'objet a comparer avec le panier
     * @return boolean Retoune true si les deux Panier sont equivalentes
     */   
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Panier panier = (Panier) o;
        return Objects.equals(fruits, panier.fruits);
    }
}


