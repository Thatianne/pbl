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

    private static final int qtd = 30;
    private int prox;
    private int tamanho;
    private int maiorVertice = 0;
    //Armazena todos os nós do grafo
    private Lista listaTodos;
    private float adjacencia[][];
    private int passagens[][];

    /**
     * O contrutor sem parâmetros.
     */
    public Grafo() {
        this.tamanho = qtd+1;
        adjacencia = new float[tamanho][tamanho];
        passagens = new int[tamanho][tamanho];
        listaTodos = new Lista();
        infinito();
    }

    public Grafo(int tamanho) {
        this.tamanho = tamanho+1;
        adjacencia = new float[tamanho][tamanho];
        passagens = new int[tamanho][tamanho];
        listaTodos = new Lista();
        infinito();
    }

    /**
     * Verifica se há elementos no grafo.
     *
     * @return verdadeiro de o grafo não tiver nós.
     */
    public Boolean grafoVazio() {
        return listaTodos.estaVazia();
    }

    /**
     * Inseri um novo nó ao grafo.
     *
     * @param v, vertice a ser inserido.
     */
    public void inserirVertice(int v) {
        adjacencia[v][v] = 0;
        passagens[v][v] = v;
        Vertice novoVertice = new Vertice(v);
        listaTodos.inserirFinal(novoVertice);
    }

    /**
     * Inserir aresta de um vértice a outro definindo o peso
     *
     * @param o vértice de origem
     * @param d vértice destino
     * @param peso kilometragem
     */
    public void inserirAresta(int o, int d, int peso) throws ObjetoProcuradoNaoExisteException {
        adjacencia[o][d] = peso;
        adjacencia[d][o] = peso;
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
        Vertice vertice;
        vertice = buscaVertice(v);//se o vértice ja tiver sido inserido

        if (vertice != null) {
            for (int i = 0; i < tamanho; i++) {
                //a distância para os outros vértices fica infinita 
                //pois não existe mais tal vértice 'v'
                adjacencia[v][i] = Float.MAX_VALUE;
                adjacencia[i][v] = Float.MAX_VALUE;
            }
            
            //tira da lista de todos os vértices
            Iterador i = this.listaTodos.iterador();
            int cont = 0;
            /* Percorrer a lista em busca do index do nó que possui o vertice passado.*/
            while (i.temProximo()) {
                Vertice n = (Vertice) i.obterProximo();
                if (n.getVertice() == v) {
                    vertice = (Vertice) listaTodos.remover(cont);
                    return vertice;
                }
                cont++;
            }
        }
        return vertice;
    }


    public void removerAresta(int o, int d) {
        adjacencia[o][d] = Float.MAX_VALUE;
        adjacencia[d][o] = Float.MAX_VALUE;
    }

    public Vertice menorCaminho(int u, int v) throws VerticeNaoPossuiArestasException, ObjetoProcuradoNaoExisteException {
        return null;
    }

    public Vertice buscaVertice(int n) {
        Iterador it = listaTodos.iterador();
        while (it.temProximo()) {
            Vertice vertice = (Vertice) it.obterProximo();
            //procura o vértice que tem o número passado por parâmetro
            if (vertice.getVertice() == n) {
                return vertice;
            }
        }
        return null;
    }

    private void zerarTudo() {
        for(int i=0; i<tamanho; i++){
            for(int j=0; i<tamanho; j++){
                adjacencia[i][j] = Float.MAX_VALUE;
            }
        }
        //falta tirar todos os vértices da lista
    }

    /**
     * Se é possível chegar do vértice 'origem' ao vértice 'destino'
     *
     * @return
     */
    public void todosCaminho() {
       
        //agora a implementação do algoritmo Warshall
        for (int y = 0; y < tamanho; y++) {
            for (int x = 0; x < tamanho; x++) {
                if (adjacencia[y][x] != Integer.MAX_VALUE) {//se tiver uma aresta de y para x
                    for (int z = 0; z < tamanho; z++) {
                        if (adjacencia[x][z] != Integer.MAX_VALUE) {
                            if (adjacencia[y][z] > adjacencia[y][x] + adjacencia[x][z]) {
                                adjacencia[y][z] = adjacencia[y][x] + adjacencia[x][z];
                                passagens[y][z] = x;
                            }
                        }
                    }
                }
            }
        }

        /*
         for (int y = 0; y < tamanho; y++) {
         for (int x = 0; x < tamanho; x++) {
         for (int z = 0; z < tamanho; z++) {
         if (adjacencia[y][z] != Integer.MAX_VALUE && adjacencia[z][x] != Integer.MAX_VALUE) {
         if (adjacencia[y][x] > adjacencia[y][z] + adjacencia[z][x]) {
         adjacencia[y][x] = adjacencia[y][z] + adjacencia[z][x];
         }
         }
         }
         }
         }*/
    }
    
    public void infinito(){
        for(int i=0; i<tamanho; i++){
            for(int j=0; j<tamanho; j++){
                adjacencia[i][j] = Float.MAX_VALUE;
            }
        }
    }
    
    float[][] getAdjacencia() {
        return adjacencia;
    }

    int[][] getCaminhos() {
        return passagens;
    }
}
