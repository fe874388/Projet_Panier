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
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BoycotterPays extends JDialog {
    private Fruit fruit;
    private VueGraphiqueListe parent;
    private JList<String> jcb;

    
    public BoycotterPays(final VueGraphiqueListe p) {
        super(p, "Boycotter un pays", true);
        this.parent = p;
        
        setLayout(new GridLayout(2, 2));
        
        DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) this.parent.getjComboBox().getModel();
        DefaultComboBoxModel<String> modeleFruitsUniques = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Fruit> modeleListeFruit = new DefaultComboBoxModel<>();
        List<String> originesDejaAjoutees = new ArrayList<>();
        
        for (int i = 0; i < model.getSize(); i++) {
            Fruit fruit = model.getElementAt(i);
            String origine = fruit.getOrigine();
            if(!originesDejaAjoutees.contains(origine)) {
                modeleFruitsUniques.addElement(fruit.getOrigine()); // Ajoutez le fruit au modèle de fruits uniques
                originesDejaAjoutees.add(origine); // Ajoutez l'origine à la liste des origines déjà ajoutées
              //  modeleListeFruit.addElement(fruit);        
            }
        }
        
        this.jcb = new JList<>(modeleFruitsUniques);
        jcb.setSelectedIndex(0);
        
        String selectedOrigine = (String) jcb.getSelectedValue();
        modeleListeFruit.removeAllElements();
        for (int i = 0; i < model.getSize(); i++) {
            Fruit fruit = model.getElementAt(i);
            if (selectedOrigine.equals(fruit.getOrigine())) {
                modeleListeFruit.addElement(fruit);
            }
        }
        
  
        this.add(jcb);
        this.add(new JList<>(modeleListeFruit));
        
        JButton boycotterButton = new JButton("Boycotter");
        boycotterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                String elementSelectionne = (String) jcb.getSelectedValue();
                
                //System.out.println("Fruit a supprimeé:"+elementSelectionne.toString());
                parent.Boycottepays(elementSelectionne);
                dispose(); // Ferme la fenêtre de dialogue
            }
        });
        
        jcb.addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                String selectedOrigine = (String) jcb.getSelectedValue();
            
                // Effacer le modèle de la deuxième JList
                modeleListeFruit.removeAllElements();

                // Ajouter les fruits correspondant à l'origine sélectionnée
                for (int i = 0; i < model.getSize(); i++) {
                    Fruit fruit = model.getElementAt(i);
                    if (selectedOrigine.equals(fruit.getOrigine())) {
                        modeleListeFruit.addElement(fruit);
                    }
                }
            }
        }
        });

        
        JButton annulerButton = new JButton("Annuler");
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                System.out.println("Annulé:");
                dispose(); // Ferme la fenêtre de dialogue sans créer de fruit
            }
        });

        add(boycotterButton);
        add(annulerButton);
       // pack();                // Redimmentionnage automatique
        setSize(450, 400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2 - this.getWidth()/2, dim.height/2 - this.getHeight()/2);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
