package br.uefs.ecomp.brt.util;

import br.uefs.ecomp.brt.util.Exception.ObjetoProcuradoNaoExisteException;
import br.uefs.ecomp.brt.util.Exception.VerticeNaoPossuiArestasException;

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
        this.listaAdjacencia.inserirFinal(novo);
    }

    /**
     * Inserir aresta de um vértice a outro definindo o peso
     *
     * @param o vértice de origem
     * @param d vértice destino
     * @param peso kilometragem
     */
    public void inserirAresta(int o, int d, int peso) throws ObjetoProcuradoNaoExisteException {
        Vertice comeco = null, fim = null;

        comeco = buscaVertice(o);
        fim = buscaVertice(d);
        if (comeco != null && fim != null) {
            comeco.inserirAresta(fim, peso);//o vértice do começo que insere a aresta
        } else {
            throw new ObjetoProcuradoNaoExisteException("Vértice não existe");
        }
    }

    /**
     * Remove um vertice da lista de adjacencia.
     *
     * @param v, vertice que se deseja remover.
     * @exception ObjetoProcuradoNaoExisteException, exceção gerada caso o
     * vertice a ser removido não exista.
     *
     */
    public Vertice removerVertice(int v) throws ObjetoProcuradoNaoExisteException {
        Vertice vertice = null;
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
                vertice = (Vertice) listaAdjacencia.remover(cont);
                return vertice;

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

        //Recebe a possição da aresta que deve ser removida
        int cont = 0;

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
            cont++;
        }
    }

    public void removerAresta(int o, int d) {
    }

    public Vertice menorCaminho(int u, int v) throws VerticeNaoPossuiArestasException, ObjetoProcuradoNaoExisteException {
        this.zerarTudo();
        Vertice vertO, vertProx;
        Vertice destino;
        vertO = buscaVertice(u);//vértice que vai começar
        destino = buscaVertice(v);//onde termina
        vertO.setDistancia(0);//a distância dele para ele msm é zero
        vertO.setPai(null);//não tem pai        
        vertProx = vertO;

        if (!vertO.temAresta()) {
            throw new ObjetoProcuradoNaoExisteException("Caminho não existe");
        }

        do {
            vertProx.setVisitado(true);

            if (vertProx.temAresta()) {
                vertProx.distProximo();

                try {
                    vertProx = vertProx.menorDistancia();
                } catch (VerticeNaoPossuiArestasException e) {
                    vertProx = vertProx.getPai();
                    if (vertProx == null) {
                        throw new VerticeNaoPossuiArestasException("Caminho não existe");
                    }
                }
            } else {
                vertProx = vertProx.getPai();
            }
        } while (!destino.getVisitado());
        return destino;
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

    private void zerarTudo() {
        Iterador i = listaAdjacencia.iterador();
        Vertice v;

        while (i.temProximo()) {
            v = (Vertice) i.obterProximo();
            v.setDistancia(Integer.MAX_VALUE);
            v.setPai(null);
            v.setVisitado(false);
        }
    }

    /**
     * Se é possível chegar do vértice 'origem' ao vértice 'destino'
     *
     * @return
     */
    public int[][] todosCaminho() {
        /*
         fica mais fácil encontrar todos os caminhos possíveis com a matriz de adjancência
         então vou pegar da lista de adjacência e passar para uma matriz de adjacência
         */
        Vertice vertice;
        Iterador itAresta;
        Aresta aresta;
        int tamanho;
        int adjacencia[][];
        tamanho = listaAdjacencia.obterTamanho() + 1;
        adjacencia = new int[tamanho][tamanho];
        //passando da lista para a matriz        
        /*
         se tiver dois caminhos possíveis para chegar a um ponto o menor caminho será mantido
         por isso todas as possições vão começar com infinito para que se for menor será bustituido
         */
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                adjacencia[i][j] = Integer.MAX_VALUE;
            }
        }
        Iterador it = listaAdjacencia.iterador();
        while (it.temProximo()) {
            vertice = (Vertice) it.obterProximo();
            itAresta = vertice.getArestas();
            while (itAresta.temProximo()) {
                aresta = (Aresta) itAresta.obterProximo();
                if (adjacencia[vertice.getVertice()][aresta.getDestino().getVertice()] > aresta.getAresta()) {
                    adjacencia[vertice.getVertice()][aresta.getDestino().getVertice()] = aresta.getAresta();
                }
            }
        }
        //passou da lista de adjacência para a matriz
        //agora a implementação do algoritmo Warshall
        for (int y = 0; y < tamanho; y++) {
            for (int x = 0; x < tamanho; x++) {
                if (adjacencia[y][x] != Integer.MAX_VALUE) {//se tiver uma aresta de y para x
                    for (int z = 0; z < tamanho; z++) {
                        if (adjacencia[x][z] != Integer.MAX_VALUE) {
                            if (adjacencia[y][z] > adjacencia[y][x] + adjacencia[x][z]) {
                                adjacencia[y][z] = adjacencia[y][x] + adjacencia[x][z];
                            }
                        }
                    }
                }
            }
        }
        return adjacencia;
    }
}
