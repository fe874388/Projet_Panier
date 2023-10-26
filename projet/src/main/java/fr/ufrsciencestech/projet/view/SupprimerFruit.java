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

public class SupprimerFruit extends JDialog {
    private Fruit fruit;
    private VueGraphiqueListe parent;
    private JComboBox<Fruit> jcb;
    
    public SupprimerFruit(final VueGraphiqueListe p) {
        super(p, "Supprimer Fruit/Jus/Macedoine", true);
        this.parent = p;
        
        setLayout(new GridLayout(3, 2));
        
        DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) this.parent.getjComboBox().getModel();

        this.jcb = new JComboBox<>(model);
        this.add(jcb);
        this.add(new JLabel("", JLabel.CENTER));
        this.add(new JLabel("", JLabel.CENTER));
        this.add(new JLabel("", JLabel.CENTER));
        
        JButton supprimerButton = new JButton("Supprimer");
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                Fruit elementSelectionne = (Fruit) jcb.getSelectedItem();
                //System.out.println("Fruit a supprimeé:"+elementSelectionne.toString());
                parent.supprimerFruit(elementSelectionne);
                dispose(); // Ferme la fenêtre de dialogue
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

        add(supprimerButton);
        add(annulerButton);
        pack();                // Redimmentionnage automatique
        setSize(400, 400);
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getWidth()/2, dim.height/2 - this.getWidth()/2);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    public Fruit getfruit() {
        return this.fruit;
    }
    
    public void setfruit(Fruit f) {
        this.fruit=f;
    }
    
    public JComboBox getjComboBox() {
        return this.jcb;
    }
        
}
