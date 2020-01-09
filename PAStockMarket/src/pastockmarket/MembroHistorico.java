/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pastockmarket;

import java.io.Serializable;

/**
 *
 * @author brunomnsilva
 */
public class MembroHistorico<T> implements Serializable {

    private final Data data;
    private final T membro;

    public MembroHistorico(Data data, T membro) {
        this.data = data;
        this.membro = membro;
    }

    /**
     * @return the data
     */
    public Data getData() {
        return data;
    }    

    /**
     * @return the membro
     */
    public T getMembro() {
        return membro;
    }
 
    
}
