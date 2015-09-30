
package br.uefs.ecomp.brt.util;

import br.uefs.ecomp.brt.util.interfaces.ILista;
import java.io.Serializable;


/**
 * Manipula objetos, podendo remover no inicio, final e em um posição especifica,
 * inserir inicio, final e em um posição especifica ,buscar um objeto de uma lista.
 * 
 * @author Thatianne e Sarah
 * 
 * @see Iterador
 */
public class Lista implements ILista, Serializable{

    private Celula primeira;//referencia o inicio da lista
    private Celula ultima;//o final
    private Celula atual;//axilia nas funções de uma lista

    
    /**Verifica se a lista está vazia.
     * @return boolean
     */
    @Override
    public boolean estaVazia() {
        return primeira == null;
    }
 
    /**Retorna o tamanho da lista. 
     * Método implementado da interface ILista.
     * 
     * @see br.uefs.ecomp.av.util.interfaces.ILista
     * 
     * @return quantidade de elementos da lista
     */
    
    @Override
    //Método da inteface ILista, retorna a quantidade de itens da lista
    public int obterTamanho() {
        
        Iterador iterador = new Iterador (primeira);
        
     //*   
        //Veririca se há elementos na lista
        if (estaVazia())
            return 0;
        
        //Declaração do contador
        int tamanho = 0;
        
        //Garante que o iterador esteja no inicio da lista
        iterador.reset();
        
        //Enquanto houver elementos na lista
        while (iterador.temProximo()) {
            tamanho++;
            iterador.obterProximo();}

        return tamanho;}// Fim do método obterTamanho
    
    /**
     * Iterador da lista, serve para percorrer a lista.
     * @return Iterador
     */
    @Override
    public Iterador iterador() {
        Iterador iterador = new Iterador(primeira);//o iterador recebe a primeira posição da lista
        return iterador;
    }  

    /**
     * Insere o objeto passado por parâmetro na posição passada por parâmetro.
     * @param index, posição em que o objeto será inserido
     * @param o, objeto que será inserido
     */
    @Override
    public void inserir(int index, Comparable o) {
        
        int cont = 0;
        Celula meio = new Celula(o);
        this.atual = this.primeira;
        if (index == 0) {//se for inserir no começo
            if (estaVazia()) {//se ainda estiver vazia
                this.primeira = meio;
                meio.setAnt(null);
                meio.setProx(null);
                this.ultima = this.primeira;

            } else {//se já tiver elementos
                this.primeira.setAnt(meio);
                meio.setProx(primeira);
                meio.setAnt(null);
                this.primeira = meio;
                this.atual = primeira;
            }
        } else {//se não for na primeira posição
            while (cont + 1 < index) {
                this.atual = this.atual.getProx();
                cont++;
            }
            if (this.atual == this.ultima) {//se for inserir no final
                this.ultima.setProx(meio);
                meio.setAnt(ultima);
                this.ultima = this.ultima.getProx();
                this.atual = this.ultima;
            } else {
                Celula aux;
                aux = this.atual.getProx();
                this.atual.setProx(meio);
                meio.setAnt(atual);
                aux.setAnt(meio);
                meio.setProx(aux);

            }
        }
    }
    
    /**
     * Insere no incio da lista.
     * @param o , objeto a ser inserido
     */
    @Override
    public void inserirInicio(Comparable o) {
        Celula aux = new Celula(o);
        if (estaVazia()) {//se estiver a lista estiver vazia, primeira vai referenciar nulo
            this.ultima = aux;
            this.primeira = aux;
            aux.setProx(null);
            aux.setAnt(null);
        } else {

            this.primeira.setAnt(aux);
            aux.setProx(primeira);
            aux.setAnt(null);
            this.primeira = aux;//a primeira vai mudar de posição, passando a referenciar o novo objeto inserido
        }
    }
    
    /**
     * Insere no final da lista.
     * @param o, objeto a ser inserido
     */

    @Override
    public void inserirFinal(Comparable o) {        
        //a nova celula vai refenrenciar o objeto 'o'
        Celula aux = new Celula(o);
        if (estaVazia()) {//se for o primeiro elemento a ser inserido
            this.ultima = aux;
            this.primeira = aux;
            aux.setAnt(null);
            aux.setProx(null);
        } else {// se não
            this.ultima.setProx(aux);
            aux.setAnt(ultima);
            aux.setProx(null);
            this.ultima = aux;
        }
    }
    
   /**Remove um objeto de determinada posição.
     * Método implementado da interface ILista.
     * 
     * @param index, posição do elemento vai ser removido
     * 
     * @return retorna o objeto removido 
     * @see br.uefs.ecomp.av.util.interfaces.ILista
     */
    @Override
    public Comparable remover(int index) {
        //Instancia um iterador para pecorrer a lista
        Iterador iterador = new Iterador (primeira);
        
        //Declaração de variável auxiliar
        Comparable o = null;
        
        //Verifica se a lista está vazia
        if (estaVazia()){
            return null;}
        
        //Verifica se index é igual ao inicio da lista, se for remove o primeiro da lista
        else if (index == 0){
           o = removerInicio();
            return o;}
        
        //Variável auxiliar
        Celula aux;
        int contador =0;
        
        //Iterador para o inicio da lista
        iterador.reset();
        
        // Pecorrer a lista em busca do index procurado
        while(iterador.temProximo()){
           
            //Se contador for igual a index, remove o elemento da lista
            if (contador == index){
               Celula celula;
               aux = iterador.getAtual();
               
               //Se o elemento for o ultimo da lista, usa o método remover final
               if (aux.getProx() == null){
                  o = removerFinal();
                  break;
               
               //Remove um elemento do meio da lista
               } 
               o = aux.getNovo();
               celula = aux.getAnt();
               celula.setProx(aux.getProx());
               celula = aux.getProx();
               celula.setAnt(aux.getAnt());
               break;
            }
            
       //Se não entrar no if o contador e o iterador é atualizado;
       contador ++;
       iterador.obterProximo(); }        
      
        //Retorna o objeto removido
      return o;
    }
    
    /**
     * Remove o inicio.
     * @return Object removido
     */

    @Override
    public Comparable removerInicio() {
        Comparable retornar;
        if (estaVazia())//sem itens
        {
            return null;
        } else if (this.primeira == this.ultima) {//unico elemento
            retornar = this.primeira.getNovo();
            this.primeira = null;
            this.ultima = this.primeira;
        }//mais de um
        else {
            this.atual = this.primeira;
            this.primeira = this.primeira.getProx();
            this.primeira.setAnt(null);
            this.atual.setAnt(null);
            retornar = this.atual.getNovo();
        }
        return retornar;
    }

    /**
     * Remove o final da lista.
     * @return Object removido
     */
    @Override
    public Comparable removerFinal() {
        Comparable retornar;
        if (estaVazia()) {//se estiver vazia
            return null;
        } else if (this.primeira == this.ultima) {//se tiver apenas um elemento
            retornar = this.primeira.getNovo();//objeto a ser retornado
            this.primeira = null;
            this.ultima = this.primeira;//primeira e ultima referencia nulo
        } else {//outros casos
            this.atual = this.ultima;//atual recebe a ultima posição
            this.ultima = this.ultima.getAnt();//o ultimo referenca uma posição anterior
            this.ultima.setProx(null);//o proximo de ultimo aponta pra nulo
            this.atual.setAnt(null);//e o anterior do atual aponta pra nulo, o proximo dele ja está apontando pra nulo or ser ultima posição
            retornar = this.atual.getNovo();
        }
        return retornar;
    }
    
    /**
     * Retorna o objeto que está na posição passada por parâmetro.
     * @param index, posição a ser recuperada
     * @return Object encontrado
     */
    @Override
    public Comparable recuperar(int index) {
        int cont = 0;
        atual = primeira;
        while(cont!=index){
            atual = atual.getProx();//atual vai mudando pra posição pocição até chegar na posição que deseja excluir
            cont++;
        }
        if(atual == null)
            return null;
        else
        return atual.getNovo();
    }

}
