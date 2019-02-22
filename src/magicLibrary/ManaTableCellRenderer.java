/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author nbp184
 */
public class ManaTableCellRenderer implements TableCellRenderer {

        private static final Color bg_sel = new Color(57, 105, 138);
        private static final Color bg_alt = new Color(242, 242, 242);
        
        private ManaPanel mp;
        
        public ManaTableCellRenderer() {
            mp = new ManaPanel(0, 2);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            mp.setBorder(0, 0);
            if(value instanceof String) {
                mp.setManaCost(new String[]{(String)value});
            } else {
                mp.setManaCost((String[])value);
            }
            mp.setBorder((table.getColumnModel().getColumn(column).getWidth() - mp.getPreferredSize().width - 1)/2, 2);
            if(isSelected) {
                mp.setBackground(bg_sel);
            } else if(row%2 == 1) {
                mp.setBackground(bg_alt);
            } else {
                mp.setBackground(Color.white);
            }
            return mp;
        }
    }
