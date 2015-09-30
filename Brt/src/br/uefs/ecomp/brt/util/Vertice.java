package br.uefs.ecomp.brt.util;

import br.uefs.ecomp.brt.util.Exception.ObjetoProcuradoNaoExisteException;

/**
 * Define o comportamento do nó de um grafo.
 *
 * @author Sarah Pereira Cerqueira
 * @author Thatianne Cristina
 *
 */
public class Vertice {

    //Vertice do nó
    private final int vertice;
    //Lista que armazena a distancia e os nós nos quais o vertice está ligado
    private final Lista arestas;
    
    private boolean visitado;
    private int distancia;
    private boolean primeiro;
    private Vertice pai;

    public boolean isPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(boolean primeiro) {
        this.primeiro = primeiro;
    }

    public Vertice getPai() {
        return pai;
    }

    public void setPai(Vertice pai) {
        this.pai = pai;
    }

    /**
     * O construtor recebe apenas o vertice do nó.
     *
     * @param v, vetice do nó
     */
    public Vertice(int v) {
        vertice = v;
        arestas = new Lista();
        visitado = false;
        distancia = -1;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    /**
     * Retorna o vertice do nó.
     *
     * @return vetice do nó.
     */
    public int getVertice() {
        return vertice;
    }

    /**
     * Retorna o iterador da lista que armazena as arestas.
     *
     * @return iterador da lista de arestas.
     */
    public Iterador getArestas() {
        return arestas.iterador();
    }

    /**
     * Apartir do valor do destino, modifica o tamanho da aresta.
     *
     * @param destino, destino no qual o vertice do nó esta ligado através da
     * aresta.
     * @param nova, novo valor da aresta
     * @exception ObjetoProcuradoNaoExisteException, caso o nò não esteja ligado
     * a aresta passada como destino uma exceção será gerada.
     */
    public void setArestas(Vertice destino, int nova) throws ObjetoProcuradoNaoExisteException {
        Iterador i = arestas.iterador();
        Aresta aresta;

        // Pecorre a lista procurando a aresta na qual o valor será modificado.
        while (i.temProximo()) {
            aresta = (Aresta) i.obterProximo();
            if (aresta.getDestino().equals(destino)) {
                aresta.setAresta(nova);
                return;
            }
        }

        // Caso não haja um destino como o passado como parâmetro, o método gera exceção
        throw new ObjetoProcuradoNaoExisteException("Destino não existe");
    }

    /**
     * Inseri uma ligação entre um nó e outro vértice.
     *
     * @param destino, vertice no qual à aresta estará ligando o vértice do nó.
     * @param aresta, tamanho da aresta.
     */
    public void inserirAresta(Vertice destino, int aresta) {
        Aresta a = new Aresta(destino, aresta);
        arestas.inserirFinal((Comparable) a);

    }

    /**
     * Remove uma aresta da lista de arestas, através do destino passado.
     *
     * @param destino, vértice no qual se quer quebrar a conecção com o vértice
     * do nó.
     */
    public void removerAresta(Vertice destino) {
        int cont = 0;
        Iterador it = arestas.iterador();
        while (it.temProximo()) {
            Aresta ar = (Aresta) it.obterProximo();

            if (ar.getDestino().equals(destino)) {
                arestas.remover(cont);
            }
            cont++;
        }

    }

    public boolean temVertice(Vertice v) {
        Iterador itAresta = arestas.iterador();
        while (itAresta.temProximo()) {
            Aresta aresta = (Aresta) itAresta.obterProximo();
            if (aresta.temDestino(v)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica de dois nós são iguais.
     *
     * @param o, objeto a ser comparado
     * @return verdadeiro se forem igual
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vertice)) {
            return false;
        }

        Vertice a = (Vertice) o;

        return a.getVertice() == vertice;

    }

    public void setVisitado(boolean op) {
        visitado = op;
    }

    public boolean getVisitado() {
        return visitado;
    }

}
