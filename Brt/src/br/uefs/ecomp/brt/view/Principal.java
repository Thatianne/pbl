/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.brt.view;

import javax.swing.JFrame;

/**
 *
 * @author thatianne
 */
public class Principal{
    
    private JFrame janela;
    
    
    public static void main(String[] args) {
        Primeira janela = new Primeira();
        janela.montaTela(); 
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        
        
    }
    
    private void prepararJanela (){
        janela = new JFrame ("Simulador BRT");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //janela.pack();
        janela.setSize(600, 600);
        
    }
}
