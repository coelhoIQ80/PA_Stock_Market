/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pastockmarket;

import java.util.Observable;


/**
 * Esta interface descreve um TAD que é capaz de guardar objectos associados
 * a uma data. Utiliza tipos genéricos por forma a guardar qualquer tipo de
 * objecto. Não impõe restrições em termos de mapeamento, i.e., associado a
 * uma data pode haver mais do que um objecto e um objecto pode estar associado
 * a mais do que uma data.
 *
 * @author brunomnsilva
 */
public interface Historico<T>  {
    //devolve o tamanho do historico
    public int tamanho();
    //verifica se esta vazio
    public  boolean estaVazio();
    //adiciona o objecto associado a uma data. Terá de ser criado um objecto
    //MembroHistorico<T> para posterior inclusao no historico
    public  boolean adiciona(Data data, T membro);
    //adiona um membro ao historico
    public  boolean adiciona(MembroHistorico<T> novo);
    //adiciona os elementos de um historico do mesmo tipo
    public  boolean adiciona(Historico<T> outroHistorico);
    //devolve um iterador para todos os membros deste historico
    public  Iterador<MembroHistorico<T>> getIterador();
    //devolve um iterador para todos os membros deste historico
    public  Iterador<MembroHistorico<T>> getIteradorInvertido();
    //devolve o historico compreendido num intervalo de tempo
    public  Historico<T> obtemIntervalo(Intervalo intervalo);
    //devolve o historico associado a apenas uma data
    public  Historico<T> obtemMembros(Data data);

}