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
public abstract class LibraryPanel extends javax.swing.JPanel {
    
    public abstract void fireLibraryChanged();
    
    public void addChangePanelListener(ChangePanelListener listener) {
        listenerList.add(ChangePanelListener.class, listener);
    }
    
    public void fireChangePanel(String newPanelName) {
        for(ChangePanelListener listener : listenerList.getListeners(ChangePanelListener.class)) {
            listener.changePanel(newPanelName);
        }
    }
    
    public interface ChangePanelListener extends java.util.EventListener {
        public void changePanel(String newPanelName);
    }
    
}
