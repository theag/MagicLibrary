/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author nbp184
 */
public class CardNameListModel extends AbstractListModel<String> {
    
    private String[] names;
    private int[] results;
    
    public CardNameListModel(ArrayList<String> names) {
        this.names = new String[names.size()];
        this.names = names.toArray(this.names);
        results = null;
    }

    @Override
    public int getSize() {
        if(results == null) {
            return 0;
        } else {
            return results.length;
        }
    }

    @Override
    public String getElementAt(int index) {
        if(results == null) {
            return names[index];
        } else {
            return names[results[index]];
        }
    }
    
    void clearSearch() {
        results = null;
        this.fireContentsChanged(this, 0, getSize());
    }
    
    void doSearch(String searchStr) {
        int[] temp = new int[names.length];
        int count = 0;
        for(int i = 0; i < names.length; i++) {
            if(names[i].toLowerCase().startsWith(searchStr)) {
                temp[count++] = i;
            }
        }
        results = new int[count];
        System.arraycopy(temp, 0, results, 0, count);
        this.fireContentsChanged(this, 0, getSize());
    }
    
}
