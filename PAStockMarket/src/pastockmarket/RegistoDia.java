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
public class RegistoDia implements Serializable {
    
    private final float valorAbertura;
    private final float valorFecho;
    private final float valorFechoAjustado;
    private final float valorMaximo;
    private final float valorMinimo;
    private final int volume;

    public RegistoDia(float valorAbertura, float valorFecho, float valorFechoAjustado, float valorMaximo, float valorMinimo, int volume) {

        this.valorAbertura = valorAbertura;
        this.valorFecho = valorFecho;
        this.valorFechoAjustado = valorFechoAjustado;
        this.valorMaximo = valorMaximo;
        this.valorMinimo = valorMinimo;
        this.volume = volume;
    }

   
    /**
     * @return the valorAbertura
     */
    public float getValorAbertura() {
        return valorAbertura;
    }

    /**
     * @return the valorFecho
     */
    public float getValorFecho() {
        return valorFecho;
    }

    /**
     * @return the valorFechoAjustado
     */
    public float getValorFechoAjustado() {
        return valorFechoAjustado;
    }

    /**
     * @return the valorMaximo
     */
    public float getValorMaximo() {
        return valorMaximo;
    }

    /**
     * @return the valorMinimo
     */
    public float getValorMinimo() {
        return valorMinimo;
    }

    public String toString() {
        return String.format("Valor Abertura: %3.2f | Valor Min: %3.2f | Valor Max: %3.2f | Valor Fecho: %3.2f | Valor Fecho Ajustado: %3.2f | Volume: %d",
                valorAbertura, valorMinimo, valorMaximo, valorFecho, valorFechoAjustado, volume);
    }

    /**
     * @return the volume
     */
    public int getVolume() {
        return volume;
    }
}
