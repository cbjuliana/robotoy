package br.inf.furb.tcc.robotoy.view;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import br.inf.furb.tcc.robotoy.control.ICompilerFacade;
import br.inf.furb.tcc.robotoy.control.impl.CompilerFacade;
import br.inf.furb.tcc.robotoy.view.button.ButtonConfigure;
import br.inf.furb.tcc.robotoy.view.button.ButtonInfo;
import br.inf.furb.tcc.robotoy.view.button.ButtonNewFile;
import br.inf.furb.tcc.robotoy.view.button.ButtonOpenFile;
import br.inf.furb.tcc.robotoy.view.button.ButtonRun;
import br.inf.furb.tcc.robotoy.view.button.ButtonSaveFile;
import br.inf.furb.tcc.robotoy.view.button.ButtonSelectRobot;

public final class Toolbar extends JPanel {

    public static final Toolbar SINGLETON = new Toolbar();

    private Toolbar() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        ICompilerFacade facade = new CompilerFacade();
        add(new ButtonNewFile());
        add(new JSeparator(SwingConstants.VERTICAL));
        add(new ButtonOpenFile(facade));
        add(new ButtonSaveFile(facade));
        add(new JSeparator(SwingConstants.VERTICAL));
        add(new ButtonRun());
        add(new JSeparator(SwingConstants.VERTICAL));
        add(new ButtonConfigure());
        add(new ButtonSelectRobot());
        add(new JSeparator(SwingConstants.VERTICAL));
        add(new ButtonInfo());
    }

}
