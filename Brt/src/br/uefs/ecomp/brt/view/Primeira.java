/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.brt.view;

import br.uefs.ecomp.brt.Controller.Controller;
import br.uefs.ecomp.brt.util.Exception.ObjetoProcuradoNaoExisteException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author thatianne
 */
public class Primeira extends JFrame {

    private Controller controler;

    private JTextArea ta;
    private JPanel painel;
    private BorderLayout layout;
    private JMenuBar barraMenu;
    private JMenu mArquivo;
    private JMenuItem miImportar, miSalvar, miNovo;
    private JButton addVertice;
    private String valor;
    private int vertice;    
    private JFileChooser arquivo;
    private InserirDesenho desenho;

    public Primeira() {
        this.setTitle("Grafo");
        montaTela();

    }

    public void montaTela() {
        controler = new Controller();
        
        desenho = new InserirDesenho();
        
        layout = new BorderLayout();
        painel = new JPanel(layout);

        //barra de menu
        barraMenu = new JMenuBar();
        mArquivo = new JMenu("Arquivo");
        miNovo = new JMenuItem("Novo");
        miSalvar = new JMenuItem("Salvar");
        miImportar = new JMenuItem("Importar");
        this.setJMenuBar(barraMenu);
        barraMenu.add(mArquivo);
        mArquivo.add(miNovo);
        mArquivo.add(miSalvar);
        mArquivo.add(miImportar);

        miImportar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //Responsável por mostrar uma janela de escolha de arquivo
                arquivo = new JFileChooser();

                //Determina o tipo de arquivo que será lido
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("txt", "txt");

                //Troca o filtro
                arquivo.setFileFilter(filtro);

                //Abre janela para pesquisa de arquivo, retorna um numero se o usuario selecionar um arquivo
                int opcao = arquivo.showOpenDialog(null);
                

                //Verifica se o usuario selecionou um arquivo
                if (opcao == JFileChooser.APPROVE_OPTION) {
                    System.out.println("Deu certo");                    
                    try {
                        try {
                            controler.doArquivo(arquivo.getSelectedFile());
                        } catch (ObjetoProcuradoNaoExisteException ex) {
                            JOptionPane.showMessageDialog(null, "O objeto não existe");
                        }
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "O arquivo não existe");                        
                        System.out.println("O arquivo não existe");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um arquivo");
                    System.out.println("Não deu certo");
                }                               
            }
        });

        addVertice = new JButton("Adicionar vertice");
        miImportar = new JMenuItem("Exportar arquivo");
        addVertice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valor = JOptionPane.showInputDialog(null, "Digite o valor do vértice");
                try {
                    vertice = (int) Double.parseDouble(valor);                     
                    controler.inserirVertice(vertice);
                    desenho.desenhaVertice(vertice);
                    
                } catch (NumberFormatException num) {
                    System.out.println("Fazer caixa de mensagem depois");
                }

            }
        });

        painel.add(addVertice, layout.NORTH);
        
        //os desenhos vão estar no centro, vai ter um panel no layout.CENTER
        
        painel.add(desenho, layout.CENTER);
        
        

        this.setContentPane(painel);
        this.setSize(640, 480);
        this.setVisible(true);
        //DESLIGA A APLICAÇÃO QUANDO O USUÁRIO FECHA A JANELA
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

    }

}
