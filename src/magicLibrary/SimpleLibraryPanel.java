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

    private final int[][] selected;
  
    /**
     * Creates new form LibraryPanel
     */
    public SimpleLibraryPanel() {
        initComponents();
        lstCards.setCellRenderer(new CardCellRenderer());
        selected = new int[2][0];
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
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstCards = new javax.swing.JList();

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        btnAdvanced.setText("Advanced");
        btnAdvanced.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdvancedActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        lstCards.setModel(new LibraryListModel());
        lstCards.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstCardsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstCards);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch)
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
                    .addComponent(btnAdvanced)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lstCardsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstCardsMouseClicked
        if(evt.getClickCount() == 1) {
            selected[1] = selected[0];
            selected[0] = lstCards.getSelectedIndices();
            if(selected[0].length == 1 && selected[1].length == 1 && selected[0][0] == selected[1][0]) {
                lstCards.clearSelection();
                selected[0] = new int[0];
            }
        } else if(evt.getClickCount() == 2) {
            selected[0] = selected[1];
            lstCards.setSelectedIndices(selected[0]);
            int index = lstCards.locationToIndex(evt.getPoint());
            if(CardDialog.showEditDialog(MainFrame.getInstance(), true, Library.getInstance().resultAt(index))) {
                fireLibraryChanged();
                //todo: update deck panel
            }
        }
    }//GEN-LAST:event_lstCardsMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String searchStr = txtName.getText().trim().toLowerCase();
        if(searchStr.isEmpty()) {
            Library.getInstance().clearSearch();
        } else {
            Library.getInstance().doSearch(searchStr);
        }
        fireLibraryChanged();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        btnSearchActionPerformed(null);
    }//GEN-LAST:event_txtNameActionPerformed

    private void btnAdvancedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdvancedActionPerformed
        this.fireChangePanel("Advanced");
    }//GEN-LAST:event_btnAdvancedActionPerformed

    
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
    private javax.swing.JButton btnSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstCards;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables

    @Override
    public void fireLibraryChanged() {
        Library.getInstance().redoSesarch();
        ((LibraryListModel)lstCards.getModel()).fireLibraryChanged();
    }
}
