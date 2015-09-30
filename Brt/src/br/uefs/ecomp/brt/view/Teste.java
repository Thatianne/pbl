/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.brt.view;

import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Sarah
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //Responsável por mostrar uma janela de escolha de arquivo
        JFileChooser arquivo = new JFileChooser ();
        
        //Determina o tipo de arquivo que será lido
        FileNameExtensionFilter filtro = new FileNameExtensionFilter ("txt", "txt");
        
        //Troca o filtro
        arquivo.setFileFilter(filtro);
        
        //Abre janela, se houver algum JFrame passa como parametro
        int opcao = arquivo.showOpenDialog(null);
        
        if (opcao == JFileChooser.APPROVE_OPTION) {  
            System.out.print(arquivo.getSelectedFile().getCanonicalPath());       }
        else{}
        
        
    
}
}
