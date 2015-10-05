/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.brt.view;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author Sarah
 */
public class JApresentacao {
    private JPanel painel;
    private JButton iniciar;
    private JLabel label;
    private BorderLayout layout;

    
    public void aprestJanela1 (){
        painel = new JPanel ();
        iniciar =  new JButton ("Iniciar");
        label = new JLabel ();
        label.setText("Seja Bem Vindo ao \n Simulador BRT");
        label.setFont(new Font ("SansSerif", Font.PLAIN, 70));
        painel.add(label,BorderLayout.CENTER );
        
        
        
    
    }
        
    
}
