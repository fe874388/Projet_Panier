/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.ufrsciencestech.projet.model;

/**
 *
 * @author idira
 */
public class OrangeFactory implements FruitFactory{
    @Override
    public Fruit creerFruit() {
        return new Orange();
    }
    @Override
    public Fruit creerFruit(double prix, String origine) {
        Fruit fruit = new Orange();
        fruit.setPrix(prix);
        fruit.setOrigine(origine);
        return fruit;
    }
}
