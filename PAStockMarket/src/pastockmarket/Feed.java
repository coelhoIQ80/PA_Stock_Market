/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pastockmarket;

/**
 * Define o comportamento de uma classe que disponibiliza feeds sobre um
 * determinado produto financeiro, e.g., Yahoo! Finance, Google, etc.
 * @author brunomnsilva
 */
public interface Feed {

        Historico<RegistoDia> getHistoricoRegistoDia(String simbolo)
                throws ProdutoFinanceiroInexistenteException, RecursoIndisponivelException;
        Historico<RegistoDia> getHistoricoRegistoDiaIntervalo(String simbolo, Intervalo intervalo)
                throws ProdutoFinanceiroInexistenteException, RecursoIndisponivelException;
        ProdutoFinanceiro getProdutoFinanceiro(String simbolo)
                throws ProdutoFinanceiroInexistenteException, RecursoIndisponivelException;
        
}
