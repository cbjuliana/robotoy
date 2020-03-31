package br.inf.furb.tcc.robotoy.view.button;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.inf.furb.tcc.robotoy.control.ICompilerFacade;
import br.inf.furb.tcc.robotoy.view.Constants;
import br.inf.furb.tcc.robotoy.view.DevelopmentFrame;
import br.inf.furb.tcc.robotoy.view.Dialogs;

public class ButtonOpenFile extends ToolbarButton {

    private final ICompilerFacade facade;
    private final JFileChooser openFileDialog;

    public ButtonOpenFile(ICompilerFacade facade) {
        super(new ImageIcon(ClassLoader.getSystemResource("img/open_32px.png")), "Abrir (Ctrl+O)", "ctrl O");
        this.facade = facade;
        openFileDialog = new JFileChooser();
        openFileDialog.setFileFilter(new FileNameExtensionFilter(null, Constants.PROGRAM_FILE_EXTENSION));
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
                openFile();
                editor.setFocus();
            }
        };
    }

    private void openFile() {
        int selectedOption = openFileDialog.showOpenDialog(DevelopmentFrame.SINGLETON);
        if (selectedOption == JFileChooser.APPROVE_OPTION) {
            File selectedFile = openFileDialog.getSelectedFile();
            try {
                if (!selectedFile.exists()) {
                    JOptionPane.showMessageDialog(DevelopmentFrame.SINGLETON, Constants.MESSAGE_FILE_NOT_FOUND, "Abrir", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                String sourceCode = facade.open(selectedFile);
                editor.setSourceCode(selectedFile, sourceCode);
                fileManager.setModified(false, selectedFile);
            } catch (IOException exception) {
                console.log(Constants.MESSAGE_CANNOT_OPEN_FILE);
            }
        }
    }

}
