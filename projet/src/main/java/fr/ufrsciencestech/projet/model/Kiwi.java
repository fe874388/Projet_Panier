package fr.ufrsciencestech.projet.model;
/**
 * Kiwi est un classe qui implement Fruit et qui nous sert d'objet afin de remplir un panier.
 * Kiwi est composeé d'un attribut Prix et d'un attribut Origine
 * @author TD2 Groupe 11
 */

public class Kiwi implements Fruit{
    private double prix;
    private String origine;
    
    /**
     * Constructeur de la classe Kiwi qui initialise le prix à 0.5€ et l'origine à Espagne
     */
    public Kiwi() 
    {
        this.prix = 0.5;  //prix en euros
        this.origine="Espagne";
    }
    
    /**
     * Constructeur de la classe Kiwi qui crée une kiwi avec les parametres données par l'utilisateur
     * @param prix Le prix de du kiwi
     * @param origine L'origine du kiwi
     */
    public Kiwi(double prix, String origine) 
    {
	if(prix < 0)
	    this.prix = -prix;  //une solution possible pour interdire les prix negatifs
	else
	    this.prix = prix;

	if(origine.equals("") || origine.isEmpty())
            this.origine = "Espagne";  //Espagne par défaut
	else
            this.origine = origine;   
    }
    
    /**
     * Methode getPrix qui retourne le prix du kiwi
     */
    @Override
    public double getPrix(){
	return prix;
    }
    
    /**
     * Methode setPrix qui definie le prix du kiwi par un prix en parametre 
     */
    @Override
    public void setPrix(double prix){
    	if(prix >= 0)
		this.prix=prix;
	else 
		System.out.println("Il n'est pas possible de donner un prix négatif");
    }
    
    /**
     * Methode getOrigine qui retourne l'origine du kiwi
     */
    @Override
    public String getOrigine(){
	return origine;
    }
    
    /**
     * Methode setOrigine qui definie le pays d'origine du kiwi par un prix en parametre 
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
        return "Kiwi de " + origine + " a " + prix + " €";
    }
    
    /**
     * Methode equals qui compare le kiwi courant a un autre objet
     * Ce predicat pour tester si deux Kiwis sont equivalents
     */
    @Override
    public boolean equals(Object o){
        if(o != null && getClass() == o.getClass()){
            Kiwi cer = (Kiwi) o;
            return (prix == cer.prix && origine.equals(cer.origine));
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

