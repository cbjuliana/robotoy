package br.inf.furb.tcc.robotoy.view;

import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class FileManagerStatusBar extends JPanel {

    public static final FileManagerStatusBar SINGLETON = new FileManagerStatusBar();
    private static final String MODIFIED = "Modificado";
    private static final String UNMODIFIED = "Não modificado";
    private static final String MODIFIED_WITH_FILE = "Modificado - %s";
    private static final String UNMODIFIED_WITH_FILE = "Não modificado - %s";

    private final JLabel sourceCodeStatus;
    private boolean modified;
    private File file;

    private FileManagerStatusBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 3, 3));

        sourceCodeStatus = new JLabel(UNMODIFIED);
        add(sourceCodeStatus);
    }

    public File getFile() {
        return file;
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified, File file) {
        this.modified = modified;
        this.file = file;
        if (modified) {
            if (file == null) {
                sourceCodeStatus.setText(MODIFIED);
            } else {
                sourceCodeStatus.setText(String.format(MODIFIED_WITH_FILE, file.getAbsolutePath()));
            }
        } else {
            if (file == null) {
                sourceCodeStatus.setText(UNMODIFIED);
            } else {
                sourceCodeStatus.setText(String.format(UNMODIFIED_WITH_FILE, file.getAbsolutePath()));
            }
        }
    }

    public void setModified(boolean modified) {
        setModified(modified, file);
    }

}
