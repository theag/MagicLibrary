/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

/**
 *
 * @author nbp184
 */
public class SimpleLibraryPanel extends LibraryPanel {

    private final SearchTableModel model;
  
    /**
     * Creates new form LibraryPanel
     */
    public SimpleLibraryPanel() {
        model = new SearchTableModel();
        initComponents();
        tblCards.setRowHeight(ManaPanel.DOT_SIZE + 1 + 4);
        tblCards.getColumnModel().getColumn(1).setCellRenderer(new ManaTableCellRenderer());
        tblCards.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCardsHeaderMouseClicked(evt);
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
        btnAdvanced = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCards = new javax.swing.JTable();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNameKeyReleased(evt);
            }
        });

        btnAdvanced.setText("Advanced");
        btnAdvanced.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdvancedActionPerformed(evt);
            }
        });

        tblCards.setModel(model);
        tblCards.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCardsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCards);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdvanced)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdvanced))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdvancedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdvancedActionPerformed
        this.fireChangePanel("Advanced");
    }//GEN-LAST:event_btnAdvancedActionPerformed

    private void txtNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyReleased
        String searchStr = txtName.getText().trim().toLowerCase();
        if(searchStr.isEmpty()) {
            Library.getInstance().clearSearch();
        } else {
            Library.getInstance().doSearch(searchStr);
        }
        fireLibraryChanged();
    }//GEN-LAST:event_txtNameKeyReleased

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        tblCards.getColumnModel().getColumn(1).setCellRenderer(new ManaTableCellRenderer());
        int width = (tblCards.getWidth() - 270)/2;
        tblCards.getColumnModel().getColumn(0).setPreferredWidth(width);
        tblCards.getColumnModel().getColumn(1).setPreferredWidth(width);
        tblCards.getColumnModel().getColumn(2).setPreferredWidth(60);
        tblCards.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblCards.getColumnModel().getColumn(4).setPreferredWidth(60);
    }//GEN-LAST:event_formComponentResized

    private void tblCardsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCardsMouseClicked
        if(evt.getClickCount() == 2) {
            int row = tblCards.rowAtPoint(evt.getPoint());
            boolean saved = CardDialog.showEditDialog(MainFrame.getInstance(), true, model.getCardAt(row));
            if(saved) {
                fireLibraryChanged();
                MainFrame.getInstance().updateDecks();
            }
        }
    }//GEN-LAST:event_tblCardsMouseClicked

    private void tblCardsHeaderMouseClicked(java.awt.event.MouseEvent evt) {
        int col = tblCards.columnAtPoint(evt.getPoint());
        if(col != 1) {
            model.setSort(col);
            tblCards.getColumnModel().getColumn(1).setCellRenderer(new ManaTableCellRenderer());
            int width = (tblCards.getWidth() - 270)/2;
            tblCards.getColumnModel().getColumn(0).setPreferredWidth(width);
            tblCards.getColumnModel().getColumn(1).setPreferredWidth(width);
            tblCards.getColumnModel().getColumn(2).setPreferredWidth(60);
            tblCards.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblCards.getColumnModel().getColumn(4).setPreferredWidth(60);
        }
    }
    
    private String printArr(int[] arr) {
        String rv = "[";
        for(int i = 0; i < arr.length; i++) {
            if(i > 0) {
                rv += ",";
            }
            rv += arr[i];
        }
        rv += "]";
        return rv;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdvanced;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCards;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables

    @Override
    public void fireLibraryChanged() {
        Library.getInstance().redoSesarch();
        model.updateData();
    }
}
