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
public class Data implements Comparable<Data>, Serializable {
    private final int dia;
    private final int mes;
    private final int ano;

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public Data(String dataFormatada) throws IllegalArgumentException {
        //espera-se ANO-MES-DIA
        String[] campos = dataFormatada.split("-");
        try {
            this.ano = Integer.parseInt(campos[0]);
            this.mes = Integer.parseInt(campos[1]);
            this.dia = Integer.parseInt(campos[2]);
        } catch (Exception e) {
            throw new IllegalArgumentException("A data fornecida não está num formato válido YYYY-MM-DD: recebi: " + dataFormatada);
        }
    }

    @Override
    public boolean equals(Object d) {
        if(!(d instanceof Data))
            return false;
        else {
            Data data = (Data)d;
            if(this.diferenca(data) == 0)
                return true;
            else
                return false;
        }
    }

    //este metodo é gerado automaticamente pelo netbeans
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.dia;
        hash = 89 * hash + this.mes;
        hash = 89 * hash + this.ano;
        return hash;
    }
    
    /**
     * @return the dia
     */
    public int getDia() {
        return dia;
    }
   

    /**
     * @return the mes
     */
    public int getMes() {
        return mes;
    }  

    /**
     * @return the ano
     */
    public int getAno() {
        return ano;
    }

  
    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d", ano, mes, dia);
    }

    public int diferenca(Data data) {
        return this.numeroDias() - data.numeroDias();
    }

    public int numeroDias() {
        int m = (this.mes + 9) % 12;
        int y= this.ano - mes/10;
        return 365*y + y/4 - y/100 + y/400 + (m*306 + 5)/10 + ( dia - 1 );
    }

    public int compareTo(Data o) {
        return diferenca(o);
    }
}
