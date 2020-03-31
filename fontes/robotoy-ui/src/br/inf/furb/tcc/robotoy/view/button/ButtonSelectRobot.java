package br.inf.furb.tcc.robotoy.view.button;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

public class ButtonSelectRobot extends ToolbarButton {

	private final SelectRobotDialog selectRobotDialog;
	
	public ButtonSelectRobot() {
		super(new ImageIcon(ClassLoader.getSystemResource("img/select_robot_32px.png")), "Selecionar robô (F12)", "F12");
		selectRobotDialog = new SelectRobotDialog();
	}

	@Override
	protected AbstractAction getButtonAction() {
		return new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				selectRobotDialog.setVisible(true);
				editor.setFocus();
			}
		};
	}

}
