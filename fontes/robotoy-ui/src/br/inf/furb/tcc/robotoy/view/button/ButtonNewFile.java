package br.inf.furb.tcc.robotoy.view.button;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import br.inf.furb.tcc.robotoy.view.Dialogs;

public class ButtonNewFile extends ToolbarButton {

    public ButtonNewFile() {
        super(new ImageIcon(ClassLoader.getSystemResource("img/new_32px.png")), "Novo (Ctrl+N)", "ctrl N");
    }

    @Override
    protected AbstractAction getButtonAction() {
        return new AbstractAction() {

            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileManager.isModified()) {
                    boolean discardChanges = Dialogs.showDiscardSourceCode();
                    if (!discardChanges) {
                        editor.setFocus();
                        return;
                    }
                }
                editor.clear();
                editor.setFocus();
                fileManager.setModified(false, null);
            }
        };
    }

}
