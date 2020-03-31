package br.inf.furb.tcc.robotoy.view;

import javax.swing.JOptionPane;

public final class Dialogs {



    public static boolean showDiscardSourceCode() {
        int selectedOption = JOptionPane.showConfirmDialog(DevelopmentFrame.SINGLETON, Constants.MESSAGE_DISCARD_SOURCE_CODE, "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (selectedOption != JOptionPane.YES_OPTION) {
            return false;
        }
        return true;
    }

    private Dialogs() {
    }

}
