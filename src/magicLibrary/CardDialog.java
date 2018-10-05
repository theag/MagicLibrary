/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
     * @param deckMode
     * @param count
     * @return 
     */
    public static boolean showEditDialog(java.awt.Frame parent, boolean modal, Card card, boolean deckMode, int count) {
        CardDialog cd = new CardDialog(parent, modal, card, deckMode, count);
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
        CardDialog cd = new CardDialog(parent, modal, null, false, 0);
        cd.setVisible(true);
        Card rv = cd.card;
        cd.dispose();
        return rv;
    }
    
    private boolean saved;
    
    private Card card;
    private String[][] types;
    
    private final JTextField txtName;
    private final ManaPanel pnlMana;
    private final JTextField txtSuperType;
    private final JTextField txtType;
    private final JTextField txtSubType;
    private final JTextArea txtText;
    private final JTextField txtLPT;
    private final JSpinner spnCount;
    private final MoneyPanel pnlPrice;
    private final JTextArea txtNotes;
    private final JButton btnSave;
    private final JButton btnClose;
    
    /**
     * Creates new form CardDialog
     */
    private CardDialog(java.awt.Frame parent, boolean modal, Card card, boolean deckMode, int count) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        this.card = card;
        saved = false;
        this.getContentPane().setBackground(Color.white);
        setLayout(new GridBagLayout());
        GridBagConstraints c;
        txtName = new JTextField();
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.weightx = 1;
        c.insets.top = 5;
        c.insets.left = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(txtName, c);

        txtSuperType = new JTextField();
        txtSuperType.setColumns(6);
        txtSuperType.setMinimumSize(txtSuperType.getPreferredSize());
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.insets.left = 5;
        c.anchor = GridBagConstraints.LINE_START;
        add(txtSuperType, c);

        txtType = new JTextField();
        txtType.setColumns(5);
        txtType.setMinimumSize(txtType.getPreferredSize());
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        add(txtType, c);

        JLabel lbl = new JLabel(" -- ");
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        add(lbl, c);

        txtSubType = new JTextField();
        c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(txtSubType, c);

        txtText = new JTextArea();
        txtText.setRows(5);
        txtText.setLineWrap(true);
        txtText.setWrapStyleWord(true);
        JScrollPane sp = new JScrollPane();
        sp.setViewportView(txtText);
        sp.setMinimumSize(new Dimension(0, sp.getPreferredSize().height));
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 5;
        c.weightx = 1;
        c.insets.left = 5;
        c.insets.right = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(sp, c);

        txtLPT = new JTextField();
        txtLPT.setColumns(3);
        txtLPT.setMinimumSize(txtLPT.getPreferredSize());
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 5;
        c.insets.right = 5;
        c.anchor = GridBagConstraints.LINE_END;
        add(txtLPT, c);
        
        spnCount = new JSpinner();
        spnCount.setModel(new SpinnerNumberModel(0, 0, null, 1));
        spnCount.setBackground(Color.white);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 5;
        c.insets.left = 5;
        c.anchor = GridBagConstraints.LINE_START;
        add(spnCount, c);
        
        pnlPrice = new MoneyPanel();
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 5;
        c.insets.left = 5;
        c.insets.right = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(pnlPrice, c);
        
        JLabel updated = new JLabel("Last Updated: never");
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 5;
        c.insets.left = 5;
        c.anchor = GridBagConstraints.LINE_START;
        add(updated, c);
        
        lbl = new JLabel("Notes");
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 5;
        c.insets.left = 5;
        c.anchor = GridBagConstraints.LINE_START;
        add(lbl, c);
        
        txtNotes = new JTextArea();
        txtNotes.setLineWrap(true);
        txtNotes.setWrapStyleWord(true);
        sp = new JScrollPane();
        sp.setViewportView(txtNotes);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets.left = 5;
        c.insets.right = 5;
        c.fill = GridBagConstraints.BOTH;
        add(sp, c);
        
        btnSave = new JButton("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveCard();
            }
        });
        btnClose = new JButton("Cancel");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setVisible(false);
            }
        });
        JPanel pnl = new JPanel();
        pnl.setBackground(Color.white);
        pnl.add(btnSave);
        pnl.add(btnClose);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 5;
        c.weightx = 1;
        c.insets.left = 5;
        c.insets.bottom = 5;
        c.anchor = GridBagConstraints.LINE_START;
        add(pnl, c);

        if(card != null) {
            if(deckMode) {
                setTitle("Edit Deck Card");
                //add deck amount
            } else {
                setTitle("Edit Card");
            }
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
            pnlPrice.setAmount(card.price);
            updated.setText("Last Updated: " +card.formatUpdate());
        } else {
            setTitle("New Card");
            pnlMana = new ManaPanel();
        }
        c = new GridBagConstraints();
        c.gridx = 4;
        c.gridy = 0;
        c.insets.top = 5;
        c.insets.left = 5;
        c.insets.right = 5;
        c.anchor = GridBagConstraints.LINE_END;
        add(pnlMana, c);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 417, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnlManaMouseClicked(java.awt.event.MouseEvent evt) {
        if(evt.getClickCount() == 2) {
            String[] mana = EditManaDialog.showDialog(this, pnlMana.getManaCost());
            if(mana != null) {
                pnlMana.setManaCost(mana);
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
            //show error
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
        card.price = pnlPrice.getAmount();
        if(!txtNotes.getText().trim().isEmpty()) {
            card.notes = txtNotes.getText().trim();
        } else {
            card.notes = null;
        }
        card.lastUpdated = Calendar.getInstance().getTimeInMillis();
        saved = true;
        setVisible(false);
    }
    
    private String[] split(String s) {
        if(s.isEmpty()) {
            return new String[0];
        } else {
            return s.split(" ");
        }
    }
    
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
            java.util.logging.Logger.getLogger(CardDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CardDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CardDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CardDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Library.makeLibrary(new java.io.File(MainFrame.filename));
                } catch (IOException ex) {
                    System.out.println("Library creation failed");
                    System.out.println(ex.getMessage());
                    ex.printStackTrace(System.out);
                }
                CardDialog dialog = new CardDialog(new javax.swing.JFrame(), false, Library.getInstance().resultAt(1), false, 0);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
