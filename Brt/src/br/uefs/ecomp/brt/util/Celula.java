
package br.uefs.ecomp.brt.util;
import java.io.Serializable;

/**
 * Contém referência para duas outras celulas e para um objeto.
 * 
 * @author Thatianne E Sarah
 */
public class Celula implements Serializable {
    
    //uma celula tem referência para:
    private Object chave;//um objeto
    private Celula prox;//para um proxima celula
    private Celula ant;//e para uma anterior
    //pois é duplamente encadeada
        
    /** O construtor recebe o objeto da celula, o atributo novo.
     * 
     * @param o, objeto a ser inserido na celula
     */
    public Celula(Object o) {
      this.chave = o;
      this.prox = null;
      this.ant = null;
    }
    
    /** Retorna o objeto da celula.
     * 
     * @return novo
     */
    public Object getChave() {
        return chave;
    }

    
    /** Modifica o valor do objeto da celula.
     * 
     * @param novo valor do obejeto da celula
     */
    public void setNovo(Object novo) {
        this.chave = novo;
        
    }
    
    /** Retorna a proxima celula da lista
     * 
     * @return  prox
     */
    public Celula getProx() {
        return prox;
    }
    
    /** Retorna a celula anterior da lista
     * 
     * @return ant
     */
    public Celula getAnt() {
        return ant;
    }
    
    /** Modifica o valor da proxima celula
     * 
     * @param prox, novo valor da proxima celular
     */
    public void setProx(Celula prox) {
        this.prox = prox;
    }
    
     /** Modifica o valor da celula anterior.
     * 
     * @param ant, novo valor da celula anterior
     */ 
    public void setAnt(Celula ant) {
        this.ant = ant;
    }
    
    
}
