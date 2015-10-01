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

    /**
     * Procura o menor caminho de um vertice para todos os outros
     */
    public void menorCaminho(int u) {
        Vertice vertice = null;        
        //o vertice inicial tem distancia zero
        disVertice(u);
        
        Iterador it = listaAdjacencia.iterador();
        while(it.temProximo()){
            vertice = procuraMenorDistancia();
            if(vertice.getDistancia() == -1){//se tiver um vértice inacessível
                break;                
            }
            vertice.setVisitado(true);            
        }
        
        
        //liberar os visitados?? colocar vertices.setVisitado(false)??
        /*
        Iterador it4 = listaAdjacencia.iterador();
        while(it4.temProximo()){
            Vertice v4 = (Vertice) it4.obterProximo();
            v4.setVisitado(false);
        }
        */
        
        
        
        
        //visitar todos os vertices vizinhos do vértice de menor distancia
        Iterador itArestas = vertice.getArestas();
        while (itArestas.temProximo()) {            
            Aresta aresta = (Aresta) itArestas.obterProximo();
            Vertice vizinho = aresta.getDestino();
            if(vizinho.getDistancia() < 0){//ninguém chegou nele ainda, está com a distância inválida
                //soma as distancias de vertice até seu vizinho e atribui a distância do vizinho
                vizinho.setDistancia(vertice.getDistancia()+aresta.getAresta());
                //o pai do 'vizinho' vai ser 'vertice'
                vizinho.setPai(vertice);
            }else if(vizinho.getDistancia() > vertice.getDistancia()+aresta.getAresta()){
                vizinho.setDistancia(vertice.getDistancia()+aresta.getAresta());
                vizinho.setPai(vertice);
            }
            
        }
        
        

    }
    /**
     * Procura vertice com menor distância
     * @return 
     */
    private Vertice procuraMenorDistancia(){
        Vertice menor = null;
        boolean primeiro = true;
        //ir em todos os vértice        
        Iterador it = listaAdjacencia.iterador();

        //procura o vértice com a menor distância e que não tenha sido visitado
        while (it.temProximo()) {
            Vertice vert = (Vertice) it.obterProximo();
            if(vert.getDistancia() >= 0 && (!vert.getVisitado())){
                if(primeiro){//se for o primeiro vertice
                    menor = vert;
                    primeiro = false;
                }else if(menor.getDistancia() > vert.getDistancia()){
                    menor = vert;                    
                }                
            }
        }
        return menor;
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
    /**
     * Definir que o vertice inicial tem distância zero, e os outros tem distancia -1
     */
    private void disVertice(int n){
        Vertice v = (Vertice) listaAdjacencia.recuperar(n);
        v.setDistancia(0);
    }

}
