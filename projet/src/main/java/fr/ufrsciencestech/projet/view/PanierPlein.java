package fr.ufrsciencestech.projet.view;

import javax.swing.*;

/**
 * Cette classe représente une fenêtre d'erreur affichée lorsque le panier est plein.
 * @author TD2 Groupe 11
 */
public class PanierPlein extends JFrame {
    
    /**
     * Constructeur de la fenêtre d'erreur pour un panier plein.
     * @param message Le message d'erreur à afficher.
     */
    public PanierPlein(String message) {
        initComponents(message);
    }
    
    /**
     * Initialise les composants de la fenêtre d'erreur.
     * @param message Le message d'erreur à afficher.
     */
    private void initComponents(String message) {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Erreur - Panier Plein");

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel errorMessageLabel = new JLabel(message);
        errorMessageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose(); // Ferme la fenêtre en cliquant sur le bouton "Fermer"
            }
        });
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(errorMessageLabel)
                        .addComponent(closeButton)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(errorMessageLabel)
                        .addComponent(closeButton)
        );
        GroupLayout mainLayout = new GroupLayout(getContentPane());
        getContentPane().setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
                mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        mainLayout.setVerticalGroup(
                mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
