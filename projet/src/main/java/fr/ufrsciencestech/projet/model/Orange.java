package fr.ufrsciencestech.projet.model;

/**
 * Orange est un classe qui implement Fruit et qui nous sert d'objet afin de remplir un panier.
 * Orange est composeé d'un attribut Prix et d'un attribut Origine
 * @author TD2 Groupe 11
 */
public class Orange implements Fruit{
    private double prix;
    private String origine;

    /**
     * Constructeur de la classe Orange qui initialise le prix à 0.5€ et l'origine à Espagne
     */
    public Orange() 
    {
        this.prix = 0.5;  //prix en euros
        this.origine="Espagne";
    }
    
    /**
     * Constructeur de la classe Orange qui crée un orange avec les parametres données par l'utilisateur
     * @param prix Le prix de l'orange
     * @param origine L'origine de l'orange
     */
    public Orange(double prix, String origine) 
    {
	if(prix < 0)
	    this.prix = -prix;  //une solution possible pour interdire les prix negatifs
	else
	    this.prix = prix;

	if(origine.equals(""))
            this.origine = "Espagne";  //Espagne par défaut
	else
            this.origine = origine;   
    }
    
    /**
     * Methode getPrix qui retourne le prix de l'orange
     */
    @Override
    public double getPrix(){
	return prix;
    }

    /**
     * Methode setPrix qui definie le prix de l'orange par un prix en parametre 
     */
    @Override
    public void setPrix(double prix){
	this.prix=prix;
    }
    
    /**
     * Methode getOrigine qui retourne l'origine de l'orange
     */
    @Override
    public String getOrigine(){
	return origine;
    }

    /**
     * Methode setOrigine qui definie le pays d'origine de l'orange par un prix en parametre 
     */
    @Override
    public void setOrigine(String origine){
	this.origine=origine;
    }
    
    /**
     * Methode toString qui affiche le fruit et ses attributs origine et prix
     */
    @Override
    public String toString(){
        return "Orange de " + origine + " a " + prix + " €";
    }
    
    
    /**
     * Methode equals qui compare l'orange courant a un autre objet
     * Ce predicat pour tester si deux Oranges sont equivalentes
     */
    @Override
    public boolean equals(Object o){
        if(o != null && getClass() == o.getClass()){
            Orange an = (Orange) o;
            return (prix == an.prix && origine.equals(an.origine));
        }
        return false;
    }
    
    /**
     * Methode/predicat isSeedless indiquant s'il y a des pepins
     */
    @Override
    public boolean isSeedless() {
        return false;
    }

}

