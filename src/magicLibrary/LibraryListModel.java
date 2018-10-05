/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import javax.swing.AbstractListModel;

/**
 *
 * @author nbp184
 */
public class LibraryListModel extends AbstractListModel<Card> {
    
    public LibraryListModel() {}

    @Override
    public int getSize() {
        return Library.getInstance().resultSize();
    }

    @Override
    public Card getElementAt(int index) {
        return Library.getInstance().resultAt(index);
    }
    
    public void fireLibraryChanged() {
        this.fireContentsChanged(this, 0, getSize());
    }
        
}
