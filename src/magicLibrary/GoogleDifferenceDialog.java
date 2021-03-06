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
public class GoogleDifferenceDialog extends javax.swing.JDialog {
    
    private static GoogleDifferenceDialog instance = null;
    
    public static void showDialog(java.awt.Frame parent, Library.Differences diffs) {
        if(instance == null) {
            instance = new GoogleDifferenceDialog(parent, diffs);
            instance.setVisible(true);
        } else {
            instance.updateDifferences(diffs);
        }
    }

    /**
     * Creates new form GoogleDifferenceDialog
     */
    public GoogleDifferenceDialog(java.awt.Frame parent, Library.Differences diffs) {
        super(parent, false);
        initComponents();
        setLocationRelativeTo(parent);
        txtDifferences.setText(diffs.toString());
    }
    
    private void updateDifferences(Library.Differences diffs) {
        txtDifferences.setText(diffs.toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtDifferences = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Google Differences");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        txtDifferences.setEditable(false);
        txtDifferences.setColumns(20);
        txtDifferences.setRows(5);
        jScrollPane1.setViewportView(txtDifferences);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        instance = null;
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtDifferences;
    // End of variables declaration//GEN-END:variables
}
