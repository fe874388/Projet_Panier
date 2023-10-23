/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.projet.view;
import fr.ufrsciencestech.projet.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjoutFruit extends JDialog {
    private JTextArea nomField, prixField, paysField;
    private Fruit fruit;
    private VueGraphiqueListe parent;

    
    public AjoutFruit(final VueGraphiqueListe parent) {
        super(parent, "Ajouter/Créer un fruit", true);
        this.parent = parent;
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Nom du fruit:"));
        nomField = new JTextArea();
        add(nomField);

        add(new JLabel("Prix du fruit:"));
        prixField = new JTextArea();
        add(prixField);

        add(new JLabel("Pays de provenance:"));
        paysField = new JTextArea();
        add(paysField);
        
        JButton confirmerButton = new JButton("Confirmer");
        confirmerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                String nom = nomField.getText();
                double prix = Double.parseDouble(prixField.getText());
                String pays = paysField.getText();
                Fruit fruit;
                switch(nom.toLowerCase()) {
                case "orange":
                    fruit = new Orange(prix, pays);
                    parent.ajouterFruit(fruit);
                    break;
                case "ananas":
                    fruit = new Ananas(prix, pays);
                    parent.ajouterFruit(fruit);
                    break;
                case "banane":
                    fruit = new Banane(prix, pays);
                    parent.ajouterFruit(fruit);
                    break;
                case "cerise":
                    fruit = new Cerise(prix, pays);
                    parent.ajouterFruit(fruit);
                    break;
                case "kiwi":
                    fruit = new Kiwi(prix, pays);
                    parent.ajouterFruit(fruit);
                    break;
                case "fraise":
                    fruit = new Fraise(prix, pays);
                    parent.ajouterFruit(fruit);
                    break;
                default:
                    System.out.println("Fruit non disponible");
                    break;
            }
                dispose(); // Ferme la fenêtre de dialogue
            }
        });

        JButton annulerButton = new JButton("Annuler");
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                System.out.println("Ajout fruit Annulé");
                dispose(); // Ferme la fenêtre de dialogue sans créer de fruit
            }
        });

        add(confirmerButton);
        add(annulerButton);
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    public Fruit getfruit() {
        return this.fruit;
    }
    
    public void setfruit(Fruit f) {
        this.fruit=f;
    }
    
}
