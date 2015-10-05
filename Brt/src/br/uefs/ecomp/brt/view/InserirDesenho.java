/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.brt.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

/**
 *
 * @author thatianne
 */
public class InserirDesenho extends JPanel{
    Ellipse2D vertice = new Ellipse2D.Float(30, 30, 30, 30);
 
    public InserirDesenho(){
        repaint();
    }
    
    
    @Override
    public void paint(Graphics g){
        Graphics2D desenha = (Graphics2D) g;
        desenha.drawLine(100, 150, 400, 300);
        desenha.draw(vertice);
        
    }
    
    
    public void desenhaVertice(int n){
        
    }
    
    public void desenhaAresta(int comeco, int fim, int distancia){
        
    }

    
}
