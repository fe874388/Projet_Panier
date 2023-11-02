package fr.ufrsciencestech.projet.model;
/**
 * Cerise est un classe qui implement Fruit et qui nous sert d'objet afin de remplir un panier.
 * Cerise est composeé d'un attribut Prix et d'un attribut Origine
 * @author TD2 Groupe 11
 */

public class Cerise implements Fruit{
    private double prix;
    private String origine;
    
    /**
     * Constructeur de la classe Cerise qui initialise le prix à 0.6€ et l'origine à Italie
     */
    public Cerise() 
    {
        this.prix = 0.6;  //prix en euros
        this.origine="Italie";
    }
    
    /**
     * Constructeur de la classe Cerise qui crée une cerise avec les parametres données par l'utilisateur
     * @param prix Le prix de la cerise
     * @param origine L'origine de la cerise
     */
    public Cerise(double prix, String origine) 
    {
	if(prix < 0)
	    this.prix = -prix;  //une solution possible pour interdire les prix negatifs
	else
	    this.prix = prix;

	if(origine.equals("") || origine.isEmpty())
            this.origine = "Italie";  //Italie par défaut
	else
            this.origine = origine;   
    }
    
    /**
     * Methode getPrix qui retourne le prix de la cerise
     */
    @Override
    public double getPrix(){
	return prix;
    }
    
    /**
     * Methode setPrix qui definie le prix de la cerise par un prix en parametre 
     */
    @Override
    public void setPrix(double prix){
    	if(prix >= 0)
		this.prix=prix;
	else 
		System.out.println("Il n'est pas possible de donner un prix négatif");
    }
    
    /**
     * Methode getOrigine qui retourne l'origine de la cerise
     */
    @Override
    public String getOrigine(){
	return origine;
    }
    
    /**
     * Methode setOrigine qui definie le pays d'origine de la cerise par un prix en parametre 
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
        return "Cerise de " + origine + " a " + prix + " €";
    }
    
    /**
     * Methode equals qui compare la cerise courant a un autre objet
     * Ce predicat pour tester si deux Cerises sont equivalentes
     */
    @Override
    public boolean equals(Object o){
        if(o != null && getClass() == o.getClass()){
            Cerise cer = (Cerise) o;
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

