package fr.ufrsciencestech.projet.model;

import java.util.ArrayList;

/**
 * Jus est un classe qui implement Fruit et qui nous sert d'objet afin de remplir un panier.
 * Jus est composeé d'un attribut Prix et d'une ArrayList de Fruit
 * @author TD2 Groupe 11
 */
public class Jus implements Fruit{
    private double prix;
    private ArrayList<Fruit> fruits;
    
    /**
     * Constructeur Jus 
     * Initialise ArrayList de Fruit et le prix a 0
     */
    public Jus() 
    {
        this.fruits = new ArrayList<Fruit>();
        this.prix = 0;
    }
    
    /** 
     * Constructeur Jus avec un parametre
     * Initialise ArrayList de Fruit et ajoute le fruit en parametre a l'arrayList
     * @param f Le fruit à ajouter
     */
    public Jus(Fruit f) 
    {
        this.fruits = new ArrayList<Fruit>();
        this.fruits.add(f);
    }
    
    /** 
     * Constructeur Jus avec deux parametre
     * Initialise ArrayList de Fruit et ajoute les deux fruits en parametre a l'arrayList
     * @param f1 Le 1er fruit à ajouter
     * @param f2 Le second fruit à ajouter
     */
    public Jus(Fruit f1,Fruit f2) 
    {
        this.fruits = new ArrayList<Fruit>();
        this.prix = 0;
        this.fruits.add(f1);
        this.fruits.add(f2);
    }
    
    /** 
     * Methode ajoute(Fruit f) 
     * Permet d'ajouter le fruit en parametre a l'arrayList
     * @param f Le fruit à ajouter
     */
    public void ajoute(Fruit f) 
    {
        this.fruits.add(f);
    }
    
     /** 
     * Methode getPrix()
     * Permet de calculer le prix du Jus grace a tout les fruits qui le compose (ArrayList) et de le renvoyé 
     */
    @Override
    public double getPrix(){
        prix=0.0;
        for(int i=0; i<fruits.size();i++){
            prix=prix+fruits.get(i).getPrix();
        }
	return prix;
    }

    /** 
     * Methode setPrix(double prix)
     * Permet de remplacer l'ancien prix du Jus par un nouveau prix en parametre
     */
    @Override
    public void setPrix(double prix){
	this.prix=prix;
    }
    
    /** 
     * Methode getFruits()
     * @return Renvoie la liste de tout les fruits qui le compose (ArrayList)
     */
    public ArrayList<Fruit> getFruits(){
	return fruits;
    }

    /** 
     * Methode setFruits(ArrayList(Fruit) f)
     * Remplace la liste de fruit qui compose le Jus par une autre liste en parametre
     * @param f est une ArrayList de Fruit 
     */
    public void setFruits(ArrayList<Fruit> f){
	this.fruits=f;
    }
    
    /** 
     * Methode getOrigine()
     * @deprecated Etant donnée que les fruit on des provenance differente, cette methode renvoie une chaine vide
     */
    @Override
    public String getOrigine(){
	return "";
    }
    
    /** 
     * setOrigine(String origine)
     * @deprecated Etant donnée que les fruit on des provenance differente, cette methode est inutile. L'origine est une chaine vide
     */
    @Override
    public void setOrigine(String origine){
    }
    
    /**
     * Methode toString qui affiche le Jus, les fruits dont il est composé ainsi que son prix
     */
    @Override
    public String toString() {
    String Noms="";
    for (int i=0; i<fruits.size(); i++) {
        if(i>0){
            Noms+=", ";
        }
        Noms+=fruits.get(i).getClass().getSimpleName();
    }
    return "Jus de (" + Noms + ") a " + getPrix() + " €";
    }
    
    /**
     * Methode equals qui compare le Jus courant a un autre objet
     * Ce predicat pour tester si deux Jus sont equivalents
     */
    @Override
    public boolean equals(Object o){  //predicat pour tester si 2 Jus sont equivalentes
        if(o != null && getClass() == o.getClass()){
            Jus or = (Jus) o;
            return (prix == or.prix && fruits.equals(or.fruits));
        }
        return false;
    }
    
    /**
     * Methode/predicat isSeedless indiquant s'il y a des pepins
     * Un jus est liquide donc ne contient pas de pepin
     */
    @Override
    public boolean isSeedless() {  //predicat indiquant qu'un fruit a des pepins
        return true;
    }

}
