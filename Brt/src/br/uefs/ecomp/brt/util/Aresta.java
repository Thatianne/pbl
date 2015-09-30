package br.uefs.ecomp.brt.util;

/** Comportamento de uma aresta.
 *
 * @author Sarah Pereira
 * @author Thatianne Cristinna
 */
public class Aresta {

    //Para onde a aresta aponta
    private Vertice destino;
    //Magnitude da aresta
    private int aresta;    
    
    /** Construtor inicializa todos os atributos.
     * @param v, vetice destino
     * @param a, tamanho da aresta
     */
    public Aresta (Vertice v, int a){        
        this.destino = v;
        this.aresta = a;
    }
    
    /** Retorna o valor do destino.
     * @return vertice.
     */
    public Vertice getDestino() {
        return destino;
    }
    
    /** Retorna o tamanho da aresta.
     * @return aresta
     */
    public int getAresta() {
        return aresta;
    }

    /** Modifica o valor da aresta.
     * @param  aresta, novo valor da aresta
     */
    public void setAresta(int aresta) {
        this.aresta = aresta;
    }
    
     /** Verifica de duas arestas são iguais.
     * @param o, objeto a ser comparado
     * @return verdadeiro se forem igual
     */
    @Override
    public boolean equals(Object o) {
       if (!(o instanceof Vertice)) {
            return false;
        }
       
       Vertice a = (Vertice)o;
       
       return destino.equals(a);

    }

    public boolean temDestino(Vertice v) {
        return destino.equals(v);
    }
       
    
}
