/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author nbp184
 */
public class JSONCardDialog extends javax.swing.JDialog {

    /**
     * Show a dialog for creating a new card
     * @param parent
     * @return 
     * @throws java.io.FileNotFoundException 
     */
    public static boolean showDialog(java.awt.Frame parent) throws FileNotFoundException {
        JSONCardDialog cd = new JSONCardDialog(parent, true);
        cd.setVisible(true);
        boolean rv = cd.saved;
        cd.dispose();
        return rv;
    }
    
    private final CardNameListModel model;
    private final DeckListModel decks;
    private boolean saved;
    private final JSONObject cards;
    
    /**
     * Creates new form JSONCardDialog
     * @param parent
     * @param modal
     * @throws java.io.FileNotFoundException
     */
    public JSONCardDialog(java.awt.Frame parent, boolean modal) throws FileNotFoundException {
        super(parent, modal);
        cards = new JSONObject(new JSONTokener(new FileInputStream("AllCards.json")));
        ArrayList<String> names = new ArrayList<>();
        names.addAll(cards.keySet());
        Collections.sort(names);
        model = new CardNameListModel(names);
        decks = new DeckListModel();
        initComponents();
        setLocationRelativeTo(parent);
        saved = false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstCards = new javax.swing.JList();
        btnAdd = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnAddPlus = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstDecks = new javax.swing.JList();

        setTitle("New Card from JSON");

        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNameKeyReleased(evt);
            }
        });

        lstCards.setModel(model);
        jScrollPane1.setViewportView(lstCards);

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnAddPlus.setText("Add +");
        btnAddPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPlusActionPerformed(evt);
            }
        });

        jLabel1.setText("Add to Decks");

        lstDecks.setModel(decks);
        jScrollPane3.setViewportView(lstDecks);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddPlus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd)
                        .addComponent(btnAddPlus))
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if(addSelected()) {
            setVisible(false);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyReleased
        String str = txtName.getText().trim();
        if(str.isEmpty()) {
            model.clearSearch();
        } else {
            model.doSearch(str);
        }
    }//GEN-LAST:event_txtNameKeyReleased

    private void btnAddPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPlusActionPerformed
        if(addSelected()) {
            txtName.setText("");
            model.clearSearch();
        }
    }//GEN-LAST:event_btnAddPlusActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddPlus;
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList lstCards;
    private javax.swing.JList lstDecks;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables

    private boolean addSelected() {
        java.util.List<String> cards = lstCards.getSelectedValuesList();
        if(cards.size() > 0) {
            Library l = Library.getInstance();
            Card c;
            JSONObject obj;
            JSONArray arr;
            long now = Calendar.getInstance().getTimeInMillis();
            String err = "";
            for(String name : cards) {
                if(l.getCardByName(name) != null) {
                    err += "\n" +name;
                } else {
                    c = new Card();
                    c.name = name;
                    obj = this.cards.getJSONObject(name);
                    if(obj.has("supertypes")) {
                        arr = obj.getJSONArray("supertypes");
                        c.supertype = new String[arr.length()];
                        for(int i = 0; i < arr.length(); i++) {
                            c.supertype[i] = arr.getString(i);
                        }
                    } else {
                        c.supertype = new String[0];
                    }
                    if(obj.has("types")) {
                        arr = obj.getJSONArray("types");
                        c.type = new String[arr.length()];
                        for(int i = 0; i < arr.length(); i++) {
                            c.type[i] = arr.getString(i);
                        }
                    } else {
                        c.type = new String[0];
                    }
                    if(obj.has("subtypes")) {
                        arr = obj.getJSONArray("subtypes");
                        c.subtype = new String[arr.length()];
                        for(int i = 0; i < arr.length(); i++) {
                            c.subtype[i] = arr.getString(i);
                        }
                    } else {
                        c.subtype = new String[0];
                    }
                    if(obj.has("manaCost")) {
                        String cost = obj.getString("manaCost");
                        cost = cost.substring(1, cost.length() - 1).replaceAll("/","");
                        c.manaCost = cost.split("\\}\\{");
                    } else {
                        c.manaCost = new String[]{"0"};
                    }
                    if(obj.has("text")) {
                        c.fancyText = obj.getString("text");
                        c.text = c.fancyText;
                    } else {
                        c.text = "";
                        c.fancyText = "";
                    }
                    if(obj.has("power")) {
                        c.power = obj.getString("power");
                    }
                    if(obj.has("toughness")) {
                        c.toughness = obj.getString("toughness");
                    }
                    if(obj.has("loyalty")) {
                        c.loyalty = obj.getString("loyalty");
                    }
                    c.lastUpdated = now;
                    l.addCard(c);
                    for(int index : lstDecks.getSelectedIndices()) {
                        decks.getElementAt(index).addCard(c, 1);
                    }
                }
            }
            if(err.isEmpty()) {
                saved = true;
                return true;
            } else {
                JOptionPane.showMessageDialog(MainFrame.getInstance(), "The following cards are already in your library:" +err, "Add to Library", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            saved = true;
            return true;
        }
    }
    
    private class DeckListModel extends javax.swing.AbstractListModel {

        @Override
        public int getSize() {
            return Library.getInstance().deckCount();
        }

        @Override
        public Deck getElementAt(int index) {
            return Library.getInstance().getDeck(index);
        }
        
    }

}
