/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author nbp184
 */
public class ManaCurvePanel extends javax.swing.JPanel {

    private int[] curve;
    private int max;
    
    /**
     * Creates new form ManaCurvePanel
     * @param curve
     */
    public ManaCurvePanel(int[] curve) {
        initComponents();
        this.curve = new int[curve.length];
        max = 0;
        for(int i = 0; i < curve.length; i++) {
            this.curve[i] = curve[i];
            if(curve[i] > max) {
                max = curve[i];
            }
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

        setPreferredSize(new java.awt.Dimension(225, 123));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setCurve(int[] curve) {
        this.curve = new int[curve.length];
        max = 0;
        for(int i = 0; i < curve.length; i++) {
            this.curve[i] = curve[i];
            if(curve[i] > max) {
                max = curve[i];
            }
        }
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        FontMetrics fm = g.getFontMetrics();
        int xAxis = getHeight() - 8 - fm.getAscent()*2;
        int yAxis = 8 + fm.stringWidth(""+max) + fm.stringWidth("C");
        int dx = (getWidth() - 2 - yAxis)/curve.length;
        int dy = (xAxis - 2)/(max+1);
        g.setColor(Color.BLACK);
        //x-axis
        g.drawLine(2, xAxis, getWidth() - 2, xAxis);
        int x;
        for(int i = 0; i < curve.length; i++) {
            x = yAxis + dx/2 + dx*i;
            g.drawLine(x, xAxis, x, xAxis + 2);
            g.drawString(""+i, x - fm.stringWidth(""+i)/2, xAxis + 2 + fm.getAscent());
        }
        g.drawString("CMC", (getWidth() - 2 - yAxis - fm.stringWidth("CMC"))/2 + yAxis, getHeight() - 4);
        //y-axis
        g.setColor(Color.LIGHT_GRAY);
        int y;
        for(int i = 1; i <= max; i++) {
            y = xAxis - dy*i;
            g.drawLine(yAxis, y, getWidth() - 2, y);
        }
        g.setColor(Color.BLACK);
        g.drawLine(yAxis, 2, yAxis, getHeight() - 2);
        for(int i = 1; i <= max; i++) {
            y = xAxis - dy*i;
            g.drawLine(yAxis - 2, y, yAxis, y);
            g.drawString(""+i, yAxis - 2 - fm.stringWidth(""+i), y + fm.getAscent()/2);
        }
        g.drawString("C", 4, (xAxis - 2 + fm.getAscent())/2 - 2*fm.getAscent());
        g.drawString("o", 4, (xAxis - 2 + fm.getAscent())/2 - fm.getAscent());
        g.drawString("u", 4, (xAxis - 2 + fm.getAscent())/2);
        g.drawString("n", 4, (xAxis - 2 + fm.getAscent())/2 + fm.getAscent());
        g.drawString("t", 4, (xAxis - 2 + fm.getAscent())/2 + 2*fm.getAscent());
        //drawing bars
        for(int i = 0; i < curve.length; i++) {
            x = i*dx + yAxis;
            g.setColor(Color.red);
            g.fillRect(x, xAxis - curve[i]*dy, dx, curve[i]*dy);
            g.setColor(Color.BLACK);
            g.drawRect(x, xAxis - curve[i]*dy, dx, curve[i]*dy);
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
