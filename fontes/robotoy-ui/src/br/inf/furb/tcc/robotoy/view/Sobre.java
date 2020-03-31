package br.inf.furb.tcc.robotoy.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Sobre extends JDialog {   
    public Sobre() {   
        this.setTitle("Sobre");   
        setModal(false);   
        
        JPanel configurationPanel = new JPanel();
        configurationPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        configurationPanel.setLayout(new GridLayout(4, 4, 1, 1));
        
        configurationPanel.add(new JLabel("---", JLabel.CENTER));
        configurationPanel.add(new JLabel("Robotoy (Vers�o 2)", JLabel.CENTER));
        configurationPanel.add(new JLabel("Ferramenta para ensino de programa��o para crian�as usando rob�s", JLabel.CENTER));
        configurationPanel.add(new JLabel("---", JLabel.CENTER));
        
        add(configurationPanel, BorderLayout.NORTH);
        
        pack();   
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();   
        this.setLocation((tela.width - this.getSize().width) / 2,   
             (tela.height - this.getSize().height) / 2);   
    }   
}