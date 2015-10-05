/* A classe lista, guarda elementos em uma única estrutura*/
package br.uefs.ecomp.brt.util;

import br.uefs.ecomp.brt.util.interfaces.ILista;

    /** Classe que implementa um comportamento de uma lista.
     * Todos os métodos da classe foram implementados a partir
     * a interface ILista.
     * 
     * @author Sarah Pereira Cerqueira
     * @see br.uefs.ecomp.av.util.ILista
     */
   public class Lista implements ILista {
    
    //Primeira celula da lista
    Celula primeira;
    
    //Ultima Célula da Lista
    Celula ultima;
    
    /** Construtor da lista, não recebe parâmetros.
    */
    //Construtor
    public Lista (){
    //Inicialização do iterador
    primeira = null;}
    
     /** Retornar verdadeiro se a lista estiver vazia. 
    * Método implementado da inteface ILista.
    * 
    * @see  br.uefs.ecomp.av.util.ILista
    * 
    * @return verdadeiro se a lista não tiver elementos
    */
    @Override
    /*Método da interface ILista, retorna verdadeiro se a lista estiver sem elementos e falso se a 
    lista tiver elementos*/
    public boolean estaVazia() {
        return this.primeira == null;}
    
    /**Retorna o tamanho da lista. 
     * Método implementado da interface ILista.
     * 
     * @see br.uefs.ecomp.av.util.ILista
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
    
    /** Inserir um objeto no Inicio da lista.
     * Método implementado da interface ILista
     * 
     * @param o, objeto a ser inserido na lista
     * 
     * @see br.uefs.ecomp.av.util.ILista
     */
    @Override
    //Método da interface ILista, inseri elementos no inicio da lista
    public void inserirInicio(Object o) {
        
        //Inicialização da nova celula a ser inserida na lista
        Celula novacelula = new Celula(o);
        
       //Verifica se é o primeiro elemento da lista
       if (estaVazia()){
           
            //Primeira celula da lista recebe nova   
           this.primeira = novacelula;

           //O primeiro elemento é igual ao último elemento da lista
           this.ultima = this.primeira;}//Fim do if
        
       //Se não for o primeiro elemento da lista
        else {
            //O campo anterior da celula primeira recebe a nova celula
            this.primeira.setAnt(novacelula);
            
            //O campo próximo da celula nova recebe a primeira celula
            novacelula.setProx(primeira);
            
            //A celula nova passa a ser a primeira celula
            this.primeira = novacelula;}//Fim do else
    }//Fim do Método inserirInicio

    /** Inserir um objeto no final lista.
     * Método implementado da interface ILista
     * 
     * @param o, objeto a ser inserido no final da lista
     * 
     * @see br.uefs.ecomp.av.util.ILista
     */
    @Override
    //Método da interface ILista, inseri elementos no fim da lista
    public void inserirFinal(Object o) {
        
        //Inicializa a nova celula
       Celula novacelula = new Celula(o);
        
      // Verifica se é o primeiro elemento da lista 
       if (estaVazia()){
        
       //Primeira recebe a celula noba
       this.primeira = novacelula;
       
       //Primeira é igula a ultima
       this.ultima = this.primeira;}
        
       
       //Se a lista já tive elemento
        else {
           // O campo proxima da última celula recebe celula nova
            ultima.setProx(novacelula);
            
            //O campo anterior da celula nova recebe última
            novacelula.setAnt(ultima);
            
            //Última passa a ser a nova celula
            ultima = novacelula;}//Fim do else
    
    }//Fim do Método inserirFinal

    /** Remove um elemento do inicio da lista.
     * Método implementado da interface ILista
     * 
     * @return retorna o objeto removido
     * 
     * @see br.uefs.ecomp.av.util.ILista
    */
    @Override
    //Método da interface ILista, remove a primeira celula da lista
    public Object removerInicio() {
        
         if (estaVazia()){
            return null;}
        
        //Celula auxiliar guarda o endereço da célula a ser excluída
        Celula celula = primeira;
        
         //Verifica se é a única celula da lista
        if (this.primeira == this.ultima){
            this.primeira = this.ultima = null;
        }//Fim do if
        
       else {
        //A primeira celula passa a ser a segunda
        this.primeira = this.primeira.getProx();
        
        //O laço entre a primeira celula e a lista é cortado
        this.primeira.setAnt(null);}
        
        //Retorna o elemento da celula removida
        return celula.getChave();
        }
        
    
    /** Remove um elemento do final da lista.
     * Método implementado da interface ILista
     * 
     * @return retorna o objeto removido
     * 
     * @see br.uefs.ecomp.av.util.ILista
    */
    @Override
    ////Método da interface ILista, remove a ultima celula da lista
    public Object removerFinal() {
        
        if (estaVazia()){
            return null;}
        
        //Declaração da celula auxiliar, que recebe o endereço da celula a ser removida
        Celula celula = ultima;
        
        //Verifica se é a única celula da lista
        if (this.primeira == this.ultima){
            this.primeira = this.ultima = null;
        }//Fim do if
        
        else{
        
        //A última celula passa a ser a penúltima
        this.ultima = this.ultima.getAnt();
        
        //A penúltima celula perde o endereço da última, fazendo da penúltima celula a última;
        this.ultima.setProx(null);}
        
        //Retorna o elemento da celula removida
        return celula.getChave();}// Fim do método removerFinal
     
    /** Retorna um elemento a partir da posição.
     * Método implementado da interface ILista
     * 
     * @param index, posição do objeto que vai ser retornado
     * @return retorna o objeto da posição passada como parêmetro
     * 
     * @see br.uefs.ecomp.av.util.ILista
     */
    @Override
    //Busca um elemento na lista
    public Object recuperar(int index) {
        
       //Instancia um iterador para pecorrer a lista
       Iterador iterador = new Iterador (primeira);
        
       //Verifica se a lista está varia
        if (estaVazia())
            return null;
        
        //Contador para saber em que posição da lista está
        int contador = 0;
        
       //Pecorre a lista até encontrar o elemento com o index desejado
       while (iterador.temProximo()){
           
           // Sai do while se o contador for igual ao index
           if (contador == index)
              break;
           
           //Atualiza cotador e o iterador
           contador ++;
           iterador.obterProximo();} 
        
       //Retorna o objeto procurado
        return iterador.obterProximo();}
    
    /**Retorna o iterador da lista.
     * Método implementado da interface ILista
     * 
     * @return iterador;
     * @see br.uefs.ecomp.av.util.ILista
     * @see br.uefs.ecomp.av.util.Iterador
     */
    @Override
    ////Método da interface ILista, retorna o iterador da lista
    public Iterador iterador() {
       Iterador iterador = new Iterador (primeira);
        return iterador;}
    
    /**Inseri um objeto numa determinada posição.
     * Método implementado da interface ILista.
     * 
     * @param index, posição em que o elemento vai ser inserido
     * @param o, objeto a ser inserido
     * 
     * @see br.uefs.ecomp.av.util.ILista
     */
    @Override
    public void inserir(int index, Object o) {
        
        //Cria iterador para pecorrer a lista
        Iterador iterador = new Iterador (primeira);
        
        //Verifica se a lista está vazia ou se o index é zero inserindo o elemento no inicio da lista
        if (estaVazia() || index == 0){
            inserirInicio(o);
            return;}
        
        //Declaração de variaveis auxiliares
        Celula celula,nova;
        int contador = 0;
        
        //Iterador para o inicio da lista
        iterador.reset();
        
        // Pecorre a lista em busca da posição desejada
        while (iterador.temProximo()){
                
            //Se a posição for igual ao index, inseri o elemento na posição
              if(contador == index){
                celula = iterador.getAtual();
                nova = new Celula (o);
                celula.setAnt(nova);
                celula = nova.getAnt();
                celula.setProx(nova);
                return;
                }
            //Se não entrar o if, o contador e o iterado é atualizado                 
            contador++;
            iterador.obterProximo();}
            
            /*Se o index for maior que a ultima posição a lista ou index for igual a contador
        o elemento é inserido no final da lista*/
            if (contador < index || contador == index )
                inserirFinal(o);}

     /**Remove um objeto de determinada posição.
     * Método implementado da interface ILista.
     * 
     * @param index, posição do elemento vai ser removido
     * 
     * @return retorna o objeto removido 
     * @see br.uefs.ecomp.av.util.ILista
     */
    @Override
    public Object remover(int index) {
        //Instancia um iterador para pecorrer a lista
        Iterador iterador = new Iterador (primeira);
        
        //Declaração de variável auxiliar
        Object o = null;
        
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
               o = aux.getChave();
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
    
}//Fim da Classe Lista
