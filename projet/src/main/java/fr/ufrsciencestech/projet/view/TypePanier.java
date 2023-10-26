package fr.ufrsciencestech.projet.view;
import fr.ufrsciencestech.projet.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TypePanier extends JDialog{
    private VueGraphiqueListe parent;
    private JRadioButton type1RadioButton;  // 
    private JRadioButton type2RadioButton;  // 
    private JRadioButton type3RadioButton;  //
    private JPanel jp; 
    
    
    public TypePanier(final VueGraphiqueListe p) {
        super(p, "Choisir le type du panier", true);
        this.parent = p;
        
        setLayout(new GridLayout(2, 2));
        
        type1RadioButton = new JRadioButton("Panier à moins de 1€/fruit");
        type2RadioButton = new JRadioButton("Fruits locaux (France)");
        type3RadioButton = new JRadioButton("Fruit seulement");
        JPanel jp= new JPanel();
        ButtonGroup group = new ButtonGroup();
        type1RadioButton.setSelected(true);
        group.add(type1RadioButton);
        group.add(type2RadioButton);
        group.add(type3RadioButton);
        jp.add(type1RadioButton);
        jp.add(type2RadioButton);
        jp.add(type3RadioButton);
        this.add(jp);
        this.add(new JLabel("",JLabel.CENTER));
        JButton ChoixButton = new JButton("Choisir");
        ChoixButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                dispose(); // Ferme la fenêtre de dialogue
            }
        });

        JButton annulerButton = new JButton("Annuler");
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                System.out.println("Annulé");
                dispose(); // Ferme la fenêtre de dialogue sans créer de fruit
            }
        });

        add(ChoixButton);
        add(annulerButton);
        pack();                // Redimmentionnage automatique
        setSize(400, 400);
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getWidth()/2, dim.height/2 - this.getWidth()/2);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
