/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author nbp184
 */
public class ManaCellRenderer {
    
    private static final Border selected = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createEmptyBorder(2, 2, 2, 2));
    private static final Border empty = BorderFactory.createEmptyBorder(0, 2, 0, 0);

    private ArrayList<ChangeListener> listeners;
    
    public ManaCellRenderer() {
        listeners = new ArrayList<>();
    }
    
    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    public JPanel getListCellRendererComponent(String value, int index, boolean isSelected) {
        String[] mana = new String[]{value.substring(0, 1)};
        JPanel comp = new JPanel();
        comp.setBackground(Color.white);
        
        JLabel lbl = new JLabel("[ ]");
        comp.add(lbl);
        
        ManaPanel dot = new ManaPanel(mana);
        dot.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dotMouseClicked(index, value.charAt(0));
            }
        });
        comp.add(dot);
        
        JCheckBox phyrexian = new JCheckBox("Phyrexian");
        phyrexian.setSelected(value.length() > 1 && value.charAt(1) == 'P');
        phyrexian.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pyrexianActionPerformed(index, value.length() > 1 && value.charAt(1) == 'P');
            }
        });
        comp.add(phyrexian);
        
        if(isSelected) {
            comp.setBorder(selected);
        } else {
            comp.setBorder(empty);
        }
        
        return comp;
    }
    
    private void dotMouseClicked(int index, char value) {
        for(ChangeListener listener : listeners) {
            listener.dotClicked(index, value);
        }
    }
    
    private void pyrexianActionPerformed(int index, boolean value) {
        for(ChangeListener listener : listeners) {
            listener.phyrexianClicked(index, value);
        }
    }
    
    public static interface ChangeListener {
        void dotClicked(int index, char value);
        void phyrexianClicked(int index, boolean value);
    }
    
}
