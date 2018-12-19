/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author nbp184
 */
public class MainFrame extends javax.swing.JFrame implements LibraryPanel.ChangePanelListener {

    public static final String filename = "library.txt";
    private static MainFrame instance = null;
    
    public static MainFrame getInstance() {
        return instance;
    }
    
    private LibraryPanel pnlLib;
    
    /**
     * Creates new form MainFrame
     */
    private MainFrame() {
        initComponents();
        File f = new File(filename);
        if(f.exists()) {
            try {
                Library.makeLibrary(f);
            } catch (IOException ex) {
                System.out.println("Library creation failed");
                System.out.println(ex.getMessage());
                ex.printStackTrace(System.out);
            }
        }
        pnlLib = new SimpleLibraryPanel();
        pnlLib.addChangePanelListener(this);
        pnlMain.setLayout(new BorderLayout());
        pnlMain.add(pnlLib, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        mbMain = new javax.swing.JMenuBar();
        mCard = new javax.swing.JMenu();
        miNewCard = new javax.swing.JMenuItem();
        miJSONCard = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Magic Library");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 805, Short.MAX_VALUE)
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 605, Short.MAX_VALUE)
        );

        mCard.setText("Card");

        miNewCard.setText("Add New");
        miNewCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miNewCardActionPerformed(evt);
            }
        });
        mCard.add(miNewCard);

        miJSONCard.setText("Add from JSON");
        miJSONCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miJSONCardActionPerformed(evt);
            }
        });
        mCard.add(miJSONCard);

        mbMain.add(mCard);

        setJMenuBar(mbMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            try {
                Library.getInstance().save();
            } catch (NullPointerException ex) {
                Library.getInstance().save(new File(filename));
            }
        } catch (IOException ex) {
            System.out.println("Library save failed");
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }//GEN-LAST:event_formWindowClosing

    private void miNewCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miNewCardActionPerformed
        Card c = CardDialog.showNewDialog(this, true);
        if(c != null) {
            Library.getInstance().addCard(c);
            pnlLib.fireLibraryChanged();
        }
    }//GEN-LAST:event_miNewCardActionPerformed

    private void miJSONCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miJSONCardActionPerformed
        try {
            if(JSONCardDialog.showDialog(this)) {
                pnlLib.fireLibraryChanged();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Couldn't find JSON file.");
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }//GEN-LAST:event_miJSONCardActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                instance = new MainFrame();
                instance.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu mCard;
    private javax.swing.JMenuBar mbMain;
    private javax.swing.JMenuItem miJSONCard;
    private javax.swing.JMenuItem miNewCard;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables

    @Override
    public void changePanel(String newPanelName) {
        pnlMain.remove(pnlLib);
        switch(newPanelName) {
            case "Simple":
                pnlLib = new SimpleLibraryPanel();
                pnlLib.addChangePanelListener(this);
                pnlMain.add(pnlLib, BorderLayout.CENTER);
                break;
            case "Advanced":
                break;
        }
    }

}
