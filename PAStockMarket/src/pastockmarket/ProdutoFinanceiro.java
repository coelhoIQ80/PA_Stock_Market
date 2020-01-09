/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pastockmarket;

import java.util.Observable;

/**
 *
 * @author brunomnsilva
 */
public class ProdutoFinanceiro extends Observable {

    private final String simbolo;
    private final String nome;
    private final String capitalMercado;
    private final String bolsaTransaccao;
    private final Historico<RegistoDia> historico;

    public ProdutoFinanceiro(String simbolo, String nome, String capitalMercado, String bolsaTransaccao) {
        this.simbolo = simbolo.toUpperCase();
        this.nome = nome;
        this.capitalMercado = capitalMercado;
        //this.numeroAccoes = numeroAccoes;
        this.bolsaTransaccao = bolsaTransaccao;

        historico = new HistoricoAdapter<RegistoDia>();
    }

    public String getBolsaTransaccao() {
        return bolsaTransaccao;
    }

    /**
     * @return the simbolo
     */
    public String getSimbolo() {
        return simbolo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the capitalMercado
     */
    public String getCapitalMercado() {
        return capitalMercado;
    }

    /**
     * @return the historico
     */
    public Historico<RegistoDia> getHistorico() {
        return historico;
    }

    /**
     * @param historico the historico to set
     */
    public void acrescentaHistorico(Historico<RegistoDia> novoHistorico) {
        this.historico.adiciona(novoHistorico);

        this.setChanged();
        this.notifyObservers();
    }

    public Data getUltimaDataHistorico() {
        if( historico.tamanho() == 0)
            return new Data(0, 0, 0);

        Iterador<MembroHistorico<RegistoDia>> it = historico.getIteradorInvertido();

        MembroHistorico<RegistoDia> m = it.next(); //ultimo

        return m.getData();
    }

    public Float getUltimoValorFecho() {
        if( historico.tamanho() == 0)
            return 0f;

        Iterador<MembroHistorico<RegistoDia>> it = historico.getIteradorInvertido();

        MembroHistorico<RegistoDia> m = it.next(); //ultimo

        return m.getMembro().getValorFechoAjustado();
    }

    public Float getUltimaVariacaoAbsoluto() {
        if(historico.tamanho() <= 1)
            return 0f;

        //obter ultimos dois dias e calcular
        Iterador<MembroHistorico<RegistoDia>> it = historico.getIteradorInvertido();
        float ultimo = it.next().getMembro().getValorFechoAjustado();
        float penultimo = it.next().getMembro().getValorFechoAjustado();

        return (ultimo - penultimo);

    }

    public Float getUltimaVariacaoPorcento() {
        float valAbsoluto = getUltimaVariacaoAbsoluto();
        float ultimo = getUltimoValorFecho();

        try {
            return valAbsoluto / ultimo * 100;
        } catch (ArithmeticException e) {
            return 0.0f;
        }
    }

    public Integer getUltimoValorVolume() {
        if( historico.tamanho() == 0)
            return 0;

        Iterador<MembroHistorico<RegistoDia>> it = historico.getIteradorInvertido();

        MembroHistorico<RegistoDia> m = it.next(); //ultimo

        return m.getMembro().getVolume();
    }

    @Override
    public String toString() {
        return String.format("Simbolo: %s \nNome: %s \nCapital Mercado: %s \nBolsa: %s \nTamanho Historico: \n%s\n",
                simbolo, nome, capitalMercado, bolsaTransaccao, historico.tamanho());
    }

    public String toStringResumo() {
        return String.format("%-6s | %-25s | %-12s | %3.2f | %3.2f (%3.2f%%) | %10d",
                simbolo,
                nome,
                getUltimaDataHistorico(),
                getUltimoValorFecho(),
                getUltimaVariacaoAbsoluto(),
                getUltimaVariacaoPorcento(),
                getUltimoValorVolume());
    }

}