/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nbp184
 */
public class DeckPanel extends javax.swing.JPanel {

    /**
     * Creates new form DeckPanel
     */
    public DeckPanel() {
        initComponents();
        setModels(true);
        tblDeck.setRowHeight(ManaPanel.DOT_SIZE + 1 + 4);
        tblDeck.getColumnModel().getColumn(1).setCellRenderer(new ManaTableCellRenderer());
        //todo: stretch some colums and shrink others, columns below to be shrunk
        tblDeck.getColumnModel().getColumn(2).setWidth(50);
        tblDeck.getColumnModel().getColumn(4).setWidth(50);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbDecks = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDeck = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCounts = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSpreads = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        cbDecks.setModel(new DefaultComboBoxModel(Library.getInstance().getDeckVector()));
        cbDecks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDecksActionPerformed(evt);
            }
        });

        tblDeck.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblDeck);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
        );

        jLabel1.setText("Mana Curve");

        jLabel2.setText("Colour Counts");

        tblCounts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblCounts);

        jLabel3.setText("Colour Spread");

        tblSpreads.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblSpreads);

        jLabel4.setText("Land");

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane4)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbDecks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbDecks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbDecksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDecksActionPerformed
        setModels(false);
    }//GEN-LAST:event_cbDecksActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbDecks;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblCounts;
    private javax.swing.JTable tblDeck;
    private javax.swing.JTable tblSpreads;
    // End of variables declaration//GEN-END:variables

    private void setModels(boolean setting) {
        Library l = Library.getInstance();
        ArrayList<Card> cards = new ArrayList<>();
        String deckName = (String)cbDecks.getSelectedItem();
        for(Card c : l) {
            if(c.decks.contains(deckName)) {
                cards.add(c);
            }
        }
        int[] counts = new int[cards.size()];
        ArrayList<String> countNames = new ArrayList<>();
        ArrayList<Integer> countCounts = new ArrayList<>();
        ArrayList<String> spreadNames = new ArrayList<>();
        ArrayList<Integer> spreadCounts = new ArrayList<>();
        int mana, index;
        String manaName;
        for(int i = 0; i < cards.size(); i++) {
            counts[i] = 0;
            for(String d : cards.get(i).decks) {
                if(d.compareTo(deckName) == 0) {
                    counts[i]++;
                }
            }
            mana = cards.get(i).getColours();
            manaName = getManaName(mana);
            if(!manaName.isEmpty()) {
                if(countNames.contains(manaName)) {
                    index = countNames.indexOf(manaName);
                    countCounts.set(index, countCounts.get(index)+1);
                } else {
                    countNames.add(manaName);
                    countCounts.add(1);
                }
            }
            for(String m : cards.get(i).manaCost) {
                manaName = getManaName(m);
                if(!manaName.isEmpty()) {
                    if(spreadNames.contains(manaName)) {
                        index = spreadNames.indexOf(manaName);
                        spreadCounts.set(index, spreadCounts.get(index)+1);
                    } else {
                        spreadNames.add(manaName);
                        spreadCounts.add(1);
                    }
                }
            }
        }
        if(setting) {
            tblDeck.setModel(new DeckTableModel(cards, counts));
            tblCounts.setModel(new ColourCountModel(countNames, countCounts));
            tblSpreads.setModel(new ColourCountModel(spreadNames, spreadCounts));
        } else {
            DeckTableModel m1 = (DeckTableModel)tblDeck.getModel();
            m1.update(cards, counts);
            ColourCountModel m2 = (ColourCountModel)tblCounts.getModel();
            m2.update(countNames, countCounts);
            m2 = (ColourCountModel)tblSpreads.getModel();
            m2.update(spreadNames, spreadCounts);
        }
    }
    
    private String getManaName(int mana) {
        String rv = "";
        if((mana & 1) == 1) {
            if(!rv.isEmpty()) {
                rv += "/";
            }
            rv += "White";
        }
        if((mana & 2) == 2) {
            if(!rv.isEmpty()) {
                rv += "/";
            }
            rv += "Blue";
        }
        if((mana & 4) == 4) {
            if(!rv.isEmpty()) {
                rv += "/";
            }
            rv += "Black";
        }
        if((mana & 8) == 8) {
            if(!rv.isEmpty()) {
                rv += "/";
            }
            rv += "Red";
        }
        if((mana & 16) == 16) {
            if(!rv.isEmpty()) {
                rv += "/";
            }
            rv += "Green";
        }
        return rv;
    }
    
    private String getManaName(String mana) {
        String rv = "";
        for(int i = 0; i < mana.length(); i++) {
            if(!rv.isEmpty() && mana.charAt(i) != 'P') {
                rv += "/";
            }
            switch(mana.charAt(i)) {
                case 'W':
                    rv += "White";
                    break;
                case 'U':
                    rv += "Blue";
                    break;
                case 'B':
                    rv += "Black";
                    break;
                case 'R':
                    rv += "Red";
                    break;
                case 'G':
                    rv += "Green";
                    break;
            }
        }
        return rv;
    }

    private static class DeckTableModel extends AbstractTableModel {
        
        private Card[] cards;
        private int[] counts;
        
        public DeckTableModel(ArrayList<Card> cards, int[] counts) {
            this.cards = new Card[cards.size()];
            cards.toArray(this.cards);
            this.counts = counts;
        }
        
        public void update(ArrayList<Card> cards, int[] counts) {
            this.cards = new Card[cards.size()];
            cards.toArray(this.cards);
            this.counts = counts;
            this.fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return cards.length;
        }

        @Override
        public int getColumnCount() {
            return 5;
        }
        
        @Override
        public String getColumnName(int columnIndex) {
            switch(columnIndex) {
                case 0:
                    return "Name";
                case 1:
                    return "Mana";
                case 2:
                    return "CMC";
                case 3:
                    return "Type";
                case 4:
                    return "Count";
                default:
                    return super.getColumnName(columnIndex);
            }
        }
        
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch(columnIndex) {
                case 0:
                    return cards[rowIndex].name;
                case 1:
                    return cards[rowIndex].manaCost;
                case 2:
                    for(String t : cards[rowIndex].type) {
                        if(t.compareToIgnoreCase("land") == 0) {
                            return "";
                        }
                    }
                    return cards[rowIndex].getCMC();
                case 3:
                    return cards[rowIndex].getTypeString();
                case 4:
                    return counts[rowIndex];
                default:
                    return null;
            }
        }
        
    }
    
    private static class ColourCountModel extends AbstractTableModel {

        private String[] names;
        private Integer[] counts;
       
        ColourCountModel(ArrayList<String> names, ArrayList<Integer> counts) {
            this.names = new String[names.size()];
            names.toArray(this.names);
            this.counts = new Integer[counts.size()];
            counts.toArray(this.counts);
        }
        
        public void update(ArrayList<String> names, ArrayList<Integer> counts) {
            this.names = new String[names.size()];
            names.toArray(this.names);
            this.counts = new Integer[counts.size()];
            counts.toArray(this.counts);
            this.fireTableDataChanged();
        }
        
        @Override
        public int getRowCount() {
            return names.length;
        }

        @Override
        public int getColumnCount() {
            return 2;
        }
        
        @Override
        public String getColumnName(int columnIndex) {
            switch(columnIndex) {
                case 0:
                    return "Colour";
                case 1:
                    return "Count";
                default:
                    return super.getColumnName(columnIndex);
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch(columnIndex) {
                case 0:
                    return names[rowIndex];
                case 1:
                    return counts[rowIndex];
                default:
                    return null;
            }
        }
        
    }

}