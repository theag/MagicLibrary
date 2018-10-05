/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author nbp184
 */
public class CardCellRenderer implements ListCellRenderer<Card> {

    private final JLabel label;
    
    public CardCellRenderer() {
        label = new JLabel();
        label.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Card> list, Card value, int index, boolean isSelected, boolean cellHasFocus) {
        if(isSelected) {
            label.setText("X " +value.name);
        } else {
            label.setText("  " +value.name);
        }
        return label;
    }
    
}
