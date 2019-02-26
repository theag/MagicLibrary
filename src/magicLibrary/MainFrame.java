/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.awt.CardLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author nbp184
 */
public class MainFrame extends javax.swing.JFrame implements LibraryPanel.ChangePanelListener {

    public static final String cardFile = "library.txt";
    public static final String deckFile = "boxes.txt";
    private static MainFrame instance = null;
    
    public static MainFrame getInstance() {
        return instance;
    }
    
    private int current;
    private final String[] panels;
    private File fc;
    private File fd;
    
    /**
     * Creates new form MainFrame
     */
    private MainFrame() {
        initComponents();
        fc = new File(cardFile);
        fd = new File(deckFile);
        if(fc.exists() && fd.exists()) {
            try {
                Library.makeLibrary(fc, fd);
            } catch (Exception ex) {
                System.out.println("Library creation failed");
                System.out.println(ex.getMessage());
                ex.printStackTrace(System.out);
            }
        }
        panels = new String[2];
        pnlMain.setLayout(new CardLayout());
        LibraryPanel pnlLib = new SimpleLibraryPanel();
        pnlLib.addChangePanelListener(this);
        panels[0] = "Simple";
        pnlMain.add(pnlLib, panels[0]);
        pnlLib = new AdvancedLibraryPanel();
        pnlLib.addChangePanelListener(this);
        panels[1] = "Advanced";
        pnlMain.add(pnlLib, panels[1]);
        pnlMain.add(new DeckPanel(), "Decks");
        current = 0;
        setLocationRelativeTo(null);
        pack();
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
        tbMain = new javax.swing.JToolBar();
        btnCards = new javax.swing.JToggleButton();
        btnDecks = new javax.swing.JToggleButton();
        mbMain = new javax.swing.JMenuBar();
        mCard = new javax.swing.JMenu();
        miNewCard = new javax.swing.JMenuItem();
        miJSONCard = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        miNewDeck = new javax.swing.JMenuItem();
        miNewDeckFromList = new javax.swing.JMenuItem();
        miDeckList = new javax.swing.JMenuItem();
        miDeckNeedList = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        miUpload = new javax.swing.JMenuItem();
        miDownload = new javax.swing.JMenuItem();
        miDifferences = new javax.swing.JMenuItem();

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
            .addGap(0, 618, Short.MAX_VALUE)
        );

        tbMain.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, java.awt.Color.gray));
        tbMain.setFloatable(false);
        tbMain.setRollover(true);

        btnCards.setSelected(true);
        btnCards.setText("Cards");
        btnCards.setFocusable(false);
        btnCards.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCards.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCards.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCardsActionPerformed(evt);
            }
        });
        tbMain.add(btnCards);

        btnDecks.setText("Decks");
        btnDecks.setFocusable(false);
        btnDecks.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDecks.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDecks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDecksActionPerformed(evt);
            }
        });
        tbMain.add(btnDecks);

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

        jMenu1.setText("Decks");

        miNewDeck.setText("New Deck");
        miNewDeck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miNewDeckActionPerformed(evt);
            }
        });
        jMenu1.add(miNewDeck);

        miNewDeckFromList.setText("New Deck from List");
        miNewDeckFromList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miNewDeckFromListActionPerformed(evt);
            }
        });
        jMenu1.add(miNewDeckFromList);

        miDeckList.setText("Deck List");
        miDeckList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miDeckListActionPerformed(evt);
            }
        });
        jMenu1.add(miDeckList);

        miDeckNeedList.setText("Deck Need List");
        miDeckNeedList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miDeckNeedListActionPerformed(evt);
            }
        });
        jMenu1.add(miDeckNeedList);

        mbMain.add(jMenu1);

        jMenu2.setText("Google Sync");

        miUpload.setText("Upload to Google");
        miUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miUploadActionPerformed(evt);
            }
        });
        jMenu2.add(miUpload);

        miDownload.setText("Download from Google");
        miDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miDownloadActionPerformed(evt);
            }
        });
        jMenu2.add(miDownload);

        miDifferences.setText("Show Differences");
        miDifferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miDifferencesActionPerformed(evt);
            }
        });
        jMenu2.add(miDifferences);

        mbMain.add(jMenu2);

        setJMenuBar(mbMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tbMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            Library.getInstance().save(fc, fd);
        } catch (IOException ex) {
            System.out.println("Library save failed");
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }//GEN-LAST:event_formWindowClosing

    private void miNewCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miNewCardActionPerformed
        Card c = CardDialog.showNewDialog(this, true);
        if(c != null) {
            if(Library.getInstance().addCard(c)) {
                ((LibraryPanel)pnlMain.getComponent(current)).fireLibraryChanged();
                updateDecks();
            } else {
                int result = JOptionPane.showConfirmDialog(this, "You already have " +c.name +" in your library, edit now?", "Add to Library", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if(result == JOptionPane.YES_OPTION) {
                    if(CardDialog.showEditDialog(MainFrame.getInstance(), true, Library.getInstance().getCardByName(c.name))) {
                        ((LibraryPanel)pnlMain.getComponent(current)).fireLibraryChanged();
                        updateDecks();
                    }
                }
            }
        }
    }//GEN-LAST:event_miNewCardActionPerformed

    private void miJSONCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miJSONCardActionPerformed
        try {
            if(JSONCardDialog.showDialog(this)) {
                ((LibraryPanel)pnlMain.getComponent(current)).fireLibraryChanged();
                updateDecks();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Couldn't find JSON file.");
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }//GEN-LAST:event_miJSONCardActionPerformed

    private void miNewDeckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miNewDeckActionPerformed
        String result = JOptionPane.showInputDialog(this, "Enter new deck name", "New Deck", JOptionPane.QUESTION_MESSAGE);
        if(result != null) {
            Deck d = new Deck(result);
            if(Library.getInstance().addDeck(d)) {
                ((DeckPanel)pnlMain.getComponent(2)).fireLibraryChanged(false);
            } else {
                JOptionPane.showMessageDialog(this, "You already have a deck called " +result +".", "New Deck", JOptionPane.ERROR_MESSAGE);
                
            }
        }
    }//GEN-LAST:event_miNewDeckActionPerformed

    private void btnCardsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCardsActionPerformed
        if(btnCards.isSelected()) {
            CardLayout lo = (CardLayout)pnlMain.getLayout();
            lo.show(pnlMain, panels[current]);
            btnDecks.setSelected(false);
        } else {
            btnCards.setSelected(true);
        }
    }//GEN-LAST:event_btnCardsActionPerformed

    private void btnDecksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDecksActionPerformed
        if(btnDecks.isSelected()) {
            CardLayout lo = (CardLayout)pnlMain.getLayout();
            lo.show(pnlMain, "Decks");
            btnCards.setSelected(false);
        } else {
            btnDecks.setSelected(true);
        }
    }//GEN-LAST:event_btnDecksActionPerformed

    private void miUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miUploadActionPerformed
        try {
            Library.getInstance().save(fc, fd);
            DriveDialog.showUploadDialog(this, fc, fd);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }//GEN-LAST:event_miUploadActionPerformed

    private void miDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miDownloadActionPerformed
        try {
            DriveDialog.showDownloadDialog(this, fc, fd);
            Library.makeLibrary(fc, fd);
            ((LibraryPanel)pnlMain.getComponent(current)).fireLibraryChanged();
            updateDecks();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }//GEN-LAST:event_miDownloadActionPerformed

    private void miDifferencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miDifferencesActionPerformed
        try {
            GoogleDifferenceDialog.showDialog(this, DriveDialog.showDifferenceDialog(this, fc, fd));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }//GEN-LAST:event_miDifferencesActionPerformed

    private void miDeckListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miDeckListActionPerformed
        Deck result;
        if(btnDecks.isSelected()) {
            DeckPanel dp = (DeckPanel)pnlMain.getComponent(2);
            result = dp.getSelectedDeck();
        } else {
            result = (Deck)JOptionPane.showInputDialog(this, "Which deck list would you like to export?", "Deck List", JOptionPane.QUESTION_MESSAGE, null, Library.getInstance().getDeckArray(), null);
        }
        if(result != null) {
            DeckListDialog.showDialog(this, result);
        }
    }//GEN-LAST:event_miDeckListActionPerformed

    private void miDeckNeedListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miDeckNeedListActionPerformed
        Deck result;
        if(btnDecks.isSelected()) {
            DeckPanel dp = (DeckPanel)pnlMain.getComponent(2);
            result = dp.getSelectedDeck();
        } else {
            result = (Deck)JOptionPane.showInputDialog(this, "Which deck list would you like to export?", "Deck Need List", JOptionPane.QUESTION_MESSAGE, null, Library.getInstance().getDeckArray(), null);
        }
        if(result != null) {
            DeckListDialog.showNeedDialog(this, result);
        }
    }//GEN-LAST:event_miDeckNeedListActionPerformed

    private void miNewDeckFromListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miNewDeckFromListActionPerformed
        try {
            Deck d = NewDeckListDialog.showDialog(this);
            if(d != null) {
                if(Library.getInstance().addDeck(d)) {
                    ((DeckPanel)pnlMain.getComponent(2)).fireLibraryChanged(false);
                } else {
                    JOptionPane.showMessageDialog(this, "You already have a deck called " +d.name +".", "New Deck", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }//GEN-LAST:event_miNewDeckFromListActionPerformed

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
    private javax.swing.JToggleButton btnCards;
    private javax.swing.JToggleButton btnDecks;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu mCard;
    private javax.swing.JMenuBar mbMain;
    private javax.swing.JMenuItem miDeckList;
    private javax.swing.JMenuItem miDeckNeedList;
    private javax.swing.JMenuItem miDifferences;
    private javax.swing.JMenuItem miDownload;
    private javax.swing.JMenuItem miJSONCard;
    private javax.swing.JMenuItem miNewCard;
    private javax.swing.JMenuItem miNewDeck;
    private javax.swing.JMenuItem miNewDeckFromList;
    private javax.swing.JMenuItem miUpload;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JToolBar tbMain;
    // End of variables declaration//GEN-END:variables

    @Override
    public void changePanel(String newPanelName) {
        switch(newPanelName) {
            case "Simple":
                current = 0;
                Library.getInstance().clearSearch();
                break;
            case "Advanced":
                current = 1;
                Library.getInstance().doSearch(""+Library.NULL);
                break;
        }
        CardLayout lo = (CardLayout)pnlMain.getLayout();
        lo.show(pnlMain, newPanelName);
    }

    void updateDecks() {
        ((DeckPanel)pnlMain.getComponent(2)).fireLibraryChanged(true);
    }

}
