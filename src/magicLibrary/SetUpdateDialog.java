/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.io.IOException;

/**
 *
 * @author nbp184
 */
public class SetUpdateDialog extends javax.swing.JDialog {

    public static void showDeckDialog(java.awt.Frame parent, Deck d) {
        SetUpdateDialog dd = new SetUpdateDialog(parent);
        dd.deck = d;
        dd.lblMessage.setText("Updating sets for " +d.name);
        dd.setVisible(true);
        dd.dispose();
    }
    
    public static String[] showCardDialog(java.awt.Frame parent, String name) {
        SetUpdateDialog dd = new SetUpdateDialog(parent);
        dd.name = name;
        dd.lblMessage.setText("Updating sets for " +name);
        dd.setVisible(true);
        String[] rv = dd.results;
        dd.dispose();
        return rv;
    }
    
    private Deck deck;
    private String name;
    private String[] results;
    
    /**
     * Creates new form DriveDialog
     */
    private SetUpdateDialog(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        setLocationRelativeTo(parent);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblMessage = new javax.swing.JLabel();

        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 2));

        lblMessage.setText("Updating sets for");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMessage)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            JSONSets sets = new JSONSets();
            if(deck != null) {
                for(Deck.DeckCard dc : deck) {
                    dc.card.sets = sets.getSets(dc.card.name);
                }
            } else {
                results = sets.getSets(name);
            }
            setVisible(false);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }//GEN-LAST:event_formWindowOpened

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblMessage;
    // End of variables declaration//GEN-END:variables
}