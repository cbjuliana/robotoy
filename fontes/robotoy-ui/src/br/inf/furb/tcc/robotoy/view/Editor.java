package br.inf.furb.tcc.robotoy.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JPanel;

public final class Editor extends JPanel {

    public static final Editor SINGLETON = new Editor();
    private EditorPane editor;
    private File file;

    private Editor() {
        setLayout(new BorderLayout());

        editor = new EditorPane();
        editor.setFont(new Font("Consolas", Font.PLAIN, 15));
        editor.addKeyListener(new KeyAdapter() {

            private String beforeTyped = "";

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (getSourceCode().equals("")) {
                        return;
                    }
                }
                if (!beforeTyped.equals(getSourceCode())) {
                    beforeTyped = getSourceCode();
                    FileManagerStatusBar.SINGLETON.setModified(true);
                }
            }
        });

        add(editor, BorderLayout.CENTER);
    }

    public String getSourceCode() {
        return editor.getText();
    }

    public void setSourceCode(File file, String sourceCode) {
        this.file = file;
        editor.setText(sourceCode);
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void clear() {
        editor.setText("");
    }

    public void setFocus() {
        editor.setFocus();
    }
}
