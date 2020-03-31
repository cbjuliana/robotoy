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

public class ButtonSaveFile extends ToolbarButton {

    private final ICompilerFacade facade;
    private final JFileChooser saveFileDialog;

    public ButtonSaveFile(ICompilerFacade facade) {
        super(new ImageIcon(ClassLoader.getSystemResource("img/save_32px.png")), "Salvar (Ctrl+S)", "ctrl S");
        this.facade = facade;
        saveFileDialog = new JFileChooser();
        saveFileDialog.setFileFilter(new FileNameExtensionFilter(null, Constants.PROGRAM_FILE_EXTENSION));
        saveFileDialog.setSelectedFile(new File(Constants.DEFAULT_PROGRAM_FILE_NAME));
    }

    @Override
    protected AbstractAction getButtonAction() {
        return new AbstractAction() {

            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
                editor.setFocus();
            }
        };
    }

    private void saveFile() {
        File file = fileManager.getFile();
        if (file == null) {
            int selectedOption = saveFileDialog.showSaveDialog(DevelopmentFrame.SINGLETON);

            if (selectedOption != JFileChooser.APPROVE_OPTION) {
                return;
            }

            file = saveFileDialog.getSelectedFile();
            if (file.exists()) {
                selectedOption = JOptionPane.showConfirmDialog(DevelopmentFrame.SINGLETON, String.format(Constants.MESSAGE_OVERWRITE, file.getName()), "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (selectedOption != JOptionPane.YES_OPTION) {
                    return;
                }
            }
        }

        try {
            facade.save(file, editor.getSourceCode());
            fileManager.setModified(false, file);
        } catch (IOException exception) {
            console.log(Constants.MESSAGE_CANNOT_SAVE_FILE);
        }
    }

}
