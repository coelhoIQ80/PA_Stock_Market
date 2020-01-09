/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pastockmarket;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author brunomnsilva
 */
public class HistoricoAdapter<T> implements Historico<T>, Serializable {

    //colecção de suporte. Alunos deverão utilizar uma sua
    private LinkedList<MembroHistorico<T>> historico;

    public HistoricoAdapter() {
        historico = new LinkedList<MembroHistorico<T>>();
    }

    public boolean adiciona(Data data, T membro) {
        return historico.add(new MembroHistorico<T>(data, membro));
    }

    public boolean adiciona(MembroHistorico<T> novo) {
        return historico.add(novo);
    }

    public Iterador<MembroHistorico<T>> getIterador() {
        return new IteradorHistorico<MembroHistorico<T>>();
    }

    public Historico<T> obtemIntervalo(Intervalo intervalo) {
        //criar um novo historico
        Historico<T> histIntervalo = new HistoricoAdapter<T>();

        Data inicio = intervalo.getInicio();
        Data fim = intervalo.getFim();

        //obter iterador e copiar membros dentro do intervalo
        Iterador<MembroHistorico<T>> it = this.getIterador();
        while(it.hasNext()) {
            MembroHistorico<T> m = it.next();
            //testar se está dentro do intervalo
            Data dataMembro = m.getData();

            if(dataMembro.compareTo(inicio) >= 0 && dataMembro.compareTo(fim) <= 0)
                histIntervalo.adiciona(m);

        }

        return histIntervalo;
    }

    public Historico<T> obtemMembros(Data data) {
        //criar um novo historico
        Historico<T> histIntervalo = new HistoricoAdapter<T>();

        //obter iterador e copiar membros dentro do intervalo
        Iterador<MembroHistorico<T>> it = this.getIterador();
        while(it.hasNext()) {
            MembroHistorico<T> m = it.next();
            //testar se está dentro do intervalo
            Data dataMembro = m.getData();

            if(dataMembro.equals(data))
                histIntervalo.adiciona(m);

        }

        return histIntervalo;
    }

    public int tamanho() {
        return historico.size();
    }

    public boolean estaVazio() {
        return historico.isEmpty();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(10000);
        Iterador<MembroHistorico<T>> it = this.getIterador();
        while(it.hasNext()) {
            MembroHistorico<T> m = it.next();
            Data data = m.getData();
            T membro = m.getMembro();

            sb.append(data);
            sb.append(" - ");
            sb.append(membro);
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean adiciona(Historico<T> outroHistorico) {
        Iterador<MembroHistorico<T>> it = outroHistorico.getIterador();
        while(it.hasNext()) {

            //TODO: validar se data é igual ou posterior à ultima
            this.adiciona( it.next() );
        }
        return true;
    }

    public Iterador<MembroHistorico<T>> getIteradorInvertido() {
        return new Iterador<MembroHistorico<T>>() {

             Iterator<MembroHistorico<T>> it = historico.descendingIterator();

            public boolean hasNext() {
                return it.hasNext();
            }

            public MembroHistorico<T> next() {
                return it.next();
            }
        };
    }

    //aos alunos nao será necessário estes truques, pois nao terão de adaptar
    //um outro iterador. A sua classe de suporte já devolverá o tipo certo.
    private class IteradorHistorico<E> implements Iterador<E> {

        Iterator<MembroHistorico<T>> it = historico.iterator();

        public boolean hasNext() {
            return it.hasNext();
        }

        public E next() {
            return (E)it.next();
        }

    }

}