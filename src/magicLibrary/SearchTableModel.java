/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nbp184
 */
public class SearchTableModel extends AbstractTableModel {
        
    private final Library lib;
    private int sort;
    private int[] order;

    public SearchTableModel() {
        lib = Library.getInstance();
        sort = 1;
        order = new int[lib.resultSize()];
        for(int i = 0; i < order.length; i++) {
            order[i] = i;
        }
    }

    public void setSort(int col) {
        if(col+1 == Math.abs(sort)) {
            sort = -sort;
        } else {
            sort = col + 1;
        }
        if(sort != 1) {
            updateOrder();
        }
        this.fireTableStructureChanged();
    }

    public void updateStructure() {
        order = new int[lib.resultSize()];
        for(int i = 0; i < order.length; i++) {
            order[i] = i;
        }
        if(sort != 1) {
            updateOrder();
        }
        this.fireTableStructureChanged();
        this.fireTableDataChanged();
    }
    
    public void updateData() {
        this.fireTableDataChanged();
    }

    private void updateOrder() {
        int j;
        int temp;
        for(int i = 1; i < lib.resultSize(); i++) {
            temp = order[i];
            for(j = i - 1; j >= 0; j--) {
                if(orderCompare(lib.resultAt(order[j]),lib.resultAt(temp)) > 0) {
                    order[j+1] = order[j];
                } else {
                    break;
                }
            }
            order[j+1] = temp;
        }
    }

    private int orderCompare(Card card1, Card card2) {
        //name
        if(sort == -1) {
            return -card1.name.compareTo(card2.name);
        } 
        //todo:mana
        //cmc
        else if(sort == 3) {
            return card1.getCMC() - card2.getCMC();
        } else if(sort == -3) {
            return card2.getCMC() - card1.getCMC();
        }
        //type
        else if(sort == 4) {
            return card1.getTypeString().compareTo(card2.getTypeString());
        } else if(sort == -4) {
            return -card1.getTypeString().compareTo(card2.getTypeString());
        }
        //count
        else if(sort == 5) {
            return card1.count - card2.count;
        } else if(sort == -5) {
            return card2.count - card1.count;
        }
        //other
        else {
            return card1.compareTo(card2);
        }
    }

    @Override
    public int getRowCount() {
        return lib.resultSize();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        String rv = "";
        switch(columnIndex) {
            case 0:
                rv = "Name";
                break;
            case 1:
                rv = "Mana";
                break;
            case 2:
                rv = "CMC";
                break;
            case 3:
                rv = "Type";
                break;
            case 4:
                rv = "Count";
                break;
            default:
                return super.getColumnName(columnIndex);
        }
        if(columnIndex+1 == sort) {
            rv += " V";
        } else if(columnIndex+1 == -sort) {
            rv += " ^";
        }
        return rv;
    }
    
    public Card getCardAt(int rowIndex) {
        if(sort != 1) {
            rowIndex = order[rowIndex];
        }
        return lib.resultAt(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(sort != 1) {
            rowIndex = order[rowIndex];
        }
        Card card = lib.resultAt(rowIndex);
        switch(columnIndex) {
            case 0:
                return card.name;
            case 1:
                return card.manaCost;
            case 2:
                for(String t : card.type) {
                    if(t.compareToIgnoreCase("land") == 0) {
                        return "";
                    }
                }
                return card.getCMC();
            case 3:
                return card.getTypeString();
            case 4:
                return card.count;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

}
