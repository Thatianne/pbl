/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.brt.util;

import br.uefs.ecomp.brt.util.Exception.ObjetoProcuradoNaoExisteException;
import br.uefs.ecomp.brt.util.Exception.VerticeNaoPossuiArestasException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thatianne
 */
public class GrafoTest {
    
    private Grafo g;
    private Vertice vertice1, vertice2, vertice3, vertice4, vertice5, vertice6;
    
    @Before
    public void setUp() {
        g = new Grafo ();
        vertice1 = new Vertice(1);
        vertice2 = new Vertice(2);
        vertice3 = new Vertice(3);
        vertice4 = new Vertice(4);
        vertice5 = new Vertice(5);
        vertice6 = new Vertice(6);
        
    }
    
    

    /**
     * Test of grafoVazio method, of class Grafo.
     */
    @Test
    public void testGrafoVazio() throws ObjetoProcuradoNaoExisteException {
        assertTrue(g.grafoVazio());
        g.inserirVertice(10);
        assertFalse(g.grafoVazio());
        g.removerVertice(10);
        assertTrue(g.grafoVazio());
        
     
    }

    /**
     * Test of inserirVertice method, of class Grafo.
     */
    @Test
    public void testInserirVertice() {
       g.inserirVertice(5);
       assertEquals(5, g.buscaVertice(5).getVertice());
       g.inserirVertice(10);
       assertEquals(10, g.buscaVertice(10).getVertice());
       g.inserirVertice(3);
       assertEquals(3, g.buscaVertice(3).getVertice());
       g.inserirVertice(4);
       assertEquals(4, g.buscaVertice(4).getVertice());
       assertEquals(5,g.buscaVertice(5).getVertice());
       assertEquals(10, g.buscaVertice(10).getVertice());
       assertEquals(3,g.buscaVertice(3).getVertice());
        
    }

    /**
     * Test of removerVertice method, of class Grafo.
     */
    @Test
    public void testRemoverVertice() throws Exception {
        g.inserirVertice(1);
        g.inserirVertice(3);
        g.inserirVertice(4);
        assertEquals(3, g.buscaVertice(3).getVertice());
        /*assertEquals(vertice1, g.removerVertice(3));
        assertEquals(vertice3, g.removerVertice(4));*/
       
    }

    /**
     * Test of inserirAresta method, of class Grafo.
     */
    @Test
    public void testInserirAresta() throws ObjetoProcuradoNaoExisteException {
        g.inserirVertice(1);
        g.inserirVertice(2);
        g.inserirAresta(1, 2, 2);
        
    }
    
    @Test
    public void testMenorCaminho() throws ObjetoProcuradoNaoExisteException, VerticeNaoPossuiArestasException{
        g.inserirVertice(1);
        g.inserirVertice(2);
        g.inserirAresta(1, 2, 2);
        
        g.inserirVertice(3);
        g.inserirAresta(1, 3, 3);
        
        g.inserirVertice(4);
        g.inserirAresta(2, 4, 5);
        
        g.inserirVertice(5);
        g.inserirAresta(3, 5, 5);
        
        g.inserirAresta(2, 5, 2);
        
        g.inserirAresta(5, 4, 1);
        
        g.inserirVertice(6);
        g.inserirAresta(4, 6, 2);
        
        g.inserirAresta(5, 6, 4);
        //menor caminho
        Vertice ultimo1 = g.menorCaminho(1, 6);
        assertEquals(vertice6, ultimo1);
        ultimo1 = ultimo1.getPai();
        assertEquals(vertice4, ultimo1);//pega o método equals do vértice??
        ultimo1 = ultimo1.getPai();
        assertEquals(vertice5, ultimo1);
        ultimo1 = ultimo1.getPai();
        assertEquals(vertice2, ultimo1);
        ultimo1 = ultimo1.getPai();
        assertEquals(vertice1, ultimo1);
        
        ultimo1 = g.menorCaminho(2, 6);
        
        assertEquals(vertice6, ultimo1);
        ultimo1 = ultimo1.getPai();
        assertEquals(vertice4, ultimo1);
        ultimo1 = ultimo1.getPai();
        assertEquals(vertice5, ultimo1);
        ultimo1= ultimo1.getPai();
        assertEquals(vertice2, ultimo1);
        
        try{
        assertNull(g.menorCaminho(3, 2));
        } catch(VerticeNaoPossuiArestasException e){}
        
       try{
        assertNull(g.menorCaminho(3, 2));}
       catch(VerticeNaoPossuiArestasException e){}
       
       g = new Grafo ();
       
       g.inserirVertice(1);
       g.inserirVertice(3);
       g.inserirVertice(5);
       g.inserirVertice(6);
       g.inserirVertice(4);
       
       g.inserirAresta(1, 3, 2);
       g.inserirAresta(3, 5, 1);
       g.inserirAresta(1, 6, 2);
       g.inserirAresta(6, 4, 3);
       
       ultimo1 = g.menorCaminho(1, 5);
       
       assertEquals(vertice5, ultimo1);
       ultimo1 = ultimo1.getPai();
       assertEquals(vertice3, ultimo1);
       ultimo1 = ultimo1.getPai();
       assertEquals(vertice1, ultimo1);                       
    }
    @Test
    public void testTodosCaminhos() throws ObjetoProcuradoNaoExisteException{
        
        g.inserirVertice(1);
        g.inserirVertice(2);
        g.inserirAresta(1, 2, 2);
        
        g.inserirVertice(3);
        g.inserirAresta(1, 3, 3);
        
        g.inserirVertice(4);
        g.inserirAresta(2, 4, 5);
        
        g.inserirVertice(5);
        g.inserirAresta(3, 5, 5);
        
        g.inserirAresta(2, 5, 2);
        
        g.inserirAresta(5, 4, 1);
        
        g.inserirVertice(6);
        g.inserirAresta(4, 6, 2);
        
        g.inserirAresta(5, 6, 4);
        g.todosCaminho();
        float adjacencia[][] = g.getAdjacencia();
        int caminhos[][] = g.getCaminhos();
        
        assertEquals(5,adjacencia[1][4], 0.01);
        assertEquals(4, adjacencia[1][5], 0.01);
        assertEquals(5, caminhos[1][4]);
        
    }
}
