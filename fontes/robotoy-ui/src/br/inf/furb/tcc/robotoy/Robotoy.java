package br.inf.furb.tcc.robotoy;

import br.inf.furb.tcc.robotoy.view.DevelopmentFrame;
import br.inf.furb.tcc.robotoy.view.Editor;

public final class Robotoy {

    public static void main(String[] args) {
        DevelopmentFrame application = DevelopmentFrame.SINGLETON;
        application.setVisible(true);
        Editor.SINGLETON.setFocus();
    }

}
