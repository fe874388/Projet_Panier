/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.ufrsciencestech.projet.model;

/**
 *
 * @author idira
 */
public class CeriseFactory implements FruitFactory{
    @Override
    public Fruit creerFruit() {
        return new Cerise();
    }
    
    @Override
    public Fruit creerFruit(double prix, String origine) {
        Fruit fruit = new Cerise();
        fruit.setPrix(prix);
        fruit.setOrigine(origine);
        return fruit;
    }
}
