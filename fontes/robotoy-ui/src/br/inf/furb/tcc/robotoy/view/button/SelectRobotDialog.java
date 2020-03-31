package br.inf.furb.tcc.robotoy.view.button;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;

import br.inf.furb.tcc.robotoy.common.PropertyFile;
import br.inf.furb.tcc.robotoy.common.util.Util;
import br.inf.furb.tcc.robotoy.view.Console;
import br.inf.furb.tcc.robotoy.view.Constants;
import br.inf.furb.tcc.robotoy.view.PropertyName;
import br.inf.furb.tcc.robotoy.view.RobotType;

public class SelectRobotDialog extends JDialog {

	private final JRadioButton mindstormsRadioButton;
	
	private final JRadioButton arduinoMegaRadioButton;

	public SelectRobotDialog() {
		setTitle("Selecionar robô");
		setIconImage(new ImageIcon(ClassLoader.getSystemResource("img/robot_16px.png")).getImage());
		setMinimumSize(new Dimension(200, 90));
		setLayout(new BorderLayout());
		setResizable(false);
		setModal(true);

		mindstormsRadioButton = new JRadioButton("LEGO Mindstorms");
		mindstormsRadioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveRobot();
			}
		});
		
		arduinoMegaRadioButton = new JRadioButton("Arduino Mega");
		arduinoMegaRadioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveRobot();
			}
		});

		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(mindstormsRadioButton);
		radioButtonGroup.add(arduinoMegaRadioButton);

		JPanel radioButtonsPanel = new JPanel(new GridLayout(2, 1));
		radioButtonsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		radioButtonsPanel.add(mindstormsRadioButton);
		radioButtonsPanel.add(arduinoMegaRadioButton);

		JPanel buttonOkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonOkPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JButton okButton = new JButton("OK");
		okButton.setPreferredSize(new Dimension(85, 22));
		buttonOkPanel.add(okButton);

		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});

		getRootPane().registerKeyboardAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

		add(radioButtonsPanel, BorderLayout.CENTER);
		add(buttonOkPanel, BorderLayout.SOUTH);

		selectRobotType();

		pack();
		setLocationRelativeTo(null);
	}

	private Properties loadPreferences() {
		File file = new File(PropertyFile.ROBOT_CONFIGURATION_FILE);
		if (!file.exists()) {
			mindstormsRadioButton.setSelected(true);
			saveRobot();
		}

		Properties properties;
		try {
			properties = Util.loadProperties(file);
			return properties;
		} catch (IOException e) {
			Console.SINGLETON.log("Não foi possível carregar o tipo de robô.");
			return null;
		}
	}
	
	public RobotType getRobotType() {
		Properties properties = loadPreferences();
		RobotType robotType = (RobotType) RobotType.getRobotByType(properties.getProperty(PropertyName.ROBOT_TYPE.getName()));
		return robotType;
	}
	
	private void selectRobotType() {
		RobotType robotType = getRobotType();
		
		if (robotType == RobotType.LEGO_MINDSTORMS) {
			mindstormsRadioButton.setSelected(true);
		} else if (robotType == RobotType.ARDUINO_MEGA) {
			arduinoMegaRadioButton.setSelected(true);
		}		
	}
	
	private void saveRobot() {
		Properties properties = new Properties();
		
		if (mindstormsRadioButton.isSelected()) {		
			properties.put(PropertyName.ROBOT_TYPE.getName(), RobotType.LEGO_MINDSTORMS.getType());
			properties.put(PropertyName.ROBOT_INTEGRATOR.getName(), "br.inf.furb.tcc.robotoy.integrator.lejos.LejosIntegrator");
			properties.put(PropertyName.ROBOT_CONFIGURATION_FILE.getName(), PropertyFile.LEJOS_ROBOT_CONFIGURATION_FILE);
		} else if (arduinoMegaRadioButton.isSelected()) {
			properties.put(PropertyName.ROBOT_TYPE.getName(), RobotType.ARDUINO_MEGA.getType());
			properties.put(PropertyName.ROBOT_INTEGRATOR.getName(), "br.inf.furb.tcc.robotoy.integrator.arduino.ArduinoIntegrator");
			properties.put(PropertyName.ROBOT_CONFIGURATION_FILE.getName(), PropertyFile.ARDUINO_MEGA_ROBOT_CONFIGURATION_FILE);
		}
		
		try {
			Util.saveProperties(properties, new File(PropertyFile.ROBOT_CONFIGURATION_FILE));
		} catch (IOException e) {
			Console.SINGLETON.log("Não foi possível salvar o tipo de robô.");
		}
	}

}
