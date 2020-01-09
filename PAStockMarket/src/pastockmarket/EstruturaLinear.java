/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pastockmarket;

/**
 *
 * @author brunomnsilva
 */
public interface EstruturaLinear <T> {
    // verifica se a estrutura tem elementos
    public boolean estaVazia();
    // devolve a quantidade de elementos da estrutura
    public int tamanho();
    // insere um elemento no início da estrutura
    public void inserir(T p0);
    // insere um elemento no fim da estrutura
    public void inserirCauda(T p0);
    // remove um elemento do início da estrutura
    public T remover();
    // remove um elemento do fim da estrutura
    public T removerCauda();
    //insere um elemento a seguir a outro devolvendo o sucesso da op
    public boolean inserirApos(T ref, T p0);
    //insere um element antes de outro
    public boolean inserirAntes(T ref, T p0);
    //metodo que devolve uma string mostrando os elementos da lista
    public String visualizar();

    //devolve iterador
    public Iterador<T> getIterador();
}