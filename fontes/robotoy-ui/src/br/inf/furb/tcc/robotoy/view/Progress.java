package br.inf.furb.tcc.robotoy.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JProgressBar;

public class Progress extends JDialog {   
    public Progress() {   
        this.setTitle("Aguarde...");   
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setUndecorated(true);
        setModal(false);   
        JProgressBar progress = new JProgressBar();   
        progress.setIndeterminate(true);   
        progress.setSize(200,20);   
        getContentPane().add(progress);   
        pack();   
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();   
        this.setLocation((tela.width - this.getSize().width) / 2,   
             (tela.height - this.getSize().height) / 2);   
    }   
}