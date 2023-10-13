package fr.ufrsciencestech.projet.model;


public class Kiwi implements Fruit {
    private double prix;
    private String origine;
	

    public Kiwi() 
    {
        this.prix = 0.5;  //prix en euros
        this.origine="Espagne";
    }
    
    public Kiwi(double prix, String origine) 
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

    public double getPrix(){
	return prix;
    }

    public void setPrix(double prix){
	this.prix=prix;
    }

    public String getOrigine(){
	return origine;
    }
 
    public void setOrigine(String origine){
	this.origine=origine;
    }

    @Override
    public String toString(){
        return "Kiwi de " + origine + " a " + prix + " euros";
    }

    @Override
    public boolean equals(Object o){  //predicat pour tester si 2 oranges sont equivalentes
        if(o != null && getClass() == o.getClass()){
            Kiwi or = (Kiwi) o;
            return (prix == or.prix && origine.equals(or.origine));
        }
        return false;
    }

    public boolean isSeedless() {  //predicat indiquant qu'une kiwi a des pepins
        return false;
    }

/*
    public static void main (String[] args){
        //Ecrire ici vos tests
        System.out.println("premier test Kiwi");
        Kiwi kiwi = new Kiwi();
        double prix = 12;
        double oldPrice = 0.5;
        boolean isTest = true;
        if (kiwi.getPrix() != oldPrice) 
            isTest = false;
        kiwi.setPrix(prix);
        if (kiwi.getPrix() != prix)
            isTest = false;
        if (isTest) 
            System.out.println("Test prix réussi");
        boolean isTest2 = true;
        isTest2 = kiwi.getOrigine().equals("Espagne");
        kiwi.setOrigine("France");
        isTest2 = kiwi.getOrigine().equals("France");
        if (isTest2)
            System.out.println("Deuxième test réussi");
   }
*/
}
