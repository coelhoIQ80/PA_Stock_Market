/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pastockmarket;

/**
 *
 * @author brunomnsilva
 */
public class ProdutoFinanceiroInexistenteException extends Exception {

    public ProdutoFinanceiroInexistenteException(String simbolo) {
        super("Produto financeiro inexistente: " + simbolo);
    }

}
