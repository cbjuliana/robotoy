package br.inf.furb.tcc.robotoy.view.button;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import br.inf.furb.tcc.robotoy.view.Console;
import br.inf.furb.tcc.robotoy.view.Editor;
import br.inf.furb.tcc.robotoy.view.FileManagerStatusBar;

public abstract class ToolbarButton extends JButton {

    private static final String ACTION_KEY = "action";
    protected FileManagerStatusBar fileManager = FileManagerStatusBar.SINGLETON;
    protected Editor editor = Editor.SINGLETON;
    protected Console console = Console.SINGLETON;

    public ToolbarButton(ImageIcon icon, String toolTipText, String keyStroke) {
        super(icon);
        setToolTipText(toolTipText);
        addActionListener(getButtonAction());

        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(keyStroke), ACTION_KEY);
        ActionMap actionMap = getActionMap();
        actionMap.put(ACTION_KEY, getButtonAction());
    }

    protected abstract AbstractAction getButtonAction();

}
