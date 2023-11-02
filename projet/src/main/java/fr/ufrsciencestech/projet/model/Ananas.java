package fr.ufrsciencestech.projet.model;

/**
 * Ananas est un classe qui implement Fruit et qui nous sert d'objet afin de remplir un panier.
 * Ananas est composeé d'un attribut Prix et d'un attribut Origine
 * @author TD2 Groupe 11
 */
public class Ananas implements Fruit{
    private double prix;
    private String origine;

    /**
     * Constructeur de la classe Ananas qui initialise le prix à 0.5€ et l'origine à Espagne
     */
    public Ananas() 
    {
        this.prix = 0.5;  //prix en euros
        this.origine="Espagne";
    }
    
    /**
     * Constructeur de la classe Ananas qui crée un ananas avec les parametres données par l'utilisateur
     * @param prix Le prix de l'ananas
     * @param origine L'origine de l'ananas
     */
    public Ananas(double prix, String origine) 
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
     * Methode getPrix qui retourne le prix de l'ananas
     */
    @Override
    public double getPrix(){
	return prix;
    }

    /**
     * Methode setPrix qui definie le prix de l'ananas par un prix en parametre 
     */
    @Override
    public void setPrix(double prix){
	this.prix=prix;
    }
    
    /**
     * Methode getOrigine qui retourne l'origine de l'ananas
     */
    @Override
    public String getOrigine(){
	return origine;
    }

    /**
     * Methode setOrigine qui definie le pays d'origine de l'ananas par un prix en parametre 
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
        return "Ananas de " + origine + " a " + prix + " €";
    }
    
    
    /**
     * Methode equals qui compare l'ananas courant a un autre objet
     * Ce predicat pour tester si deux Ananas sont equivalentes
     */
    @Override
    public boolean equals(Object o){
        if(o != null && getClass() == o.getClass()){
            Ananas an = (Ananas) o;
            return (prix == an.prix && origine.equals(an.origine));
        }
        return false;
    }
    
    /**
     * Methode/predicat isSeedless indiquant s'il y a des pepins
     */
    @Override
    public boolean isSeedless() {
        return true;
    }

}

