package br.inf.furb.tcc.robotoy.view.lejos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

import br.inf.furb.tcc.robotoy.common.PropertyFile;
import br.inf.furb.tcc.robotoy.common.lejos.ConfigurationPropertyName;
import br.inf.furb.tcc.robotoy.common.util.Util;
import br.inf.furb.tcc.robotoy.view.Console;

public class MindstormsRobotConfigurationDialog extends JDialog {

    private final JComboBox<SensorPort> comboBoxColorSensorPort;
    private final JComboBox<SensorPort> comboBoxDistanceSensorPort;
    private final JComboBox<MotorPort> comboBoxLeftWheelPort;
    private final JComboBox<MotorPort> comboBoxRightWheelPort;
    private final JComboBox<MotorPort> comboBoxMultiuseMotorPort;
    private final JTextPane errorMessagesPanel;

    public MindstormsRobotConfigurationDialog() {
        setTitle("Configurar sensores e motores");
        setMinimumSize(new Dimension(250, 50));
        setIconImage(new ImageIcon(ClassLoader.getSystemResource("img/robot_16px.png")).getImage());
        setLayout(new BorderLayout());
        setResizable(false);
        setModal(true);

        comboBoxColorSensorPort = new JComboBox<SensorPort>(SensorPort.values());
        comboBoxDistanceSensorPort = new JComboBox<SensorPort>(SensorPort.values());
        comboBoxLeftWheelPort = new JComboBox<MotorPort>(MotorPort.values());
        comboBoxRightWheelPort = new JComboBox<MotorPort>(MotorPort.values());
        comboBoxMultiuseMotorPort = new JComboBox<MotorPort>(MotorPort.values());

        JPanel configurationPanel = new JPanel();
        configurationPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        configurationPanel.setLayout(new GridLayout(5, 2, 5, 5));
        configurationPanel.add(new JLabel("Sensor de cor", JLabel.RIGHT));
        configurationPanel.add(comboBoxColorSensorPort);
        configurationPanel.add(new JLabel("Sensor de distância", JLabel.RIGHT));
        configurationPanel.add(comboBoxDistanceSensorPort);
        configurationPanel.add(new JLabel("Motor esquerdo", JLabel.RIGHT));
        configurationPanel.add(comboBoxLeftWheelPort);
        configurationPanel.add(new JLabel("Motor direito", JLabel.RIGHT));
        configurationPanel.add(comboBoxRightWheelPort);
        configurationPanel.add(new JLabel("Motor multiuso", JLabel.RIGHT));
        configurationPanel.add(comboBoxMultiuseMotorPort);

        errorMessagesPanel = new JTextPane();
        errorMessagesPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        errorMessagesPanel.setEditable(false);
        errorMessagesPanel.setBackground(null);
        errorMessagesPanel.setForeground(Color.RED);
        errorMessagesPanel.setVisible(false);

        JPanel buttonOkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonOkPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(85, 22));
        buttonOkPanel.add(okButton);

        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                validateSelectedOptions();
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

        add(configurationPanel, BorderLayout.NORTH);
        add(errorMessagesPanel, BorderLayout.CENTER);
        add(buttonOkPanel, BorderLayout.SOUTH);

        loadPreferences();

        pack();
        setLocationRelativeTo(null);
    }

    private void loadPreferences() {
    	File file = new File(PropertyFile.LEJOS_ROBOT_CONFIGURATION_FILE);
    	if (!file.exists()) {
    		return;
    	}

    	Properties properties;
		try {
			properties = Util.loadProperties(file);
		} catch (IOException e) {
			Console.SINGLETON.log("Não foi possível carregar a configuração do robô LEGO Mindstorms.");
			return;
		}
    	
		String propertyValue = properties.getProperty(ConfigurationPropertyName.COLOR_SENSOR_PORT.getName());
		if (propertyValue != null) {
			comboBoxColorSensorPort.setSelectedItem(SensorPort.getPortByValue(propertyValue));
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.DISTANCE_SENSOR_PORT.getName());
		if (propertyValue != null) {
			comboBoxDistanceSensorPort.setSelectedItem(SensorPort.getPortByValue(propertyValue));
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.MULTIUSE_MOTOR_PORT.getName());
		if (propertyValue != null) {
			comboBoxMultiuseMotorPort.setSelectedItem(MotorPort.getPortByValue(propertyValue));
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.LEFT_WHEEL_PORT.getName());
		if (propertyValue != null) {
			comboBoxLeftWheelPort.setSelectedItem(MotorPort.getPortByValue(propertyValue));
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.RIGHT_WHEEL_PORT.getName());
		if (propertyValue != null) {
			comboBoxRightWheelPort.setSelectedItem(MotorPort.getPortByValue(propertyValue));
		}
    }

    private void savePreferences() {
    	Properties properties = new Properties();
    	
    	SensorPort sensorPortSelected = (SensorPort) comboBoxColorSensorPort.getSelectedItem();
		if (sensorPortSelected != SensorPort.NONE) {
    		properties.put(ConfigurationPropertyName.COLOR_SENSOR_PORT.getName(), sensorPortSelected.getValue());
    	}
		
		sensorPortSelected = (SensorPort) comboBoxDistanceSensorPort.getSelectedItem();
		if (sensorPortSelected != SensorPort.NONE) {
			properties.put(ConfigurationPropertyName.DISTANCE_SENSOR_PORT.getName(), sensorPortSelected.getValue());
		}
		
		MotorPort motorPortSelected = (MotorPort) comboBoxLeftWheelPort.getSelectedItem();
		if (motorPortSelected != MotorPort.NONE) {
			properties.put(ConfigurationPropertyName.LEFT_WHEEL_PORT.getName(), motorPortSelected.getValue());
		}
		
		motorPortSelected = (MotorPort) comboBoxRightWheelPort.getSelectedItem();
		if (motorPortSelected != MotorPort.NONE) {
			properties.put(ConfigurationPropertyName.RIGHT_WHEEL_PORT.getName(), motorPortSelected.getValue());
		}
		
		motorPortSelected = (MotorPort) comboBoxMultiuseMotorPort.getSelectedItem();
		if (motorPortSelected != MotorPort.NONE) {
			properties.put(ConfigurationPropertyName.MULTIUSE_MOTOR_PORT.getName(), motorPortSelected.getValue());
		}
		
		try {
			Util.saveProperties(properties, new File(PropertyFile.LEJOS_ROBOT_CONFIGURATION_FILE));
		} catch (IOException e) {
			Console.SINGLETON.log("Não foi possível salvar a configuração do robô LEGO Mindstorms.");
		}
    }

    private void validateSelectedOptions() {
        List<String> errors = new ArrayList<String>();
        if (errors.isEmpty()) {
            validateConflictingPorts(errors);
        }

        if (!errors.isEmpty()) {
            StringBuilder errorMessages = new StringBuilder();
            for (int i = 0; i < errors.size() - 1; i++) {
                String error = errors.get(i);
                errorMessages.append(error);
                errorMessages.append("\n");
            }
            errorMessages.append(errors.get(errors.size() - 1));
            this.errorMessagesPanel.setText(errorMessages.toString());
            this.errorMessagesPanel.setVisible(true);
        } else {
            errorMessagesPanel.setText("");
            errorMessagesPanel.setVisible(false);
            errorMessagesPanel.update(errorMessagesPanel.getGraphics());
            setVisible(false);
            savePreferences();
        }

        pack();
        pack();
        setLocationRelativeTo(null);
    }

    private void validateConflictingPorts(List<String> errors) {
        Object selectedItem = comboBoxColorSensorPort.getSelectedItem();
        Object otherSelectedItem = comboBoxDistanceSensorPort.getSelectedItem();
        if (selectedItem == otherSelectedItem && selectedItem != SensorPort.NONE) {
            errors.add("Sensores conectados na mesma porta: cor e distância.");
        }

        selectedItem = comboBoxLeftWheelPort.getSelectedItem();
        otherSelectedItem = comboBoxRightWheelPort.getSelectedItem();
        if (selectedItem == otherSelectedItem && selectedItem != MotorPort.NONE) {
            errors.add("Motores conectados na mesma porta: esquerdo e direito.");
        }

        otherSelectedItem = comboBoxMultiuseMotorPort.getSelectedItem();
        if (selectedItem == otherSelectedItem && selectedItem != MotorPort.NONE) {
            errors.add("Motores conectados na mesma porta: esquerdo e sensor de distância.");
        }

        selectedItem = comboBoxRightWheelPort.getSelectedItem();
        otherSelectedItem = comboBoxMultiuseMotorPort.getSelectedItem();
        if (selectedItem == otherSelectedItem && selectedItem != MotorPort.NONE) {
            errors.add("Motores conectados na mesma porta: direito e sensor de distância.");
        }
    }

    private enum SensorPort {

        NONE("", ""),

        PORT_1("Porta 1", "1"),

        PORT_2("Porta 2", "2"),

        PORT_3("Porta 3", "3"),

        PORT_4("Porta 4", "4");

        private final String name;
        private final String value;

        private SensorPort(String name, String value) {
            this.name = name;
            this.value = value;
        }

		public String getValue() {
			return value;
		}
        
        @Override
        public String toString() {
            return name;
        }
        
        public static SensorPort getPortByValue(String value) {
			SensorPort[] values = SensorPort.values();
			for (SensorPort sensorPort : values) {
				if (sensorPort.getValue().equals(value)) {
					return sensorPort;
				}
			}
			return null;
		}

    }

    private enum MotorPort {

        NONE("", ""),

        PORT_A("Porta A", "A"),

        PORT_B("Porta B", "B"),

        PORT_C("Porta C", "C");

        private final String name;
        private final String value;

        private MotorPort(String name, String value) {
            this.name = name;
			this.value = value;
        }
        
		public String getValue() {
			return value;
		}

        @Override
        public String toString() {
            return name;
        }
        
        public static MotorPort getPortByValue(String value) {
			MotorPort[] values = MotorPort.values();
			for (MotorPort motorPort : values) {
				if (motorPort.getValue().equals(value)) {
					return motorPort;
				}
			}
			return null;
		}
    }

}
