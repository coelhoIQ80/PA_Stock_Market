/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pastockmarket;

/**
 *
 * @author brunomnsilva
 */
public class Intervalo {
    private Data inicio;
    private Data fim;

    public Intervalo(Data inicio, Data fim) throws IllegalArgumentException {
        if(inicio.diferenca(fim) < 0) {
            throw new IllegalArgumentException("Data inicio maior que data fim");
        }
        this.inicio = inicio;
        this.fim = fim;
    }

    /**
     * @return the inicio
     */
    public Data getInicio() {
        return inicio;
    }

    /**
     * @return the fim
     */
    public Data getFim() {
        return fim;
    }
    
}
