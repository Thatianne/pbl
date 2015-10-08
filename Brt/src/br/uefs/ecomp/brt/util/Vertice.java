package br.uefs.ecomp.brt.util;

import br.uefs.ecomp.brt.util.Exception.ObjetoProcuradoNaoExisteException;
import br.uefs.ecomp.brt.util.Exception.VerticeNaoPossuiArestasException;

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
    private Vertice pai;

    public Vertice getPai() {
        return pai;
    }

    public void setPai(Vertice pai) {
        this.pai = pai;
    }

    public int qtdArestas() {
        return arestas.obterTamanho();
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
        distancia = Integer.MAX_VALUE;
        this.pai = null;
    }

    public boolean temAresta() {
        return !arestas.estaVazia();
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
