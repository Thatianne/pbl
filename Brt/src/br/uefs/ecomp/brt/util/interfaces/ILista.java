package br.uefs.ecomp.brt.util.interfaces;

import br.uefs.ecomp.brt.util.Iterador;


/** Interface da Lista.
 */
public interface ILista {
    
     /** Retornar verdadeiro se a lista estiver vazia. 
    * @return verdadeiro se a lista não tiver elementos
    */
    public boolean estaVazia();
    
    /**Retorna o tamanho da lista. 
     * @return quantidade de elementos da lista
     */
    public int obterTamanho();
    
    /**Inseri um objeto numa determinada posição.
     * 
     * @param index, posição em que o elemento vai ser inserido
     * @param o, objeto a ser inserido
     */
    public void inserir(int index, Object o);
    
    /** Inserir um objeto no Inicio da lista.
     * @param o, objeto a ser inserido na lista
     */
    public void inserirInicio(Object o);
    
     /** Inserir um objeto no final lista.
     * @param o, objeto a ser inserido no final da lista
     */
    public void inserirFinal(Object o);
    
     /**Remove um objeto de determinada posição.
     * @param index, posição do elemento vai ser removido
     * @return retorna o objeto removido 
     */
    public Object remover(int index);
    
     /** Remove um elemento do inicio da lista.
     * @return retorna o objeto removido
    */
    public Object removerInicio();

     /** Remove um elemento do final da lista.
     * @return retorna o objeto removido
    */
    public Object removerFinal();

    /** Retorna um elemento a partir da posição.
     * @param index, posição do objeto que vai ser retornado
     * @return retorna o objeto da posição passada como parêmetro
     */
    public Object recuperar(int index);
    
     /**Retorna o iterador da lista.
     * @return iterador;
     */
    public Iterador iterador();

}
