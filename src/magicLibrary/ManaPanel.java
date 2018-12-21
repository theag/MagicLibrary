/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import myutil.MyMath;

/**
 *
 * @author nbp184
 */
public class ManaPanel extends javax.swing.JPanel {

    private static final Color MANA_UNTYPED = new Color(0xD8D8D8);
    private static final Color MANA_RED = new Color(0xF6A28B);
    private static final Color MANA_BLUE = new Color(0x9FDCF9);
    private static final Color MANA_GREEN = new Color(0x8CD3AB);
    private static final Color MANA_WHITE = new Color(0xFFFFE1);
    private static final Color MANA_BLACK = new Color(0xC4B8B6);
    
    public static final int DOT_SIZE = 18;
    private static final int DOT_MARGIN = 1;
    
    private String[] manaCost;
    private int borderX;
    private int borderY;
    
    public ManaPanel(String[] manaCost) {
        initComponents();
        this.manaCost = manaCost;
        borderX = 0;
        borderY = 0;
        setPreferredSize(new Dimension(manaCost.length*(DOT_SIZE + 1 + DOT_MARGIN) + 1, DOT_SIZE + 1));
        setBackground(Color.white);
    }
    
    public ManaPanel(int borderX, int borderY) {
        initComponents();
        this.manaCost = new String[0];
        this.borderX = borderX;
        this.borderY = borderY;
        setPreferredSize(new Dimension((DOT_SIZE + 1 + DOT_MARGIN) + 1 + 2*borderX, DOT_SIZE + 1 + 2*borderY));
        setBackground(Color.white);
    }
    
    public ManaPanel() {
        initComponents();
        this.manaCost = new String[0];
        borderX = 0;
        borderY = 0;
        setPreferredSize(new Dimension((DOT_SIZE + 1 + DOT_MARGIN) + 1, DOT_SIZE + 1));
        setBackground(Color.white);
    }
    
    public void setBorder(int borderX, int borderY) {
        this.borderX = borderX;
        this.borderY = borderY;
        setPreferredSize(new Dimension((DOT_SIZE + 1 + DOT_MARGIN) + 1 + 2*borderX, DOT_SIZE + 1 + 2*borderY));
        repaint();
    }
    
    public int getBorderX() {
        return borderX;
    }
    
    public int getBorderY() {
        return borderY;
    }
    
    public void setManaCost(String[] manaCost) {
        this.manaCost = manaCost;
        Dimension d = getSize();
        d.width = manaCost.length*(DOT_SIZE + 1 + DOT_MARGIN) + 1 + 2*borderX;
        setPreferredSize(d);
        repaint();
    }
    
    public String[] getManaCost() {
        return manaCost;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        if(borderX > 0 || borderY > 0) {
            g.translate(borderX, borderY);
        }
        if(manaCost.length == 0) {
            g.setColor(Color.gray);
            g.drawArc(0, 0, DOT_SIZE, DOT_SIZE, 0, 360);
            return;
        }
        int x = 0;
        boolean untyped;
        FontMetrics fm = g.getFontMetrics();
        for(String mana : manaCost) {
            untyped = false;
            switch(mana.charAt(0)) {
                case 'R':
                    g.setColor(MANA_RED);
                    break;
                case 'U':
                    g.setColor(MANA_BLUE);
                    break;
                case 'G':
                    g.setColor(MANA_GREEN);
                    break;
                case 'W':
                    g.setColor(MANA_WHITE);
                    break;
                case 'B':
                    g.setColor(MANA_BLACK);
                    break;
                default:
                    g.setColor(MANA_UNTYPED);
                    untyped = true;
                    break;
            }
            if(mana.length() == 2 && mana.charAt(1) != 'P' && mana.charAt(1) >= 'B' && mana.charAt(1) <= 'W') {
                //hybrid mana
                g.drawArc(x, 0, DOT_SIZE, DOT_SIZE, 45, 180);
                g.fillArc(x, 0, DOT_SIZE, DOT_SIZE, 45, 180);
                if(untyped) {
                    g.setColor(Color.black);
                    g.drawString(mana.substring(0, 1), x + (DOT_SIZE/2 - fm.stringWidth(mana))/2 + 1, (DOT_SIZE/2 + fm.getAscent())/2 - 1);
                }
                //second half
                switch(mana.charAt(1)) {
                    case 'R':
                        g.setColor(MANA_RED);
                        break;
                    case 'U':
                        g.setColor(MANA_BLUE);
                        break;
                    case 'G':
                        g.setColor(MANA_GREEN);
                        break;
                    case 'W':
                        g.setColor(MANA_WHITE);
                        break;
                    case 'B':
                        g.setColor(MANA_BLACK);
                        break;
                }
                g.drawArc(x, 0, DOT_SIZE, DOT_SIZE, 225, 180);
                g.fillArc(x, 0, DOT_SIZE, DOT_SIZE, 225, 180);
            } else {
                g.drawArc(x, 0, DOT_SIZE, DOT_SIZE, 0, 360);
                g.fillArc(x, 0, DOT_SIZE, DOT_SIZE, 0, 360);
                if(untyped) {
                    g.setColor(Color.black);
                    g.drawString(mana, x + (DOT_SIZE - fm.stringWidth(mana))/2 + 1, (DOT_SIZE + fm.getAscent())/2 - 1);
                } else if(mana.length() > 1 && mana.charAt(1) == 'P') {
                    g.setColor(Color.black);
                    g.drawLine(x + DOT_SIZE/2, 1, x + DOT_SIZE/2, DOT_SIZE - 1);
                    g.drawArc(x + MyMath.round(DOT_SIZE/4.0), MyMath.round(DOT_SIZE/4.0), DOT_SIZE/2, DOT_SIZE/2, 0, 360);
                }
            }
            x += DOT_SIZE + 1 + DOT_MARGIN;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(25, 25));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
