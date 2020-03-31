package br.inf.furb.tcc.robotoy.view.button;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import br.inf.furb.tcc.robotoy.view.Sobre;

public class ButtonInfo extends ToolbarButton {
	
	private final Sobre sobre;
	
	public ButtonInfo() {
		super(new ImageIcon(ClassLoader.getSystemResource("img/info_32px.png")), "Ajuda (F1)", "F1");
		sobre = new Sobre();
	}

	@Override
	protected AbstractAction getButtonAction() {
		return new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				sobre.setVisible(true);
				editor.setFocus();
			}
		};
	}
	
	

}
