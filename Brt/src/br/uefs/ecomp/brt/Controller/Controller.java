/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.brt.Controller;

import br.uefs.ecomp.brt.util.Exception.ObjetoProcuradoNaoExisteException;
import br.uefs.ecomp.brt.util.Exception.VerticeNaoPossuiArestasException;
import br.uefs.ecomp.brt.util.Grafo;
import br.uefs.ecomp.brt.util.Vertice;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author thatianne
 */
public class Controller {

    private Grafo grafo = new Grafo();
    private Vertice ultimo;
    
    
    public void inserirVertice(int vertice) {
        grafo.inserirVertice(vertice);        
    }
    
    public void inserirAresta(int comeco, int fim, int valor) throws ObjetoProcuradoNaoExisteException{
        grafo.inserirAresta(comeco, fim, valor);
    }
    
    public void doArquivo(File caminho) throws FileNotFoundException, ObjetoProcuradoNaoExisteException{
        
        Scanner scanner = new Scanner(new FileReader(caminho)).useDelimiter(" ||\\n");
        while (scanner.hasNext()) {
            int comeco = scanner.nextInt();
            inserirVertice(comeco);
            int fim = scanner.nextInt();
            inserirVertice(fim);
            int distancia = scanner.nextInt();  
            inserirAresta(comeco, fim, distancia);
        }
    }
    
    public void deletarVertice(int v) throws ObjetoProcuradoNaoExisteException{
        grafo.removerVertice(v);        
    }
    
    public void buscaMenorCaminho(int u, int v) throws VerticeNaoPossuiArestasException, ObjetoProcuradoNaoExisteException{
        ultimo = grafo.menorCaminho(u, v);
    }
}
