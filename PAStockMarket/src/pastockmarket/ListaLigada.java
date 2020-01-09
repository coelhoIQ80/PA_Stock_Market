/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pastockmarket;

/**
 *
 * @author brunomnsilva
 */
public class ListaLigada<T> implements EstruturaLinear<T> {

    private int tamanho;
    private No<T> header, trailer;

    public ListaLigada() {
        tamanho = 0;
        header = new No<T>(null, null, null);
        trailer = new No<T>(null, null, header);
        header.next = trailer;
    }


    public boolean estaVazia() {
        return tamanho == 0;
    }

    public int tamanho() {
        return tamanho;
    }

    public void inserir(T p0) {
        No<T> novo = new No<T>(p0, header.next, header);
        header.next.prev = novo;
        header.next = novo;
        tamanho++;
    }

    public void inserirCauda(T p0) {
        No<T> novo = new No<T>(p0, trailer, trailer.prev);
        trailer.prev.next = novo;
        trailer.prev = novo;
        tamanho++;
    }

    public T remover() {
        if(estaVazia()) return null;

        No<T> rem = header.next;

        T elem = rem.element;

        header.next = rem.next;
        rem.next.prev = header;

        tamanho--;

        return elem;
    }

    public T removerCauda() {
        if(estaVazia()) return null;
        No<T> rem = trailer.prev;

        T elem = rem.element;

        trailer.prev = rem.prev;
        rem.prev.next = trailer;

        tamanho--;

        return elem;
    }

    public boolean inserirApos(T ref, T p0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean inserirAntes(T ref, T p0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String visualizar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Iterador<T> getIterador() {
        return new Iterador<T>() {

            No<T> actual = header.next;

            public boolean hasNext() {
                return actual != trailer;
            }

            public T next() {
                T elem = actual.element;
                actual = actual.next;
                return elem;
            }
        };
    }


    class No<E> {
        E element;
        No<E> next;
        No<E> prev;

        No(E element, No<E> next, No<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

}
