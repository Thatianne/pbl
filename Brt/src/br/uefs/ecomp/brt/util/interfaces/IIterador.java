package br.uefs.ecomp.brt.util.interfaces;

/** Interface do Iterador.
 */
public interface IIterador {
	
        /**Verifica se tem outros elementos na lista.
         * 
         *@return verdadeiro se houver mais elementos na lista.
         */
	public boolean temProximo();
        
        /** Retorna o proximo elemento da lista.
         * 
         * @return proximo objeto da lista
         */
	public Object obterProximo();

}
