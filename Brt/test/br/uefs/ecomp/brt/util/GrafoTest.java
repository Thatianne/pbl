/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.brt.util;

import br.uefs.ecomp.brt.util.Exception.ObjetoProcuradoNaoExisteException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thatianne
 */
public class GrafoTest {
    
    private Grafo g;
    
    @Before
    public void setUp() {
        g = new Grafo ();
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
       assertEquals(4, g.buscaVertice(4).getVisitado());
       assertEquals(5,g.buscaVertice(5).getVertice());
       assertEquals(10, g.buscaVertice(10).getVertice());
       assertEquals(3,g.buscaVertice(3).getVertice());
        
    }

    /**
     * Test of removerVertice method, of class Grafo.
     */
    @Test
    public void testRemoverVertice() throws Exception {
        
       
    }

    /**
     * Test of inserirAresta method, of class Grafo.
     */
    @Test
    public void testInserirAresta() {
        System.out.println("inserirAresta");
        int u = 0;
        int v = 0;
        int peso = 0;
        Grafo instance = new Grafo();
        instance.inserirAresta(u, v, peso);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of menorCaminho method, of class Grafo.
     */
    @Test
    public void testMenorCaminho() {
        System.out.println("menorCaminho");
        int u = 0;
        Grafo instance = new Grafo();
        instance.menorCaminho(u);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
