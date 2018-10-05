/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import magicLibrary.ManaCellRenderer.ChangeListener;

/**
 *
 * @author nbp184
 */
public class EditManaList extends JPanel {
    
    private static final String CYCLE = "BUGRW";
    
    private ArrayList<String> mana;
    private ManaCellRenderer mcr;
    private int selected = -1;

    public EditManaList(String[] mana) {
        this.setBackground(Color.red);
        this.setLayout(new FlowLayout());
        mcr = new ManaCellRenderer();
        mcr.addChangeListener(new ChangeListener() {

            @Override
            public void dotClicked(int index, char value) {
                manaDotClicked(index, value);
            }

            @Override
            public void phyrexianClicked(int index, boolean value) {
                manaPhyrexianClicked(index, value);
            }
        });
        this.mana = new ArrayList<>();
        JPanel ele;
        for(String m : mana) {
            if(CYCLE.contains(""+m.charAt(0))) {
                ele = mcr.getListCellRendererComponent(m, this.mana.size(), false);
                ele.setName(""+this.mana.size());
                ele.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        elementClicked(evt);
                    }
                });
                add(ele);
                this.mana.add(m);
            }
        }
    }
    
    private void manaDotClicked(int index, char value) {
        int mi = CYCLE.indexOf(value);
        mi = (mi+1)%CYCLE.length();
        String m = mana.get(index);
        m = CYCLE.substring(mi, mi+1) + m.substring(1);
        mana.set(index, m);
        update(index);
    }
    
    private void manaPhyrexianClicked(int index, boolean value) {
        
    }

    private void update(int index) {
        this.remove(index);
        JPanel ele = mcr.getListCellRendererComponent(mana.get(index), index, index == selected);
        ele.setName(""+index);
        ele.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                elementClicked(evt);
            }
        });
        this.add(ele, index);
        ele.setSize(ele.getPreferredSize());
        this.invalidate();
        this.repaint();
    }

    private void elementClicked(java.awt.event.MouseEvent evt) {
        int index = Integer.parseInt(((JPanel)evt.getSource()).getName());
        if(selected == index) {
            selected = -1;
        } else {
            selected = index;
        }
        update(index);
    }

}
