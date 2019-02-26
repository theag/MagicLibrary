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
public class DeckListDialog extends javax.swing.JDialog {

    public static void showDialog(java.awt.Frame parent, Deck d) {
        DeckListDialog dld = new DeckListDialog(parent, d, false);
        dld.setVisible(true);
    }
    
    public static void showNeedDialog(java.awt.Frame parent, Deck d) {
        DeckListDialog dld = new DeckListDialog(parent, d, true);
        dld.setVisible(true);
    }
    
    /**
     * Creates new form DeckListDialog
     */
    private DeckListDialog(java.awt.Frame parent, Deck d, boolean needOnly) {
        super(parent, true);
        initComponents();
        setTitle(d +" Deck List");
        String t = "";
        for(Deck.DeckCard dc : d) {
            if(needOnly && dc.count > dc.card.count) {
                if(!t.isEmpty()) {
                    t += "\n";
                }
                t += (dc.count - dc.card.count) +"x " +dc.card.name;
            } else if(!needOnly) {
                if(!t.isEmpty()) {
                    t += "\n";
                }
                t += dc.count +"x " +dc.card.name;
            }
        }
        txtList.setText(t);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        txtList = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtList.setEditable(false);
        txtList.setColumns(20);
        txtList.setRows(5);
        jScrollPane1.setViewportView(txtList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtList;
    // End of variables declaration//GEN-END:variables
}