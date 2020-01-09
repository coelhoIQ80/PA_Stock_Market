/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pastockmarket;

import java.io.BufferedReader;
import java.io.Serializable;
import java.io.StringReader;
import java.util.Stack;

/**
 *
 * @author brunomnsilva
 */
public class YahooFinanceFeed implements Feed, Serializable {

    //definir urls das feeds --- %? sao placeholders
    private final String urlHistoricoCompleto = "http://ichart.yahoo.com/table.csv?s=%s";
    private final String urlHistoricoIntervalo = "http://ichart.yahoo.com/table.csv?s=%s&a=%d&b=%d&c=%d&d=%d&e=%d&f=%d&g=d";
    private final String urlInformacao = "http://finance.yahoo.com/d/quotes.csv?s=%s&f=nj1x";

    public Historico<RegistoDia> getHistoricoRegistoDia(String simbolo)
            throws ProdutoFinanceiroInexistenteException, RecursoIndisponivelException {
        //obter string correspondente para importacao
        String url = String.format(urlHistoricoCompleto, simbolo);

        UrlDownloader dl = new UrlDownloader(url); //pode lançar excepção que é cascaded para "fora"

        String ficheiro = dl.obterFicheiro();
        //fazer o parse do ficheiro

        Historico<RegistoDia> historico = new HistoricoAdapter<RegistoDia>();

        //vamos utilizar uma Pilha para inverter os valores devolvidos pelo histórico
        Stack<MembroHistorico<RegistoDia>> pilha = new Stack<MembroHistorico<RegistoDia>>();

        BufferedReader buf = new BufferedReader(new StringReader(ficheiro));
        try {
            //vamos descartar a primeira linha, que contem o cabeçalho
            buf.readLine();
            String linha;
            while( (linha = buf.readLine()) != null) {
                //fazer o split dos campos por virgula (CSV) e converter

                //TODO: try-catch por linha. Assim nao perdemos o historico todo
                //caso haja um erro numa linha por algum motivo.
                String[] campos = linha.split(",");
                String data = campos[0];
                float open = Float.parseFloat(campos[1]);
                float high = Float.parseFloat(campos[2]);
                float low = Float.parseFloat(campos[3]);
                float close = Float.parseFloat(campos[4]);
                int volume = Integer.parseInt(campos[5]);
                float adj_close = Float.parseFloat(campos[6]);

                //criar registo dia para colocar no historico
                RegistoDia dia = new RegistoDia(open, close, adj_close, high, low, volume);

                pilha.add(new MembroHistorico<RegistoDia>(new Data(data), dia));

            }

            while(!pilha.empty()) {
                historico.adiciona( pilha.pop() );
            }

        } catch (Exception ex) {
            //estamos a ler de uma string, nao deverá haver problema aí.
            //Apenas
        }

        return historico;
    }

    public Historico<RegistoDia> getHistoricoRegistoDiaIntervalo(String simbolo, Intervalo intervalo)
            throws ProdutoFinanceiroInexistenteException, RecursoIndisponivelException {
        //obter string correspondente para importacao
        Data i = intervalo.getInicio();
        Data f = intervalo.getFim();

        String url = String.format(urlHistoricoIntervalo, simbolo, i.getMes()-1, i.getDia(), i.getAno(),
                                                                f.getMes()-1, f.getDia(), f.getAno());

        UrlDownloader dl = new UrlDownloader(url); //pode lançar excepção que é cascaded para "fora"

        String ficheiro = dl.obterFicheiro();
        //fazer o parse do ficheiro

        Historico<RegistoDia> historico = new HistoricoAdapter<RegistoDia>();

        //vamos utilizar uma Pilha para inverter os valores devolvidos pelo histórico
        Stack<MembroHistorico<RegistoDia>> pilha = new Stack<MembroHistorico<RegistoDia>>();

        BufferedReader buf = new BufferedReader(new StringReader(ficheiro));
        try {
            //vamos descartar a primeira linha, que contem o cabeçalho
            buf.readLine();
            String linha;
            while( (linha = buf.readLine()) != null) {
                //fazer o split dos campos por virgula (CSV) e converter

                //TODO: try-catch por linha. Assim nao perdemos o historico todo
                //caso haja um erro numa linha por algum motivo.
                String[] campos = linha.split(",");
                String data = campos[0];
                float open = Float.parseFloat(campos[1]);
                float high = Float.parseFloat(campos[2]);
                float low = Float.parseFloat(campos[3]);
                float close = Float.parseFloat(campos[4]);
                int volume = Integer.parseInt(campos[5]);
                float adj_close = Float.parseFloat(campos[6]);

                //criar registo dia para colocar no historico
                RegistoDia dia = new RegistoDia(open, close, adj_close, high, low, volume);

                pilha.add(new MembroHistorico<RegistoDia>(new Data(data), dia));

            }

            while(!pilha.empty()) {
                historico.adiciona( pilha.pop() );
            }

        } catch (Exception ex) {
            //estamos a ler de uma string, nao deverá haver problema aí.
            //Apenas
        }

        return historico;
    }

    public ProdutoFinanceiro getProdutoFinanceiro(String simbolo)
            throws ProdutoFinanceiroInexistenteException, RecursoIndisponivelException {
        //obter string correspondente para importacao
        String url = String.format(urlInformacao, simbolo);

        UrlDownloader dl = new UrlDownloader(url); //pode lançar excepção que é cascaded para "fora"

        String ficheiro = dl.obterFicheiro();
        //fazer o parse do ficheiro


        BufferedReader buf = new BufferedReader(new StringReader(ficheiro));
        try {
            //só é devolvida uma linha com a info pretendida
            String[] campos = buf.readLine().split(",");

            String nome = campos[0].replace("\"", "");
            String capital = campos[1];            
            String exchange = campos[2].replace("\"", "");

            return new ProdutoFinanceiro(simbolo, nome, capital, exchange);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }



}
