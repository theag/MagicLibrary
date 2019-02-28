/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author nbp184
 */
public class CardDialog extends javax.swing.JDialog {

    /**
     * Shows a dialog for editing an existing card
     * @param parent
     * @param modal
     * @param card
     * @return 
     */
    public static boolean showEditDialog(java.awt.Frame parent, boolean modal, Card card) {
        CardDialog cd = new CardDialog(parent, modal, card);
        cd.pack();
        cd.setVisible(true);
        boolean rv = cd.saved;
        cd.dispose();
        return rv;
    }
    
    /**
     * Show a dialog for creating a new card
     * @param parent
     * @param modal
     * @return 
     */
    public static Card showNewDialog(java.awt.Frame parent, boolean modal) {
        CardDialog cd = new CardDialog(parent, modal, null);
        cd.setVisible(true);
        Card rv = cd.card;
        cd.dispose();
        return rv;
    }
    
    private boolean saved;
    
    private Card card;
    private DeckListModel deckModel;
    private String[] sets;
    
    private final ManaPanel pnlMana;
    
    /**
     * Creates new form CardDialog
     */
    private CardDialog(java.awt.Frame parent, boolean modal, Card card) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        this.card = card;
        saved = false;
        this.getContentPane().setBackground(Color.white);
        if(card == null) {
            btnDelete.setVisible(false);
        }
        if(card != null) {
            setTitle("Edit Card");
            deckModel = new DeckListModel(card);
            txtName.setText(card.name);
            pnlMana = new ManaPanel(card.manaCost);
            if(card.supertype.length > 0) {
                txtSuperType.setText(card.getSuperTypeString());
            }
            txtType.setText(card.getTypeString());
            if(card.subtype.length > 0) {
                txtSubType.setText(card.getSubTypeString());
            }
            if(card.text != null) {
                txtText.setText(card.text);
            }
            if(card.loyalty != null) {
                txtLPT.setText(card.loyalty);
            } else if(card.power != null) {
                txtLPT.setText(card.power +"/" +card.toughness);
            }
            if(card.notes != null) {
                txtNotes.setText(card.notes);
            }
            spnCount.setValue(card.count);
            cbAddsMana.setSelected(card.addsMana);
            txtSets.setText(card.getSetString());
            sets = card.sets;
            lblUpdated.setText("Last Updated: " +card.formatUpdate());
        } else {
            setTitle("New Card");
            pnlMana = new ManaPanel(new String[]{"0"});
            deckModel = new DeckListModel();
            sets = null;
        }
        lstDecks.setModel(deckModel);
        pnlManaHolder.setLayout(new BorderLayout());
        pnlManaHolder.add(pnlMana, BorderLayout.LINE_END);
        pnlMana.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlManaMouseClicked(evt);
            }
        });
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
        txtSuperType = new javax.swing.JTextField();
        txtType = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtSubType = new javax.swing.JTextField();
        pnlManaHolder = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtText = new javax.swing.JTextArea();
        txtLPT = new javax.swing.JTextField();
        spnCount = new javax.swing.JSpinner();
        lblUpdated = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtNotes = new javax.swing.JTextArea();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstDecks = new javax.swing.JList();
        btnAddToDeck = new javax.swing.JButton();
        btnEditDeckCount = new javax.swing.JButton();
        btnRemoveFromDeck = new javax.swing.JButton();
        cbAddsMana = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSets = new javax.swing.JTextArea();
        btnUpdateSets = new javax.swing.JButton();

        txtSuperType.setColumns(6);

        jLabel1.setText("--");

        txtSubType.setColumns(10);

        pnlManaHolder.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlManaHolderLayout = new javax.swing.GroupLayout(pnlManaHolder);
        pnlManaHolder.setLayout(pnlManaHolderLayout);
        pnlManaHolderLayout.setHorizontalGroup(
            pnlManaHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlManaHolderLayout.setVerticalGroup(
            pnlManaHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        txtText.setColumns(20);
        txtText.setLineWrap(true);
        txtText.setRows(5);
        txtText.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtText);

        txtLPT.setColumns(3);

        spnCount.setModel(new SpinnerNumberModel(0, 0, null, 1));

        lblUpdated.setText("Last Updated: never");

        jLabel2.setText("Decks");

        jLabel3.setText("Notes");

        txtNotes.setColumns(20);
        txtNotes.setRows(5);
        jScrollPane3.setViewportView(txtNotes);

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lstDecks.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstDecks.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(lstDecks);

        btnAddToDeck.setText("Add to Deck");
        btnAddToDeck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToDeckActionPerformed(evt);
            }
        });

        btnEditDeckCount.setText("Edit Count");
        btnEditDeckCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditDeckCountActionPerformed(evt);
            }
        });

        btnRemoveFromDeck.setText("Remove from Deck");
        btnRemoveFromDeck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFromDeckActionPerformed(evt);
            }
        });

        cbAddsMana.setText("Adds Mana");

        jLabel4.setText("Sets");

        txtSets.setEditable(false);
        txtSets.setColumns(20);
        txtSets.setLineWrap(true);
        txtSets.setRows(3);
        jScrollPane2.setViewportView(txtSets);

        btnUpdateSets.setText("Update Sets from JSON");
        btnUpdateSets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateSetsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtName)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSuperType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtType)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSubType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pnlManaHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtLPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
                        .addComponent(btnCancel))
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(lblUpdated)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(spnCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbAddsMana))
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAddToDeck)
                                    .addComponent(btnEditDeckCount)
                                    .addComponent(btnRemoveFromDeck)))
                            .addComponent(jLabel4)
                            .addComponent(btnUpdateSets))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtName)
                    .addComponent(pnlManaHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSuperType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtSubType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAddsMana))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUpdated)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddToDeck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditDeckCount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveFromDeck)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdateSets)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnDelete)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        saveCard();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteCard();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddToDeckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToDeckActionPerformed
        Deck[] decks = Library.getInstance().getDeckArray();
        Deck deck = (Deck)JOptionPane.showInputDialog(MainFrame.getInstance(), "Select a deck to add this card to", "Add to Deck", JOptionPane.QUESTION_MESSAGE, null, decks, decks[0]);
        if(deck != null) {
            if(!deckModel.add(deck)) {
                JOptionPane.showMessageDialog(MainFrame.getInstance(), "This card is already in " +deck.name +".", "Add to Deck", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAddToDeckActionPerformed

    private void btnEditDeckCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditDeckCountActionPerformed
        int index = lstDecks.getSelectedIndex();
        if(index >= 0) {
            int count = deckModel.getCount(index);
            Object value = JOptionPane.showInputDialog(MainFrame.getInstance(), "Number of this card in deck:", "Edit Deck Count", JOptionPane.QUESTION_MESSAGE, null, null, ""+count);
            while(value != null) {
                try {
                    count = Integer.parseInt((String)value);
                    if(count <= 0) {
                        value = JOptionPane.showInputDialog(MainFrame.getInstance(), "Count can't be zero or less.\nNumber of this card in deck:", "Edit Deck Count", JOptionPane.QUESTION_MESSAGE, null, null, ""+count);
                    } else {
                        deckModel.updateCount(index, count);
                        break;
                    }
                } catch(NumberFormatException ex) {
                    value = JOptionPane.showInputDialog(MainFrame.getInstance(), "That wasn't a number.\nNumber of this card in deck:", "Edit Deck Count", JOptionPane.QUESTION_MESSAGE, null, null, ""+count);
                }
            }
        }
    }//GEN-LAST:event_btnEditDeckCountActionPerformed

    private void btnRemoveFromDeckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveFromDeckActionPerformed
        int index = lstDecks.getSelectedIndex();
        if(index >= 0) {
            deckModel.removeAt(index);
        }
    }//GEN-LAST:event_btnRemoveFromDeckActionPerformed

    private void btnUpdateSetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateSetsActionPerformed
        sets = SetUpdateDialog.showCardDialog(MainFrame.getInstance(), txtName.getText().trim());
        String rv = "";
        if(sets != null) {
            for(int i = 0; i < sets.length; i++) {
                if(i > 0) {
                    rv += ", ";
                }
                rv += sets[i];
            }
        }
        txtSets.setText(rv);
    }//GEN-LAST:event_btnUpdateSetsActionPerformed

    private void pnlManaMouseClicked(java.awt.event.MouseEvent evt) {
        if(evt.getClickCount() == 2) {
            String[] mana = EditManaDialog.showDialog(this, pnlMana.getManaCost());
            if(mana != null) {
                pnlMana.setManaCost(mana);
                pnlMana.revalidate();
                this.revalidate();
            }
        }
    }
    
    private void saveCard() {
        //Validation
        ArrayList<String> errors = new ArrayList<>();
        if(txtName.getText().trim().isEmpty()) {
            errors.add("Name cannot be empty.");
        }
        if(pnlMana.getManaCost().length == 0) {
            errors.add("Must have mana cost.");
        }
        if(txtType.getText().trim().isEmpty()) {
            errors.add("Type cannot be empty");
        }
        if(!errors.isEmpty()) {
            String s = "Cannot save card because of the following errors:";
            for(int i = 0; i < errors.size(); i++) {
                s += "\n" +(i+1) +": " +errors.get(i);
            }
            JOptionPane.showMessageDialog(this, s, "Save", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Saving
        if(card == null) {
            card = new Card();
        }
        card.name = txtName.getText().trim();
        card.manaCost = pnlMana.getManaCost();
        card.supertype = split(txtSuperType.getText().trim());
        card.type = split(txtType.getText().trim());
        card.subtype = split(txtSubType.getText().trim());
        if(!txtText.getText().trim().isEmpty()) {
            card.text = txtText.getText().trim();
        } else {
            card.text = null;
        }
        String s = txtLPT.getText().trim();
        if(!s.isEmpty()) {
            int index = s.indexOf("/");
            if(index >= 0) {
                card.power = s.substring(0, index);
                card.toughness = s.substring(index + 1);
                card.loyalty = null;
            } else {
                card.loyalty = s;
                card.power = null;
                card.toughness = null;
            }
        } else {
            card.loyalty = null;
            card.power = null;
            card.toughness = null;
        }
        card.count = (int)spnCount.getValue();
        card.addsMana = cbAddsMana.isSelected();
        deckModel.save(card);
        if(!txtNotes.getText().trim().isEmpty()) {
            card.notes = txtNotes.getText().trim();
        } else {
            card.notes = null;
        }
        card.sets = sets;
        card.lastUpdated = Calendar.getInstance().getTimeInMillis();
        saved = true;
        setVisible(false);
    }
    
    private void deleteCard() {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure that you wish to delete this card?", "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION) {
            Library.getInstance().deleteCard(card);
            saved = true;
            setVisible(false);
        }
    }
    
    private String[] split(String s) {
        if(s.isEmpty()) {
            return new String[0];
        } else {
            return s.split(" ");
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToDeck;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEditDeckCount;
    private javax.swing.JButton btnRemoveFromDeck;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdateSets;
    private javax.swing.JCheckBox cbAddsMana;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblUpdated;
    private javax.swing.JList lstDecks;
    private javax.swing.JPanel pnlManaHolder;
    private javax.swing.JSpinner spnCount;
    private javax.swing.JTextField txtLPT;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextArea txtNotes;
    private javax.swing.JTextArea txtSets;
    private javax.swing.JTextField txtSubType;
    private javax.swing.JTextField txtSuperType;
    private javax.swing.JTextArea txtText;
    private javax.swing.JTextField txtType;
    // End of variables declaration//GEN-END:variables
}
