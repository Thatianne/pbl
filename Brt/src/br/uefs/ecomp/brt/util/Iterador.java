
package br.uefs.ecomp.brt.util;

import br.uefs.ecomp.brt.util.interfaces.IIterador;
import java.io.Serializable;

/** Usado para percorrer uma lista e retornar o proximo objeto.
 * 
 * @author Thatianne e Sarah
 * 
 * @see br.uefs.ecomp.av.util.Lista
 * @see br.uefs.ecomp.av.util.Fila
 */
public class Iterador implements IIterador, Serializable {
    Celula atual;
    Celula primeira;
    
    /** Contrutor do Iterador.
     * 
     * @param primeira, primeira celula da lista
     */
    public Iterador(Celula primeira) {
        this.primeira = primeira;
        this.atual = primeira;
    }
    
    /** Faz com que o atual aponte pro inicio da lista.
     */
    public void reset() {
        this.atual = this.primeira;
        //o atual vai para o inicio da lista
    }
    /** Se atual for diferente de nulo é porque tem um proximo objeto, retorna verdadeiro.
     * 
     * @return verdadeiro se houver próximo e false se não houver
     */
    @Override
    public boolean temProximo() {
        return atual != null;
    }
    
    /**Retorna um objeto que está dentro da celula atual.
     * 
     * @return novo
     */
    @Override
    public Object obterProximo() {
        Celula aux;
        if(temProximo()){
            aux = atual;
            atual = atual.getProx();//o aux passa para a proxima celula
            return aux.getChave();//o objeto referenciado por atual é retornado
        }
        return null;
    }
    
   /** Retornar a celula atual.
    * 
    * @return atual
    */
    public Celula getAtual() {
        return atual;
    }

}
