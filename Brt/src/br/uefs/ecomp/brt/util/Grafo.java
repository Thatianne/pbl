package br.uefs.ecomp.brt.util;

import br.uefs.ecomp.brt.util.Exception.ObjetoProcuradoNaoExisteException;

/**
 * Define o comportamento de um grafo.
 *
 *
 * @author Sarah Pereira Cerqueira
 * @author Thatianne Cristina
 */
public class Grafo {

    //Armazena todos os nós do grafo
    private Lista listaAdjacencia;
    private int caminho[];
    private int pesosCam[];
    private int anteriores[];

    /**
     * O contrutor sem parâmetros.
     */
    public Grafo() {
        listaAdjacencia = new Lista();
    }

    /**
     * Verifica se há elementos no grafo.
     *
     * @return verdadeiro de o grafo não tiver nós.
     */
    public Boolean grafoVazio() {
        return listaAdjacencia.estaVazia();
    }

    /**
     * Inseri um novo nó ao grafo.
     *
     * @param v, vertice a ser inserido.
     */
    public void inserirVertice(int v) {
        Vertice novo = new Vertice(v);
        this.listaAdjacencia.inserirFinal((Comparable) novo);
    }

    /**
     * Remove um vertice da lista de adjacencia.
     *
     * @param v, vertice que se deseja remover.
     * @exception ObjetoProcuradoNaoExisteException, exceção gerada caso o
     * vertice a ser removido não exista.
     *
     */
    public void removerVertice(int v) throws ObjetoProcuradoNaoExisteException {
        Iterador i = this.listaAdjacencia.iterador();
        int cont = 0;

        /* Percorrer a lista em busca do index do nó que possui o vertice passado.*/
        while (i.temProximo()) {
            Vertice n = (Vertice) i.obterProximo();

            if (n.getVertice() == v) {
                /*
                 precisa remover todas as arestas que saem do vértice que se quer remover 
                 e todas as arestas que chegam nesse vértice
                 */
                this.removeTodasArestas(n);
                this.listaAdjacencia.remover(cont);
            }

            cont++;
        }

        // Caso o vertice a ser removido não exista uma exceção é gerada.
        throw new ObjetoProcuradoNaoExisteException("Este vertice não existe");
    }

    /**
     * Remove todas as arestas que tem como destino o vertice passado como
     * parâmetro.
     */
    private void removeTodasArestas(Vertice v) {
        /*
         remove todas as arestas que saem do vértice v
         */
        Iterador itArestas = v.getArestas();
        //tira a referencia pro primeiro objeto, assim se perde o restante da lista de aresta
        itArestas.primeira = null;

        //Recebe a possição da aresta que deve ser removida
        int cont;

        /*
         remove todas as arestas que chegam no vértice v,        
         procurar em todos os vértices se o vertice v é o destino de algum
         */
        Iterador it = listaAdjacencia.iterador();
        while (it.temProximo()) {
            Vertice aux = (Vertice) it.obterProximo();
            cont = aux.temVertice(v);
            if (cont != -1) {
                aux.removerAresta(cont);
            }
        }
    }

    /**
     * Inserir aresta de um vértice a outro definindo o peso
     *
     * @param u vértice de origem
     * @param v vértice destino
     * @param peso kilometragem
     */
    public void inserirAresta(int u, int v, int peso) {
        Vertice comeco = null, fim = null;
        comeco = buscaVertice(u);
        fim = buscaVertice(v);
        comeco.inserirAresta(fim, peso);//o vértice do começo que insere a aresta
    }

    public Vertice menorCaminho(int u, int v) {
        Vertice vertAnt, vertProx = null, VertMenor = null, segMenor = null;
        Vertice destino = null;
        Aresta arestaMenor;
        vertAnt = buscaVertice(u);
        destino = buscaVertice(v);
        vertAnt.setDistancia(0);
        vertAnt.setPai(null);
        vertAnt.setVisitado(true);

        // settar as distancias, os pais e se foi visitado se não tiver mais caminhos possiveis
        //colocar um laço de repetição se todos foram visitados
        while (!destino.getVisitado()) {
            vertAnt.distProximo();//define as distâncias dos vértices vizinhos

            vertProx = vertAnt.menorDistancia();//menor vértice do vértice atual
            segMenor = vertAnt.segMenorDist();
            //pegar o segundo menor vértice se for menor que os do 'vertProx'
            if (segMenor != null && segMenor.getDistancia() < vertProx.getDistancia()) {
                vertProx = segMenor;
            }
            vertProx.setVisitado(true);
            vertAnt = vertProx;

        }
        return null;
    }

    public Vertice buscaVertice(int n) {
        Iterador it = listaAdjacencia.iterador();
        while (it.temProximo()) {//procura os vértices de origem
            Vertice vertice = (Vertice) it.obterProximo();
            if (vertice.getVertice() == n) {
                return vertice;
            }
        }
        return null;
    }

}
