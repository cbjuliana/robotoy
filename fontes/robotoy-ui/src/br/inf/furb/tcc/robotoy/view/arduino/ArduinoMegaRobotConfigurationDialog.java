package br.inf.furb.tcc.robotoy.view.arduino;

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
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

import br.furb.tcc.robotoy.common.arduino.ConfigurationPropertyName;
import br.inf.furb.tcc.robotoy.common.PropertyFile;
import br.inf.furb.tcc.robotoy.common.util.Util;
import br.inf.furb.tcc.robotoy.view.Console;

public class ArduinoMegaRobotConfigurationDialog extends JDialog {
	
	//Falta configurar LCD e Sensor de Cor;
	//Incluir Enum DigitalPort
	
	private final JTextField textFieldBoard;
	
	private final JTextField textFieldIDEArduino;
	
	private final JTextField textFieldPort;
	
    private final JComboBox<AnalogicPort> comboBoxBuzzerPort;
    
    private final JComboBox<AnalogicPort> comboBoxDistanceSensorTriggerPort;
    private final JComboBox<AnalogicPort> comboBoxDistanceSensorEchoPort;
    
    private final JComboBox<MotorPort> comboBoxLeftWheelPort;
    private final JComboBox<MotorPort> comboBoxRightWheelPort;
    
    private final JComboBox<ServoMotorPort> comboBoxMultiuseMotorPort;
    
    private final JComboBox<DigitalPort> comboBoxColotSensorS0Port;
    private final JComboBox<DigitalPort> comboBoxColotSensorS1Port;
    private final JComboBox<DigitalPort> comboBoxColotSensorS2Port;
    private final JComboBox<DigitalPort> comboBoxColotSensorS3Port;
    private final JComboBox<DigitalPort> comboBoxColotSensorOutPort;
    
    private final JComboBox<DigitalPort> comboBoxLcdPin1Port;
    private final JComboBox<PWMPort> comboBoxLcdPin2Port;
    private final JComboBox<PWMPort> comboBoxLcdPin3Port;
    private final JComboBox<DigitalPort> comboBoxLcdPin4Port;
    private final JComboBox<PWMPort> comboBoxLcdPin5Port;
    private final JComboBox<DigitalPort> comboBoxLcdPin6Port;
    
    private final JTextPane errorMessagesPanel;

    public ArduinoMegaRobotConfigurationDialog() {
        setTitle("Configurar sensores e motores");
        setMinimumSize(new Dimension(500, 100));
        setIconImage(new ImageIcon(ClassLoader.getSystemResource("img/robot_16px.png")).getImage());
        setLayout(new BorderLayout());
        setResizable(false);
        setModal(true);
        
        textFieldBoard = new JTextField();
        textFieldIDEArduino = new JTextField();
        textFieldPort = new JTextField();
        
        comboBoxDistanceSensorTriggerPort = new JComboBox<AnalogicPort>(AnalogicPort.values());
        comboBoxDistanceSensorEchoPort = new JComboBox<AnalogicPort>(AnalogicPort.values());
        
        comboBoxBuzzerPort = new JComboBox<AnalogicPort>(AnalogicPort.values());
        comboBoxMultiuseMotorPort = new JComboBox<ServoMotorPort>(ServoMotorPort.values());
        
        comboBoxLeftWheelPort = new JComboBox<MotorPort>(MotorPort.values());
        comboBoxRightWheelPort = new JComboBox<MotorPort>(MotorPort.values());
        
        comboBoxLcdPin1Port = new JComboBox<DigitalPort>(DigitalPort.values());
        comboBoxLcdPin2Port = new JComboBox<PWMPort>(PWMPort.values());
        comboBoxLcdPin3Port = new JComboBox<PWMPort>(PWMPort.values());
        comboBoxLcdPin4Port = new JComboBox<DigitalPort>(DigitalPort.values());
        comboBoxLcdPin5Port = new JComboBox<PWMPort>(PWMPort.values());
        comboBoxLcdPin6Port = new JComboBox<DigitalPort>(DigitalPort.values());
        
        comboBoxColotSensorS0Port = new JComboBox<DigitalPort>(DigitalPort.values());
        comboBoxColotSensorS1Port = new JComboBox<DigitalPort>(DigitalPort.values());
        comboBoxColotSensorS2Port = new JComboBox<DigitalPort>(DigitalPort.values());
        comboBoxColotSensorS3Port = new JComboBox<DigitalPort>(DigitalPort.values());
        comboBoxColotSensorOutPort = new JComboBox<DigitalPort>(DigitalPort.values());

        JPanel configurationPanel = new JPanel();
        configurationPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        configurationPanel.setLayout(new GridLayout(20, 10, 10, 5));
        
        configurationPanel.add(new JLabel("Placa Arduino - modelo", JLabel.RIGHT));
        configurationPanel.add(textFieldBoard);
        
        configurationPanel.add(new JLabel("Placa Arduino - porta", JLabel.RIGHT));
        configurationPanel.add(textFieldPort);
        
        configurationPanel.add(new JLabel("Instalação Arduino IDE", JLabel.RIGHT));
        configurationPanel.add(textFieldIDEArduino);
        
        configurationPanel.add(new JLabel("Sensor de distância - trigger", JLabel.RIGHT));
        configurationPanel.add(comboBoxDistanceSensorTriggerPort);
        configurationPanel.add(new JLabel("Sensor de distância - echo", JLabel.RIGHT));
        configurationPanel.add(comboBoxDistanceSensorEchoPort); 
        
        configurationPanel.add(new JLabel("Buzzer", JLabel.RIGHT));
        configurationPanel.add(comboBoxBuzzerPort);
        
        configurationPanel.add(new JLabel("Motor multiuso", JLabel.RIGHT));
        configurationPanel.add(comboBoxMultiuseMotorPort);
        
        configurationPanel.add(new JLabel("Motor esquerdo", JLabel.RIGHT));
        configurationPanel.add(comboBoxLeftWheelPort);
        configurationPanel.add(new JLabel("Motor direito", JLabel.RIGHT));
        configurationPanel.add(comboBoxRightWheelPort);
        
        configurationPanel.add(new JLabel("LCD - pino 1", JLabel.RIGHT));
        configurationPanel.add(comboBoxLcdPin1Port);
        
        configurationPanel.add(new JLabel("LCD - pino 2", JLabel.RIGHT));
        configurationPanel.add(comboBoxLcdPin2Port);
        
        configurationPanel.add(new JLabel("LCD - pino 3", JLabel.RIGHT));
        configurationPanel.add(comboBoxLcdPin3Port);
        
        configurationPanel.add(new JLabel("LCD - pino 4", JLabel.RIGHT));
        configurationPanel.add(comboBoxLcdPin4Port);
        
        configurationPanel.add(new JLabel("LCD - pino 5", JLabel.RIGHT));
        configurationPanel.add(comboBoxLcdPin5Port);
        
        configurationPanel.add(new JLabel("LCD - pino 6", JLabel.RIGHT));
        configurationPanel.add(comboBoxLcdPin6Port);
        
        configurationPanel.add(new JLabel("Sensor de cor - s0", JLabel.RIGHT));
        configurationPanel.add(comboBoxColotSensorS0Port);
        
        configurationPanel.add(new JLabel("Sensor de cor - s1", JLabel.RIGHT));
        configurationPanel.add(comboBoxColotSensorS1Port);
        
        configurationPanel.add(new JLabel("Sensor de cor - s2", JLabel.RIGHT));
        configurationPanel.add(comboBoxColotSensorS2Port);
        
        configurationPanel.add(new JLabel("Sensor de cor - s3", JLabel.RIGHT));
        configurationPanel.add(comboBoxColotSensorS3Port);
        
        configurationPanel.add(new JLabel("Sensor de cor - out", JLabel.RIGHT));
        configurationPanel.add(comboBoxColotSensorOutPort);

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
    	File file = new File(PropertyFile.ARDUINO_MEGA_ROBOT_CONFIGURATION_FILE);
    	if (!file.exists()) {
    		return;
    	}

    	Properties properties;
		try {
			properties = Util.loadProperties(file);
		} catch (IOException e) {
			Console.SINGLETON.log("Não foi possível carregar a configuração do robô Arduino Mega.");
			return;
		}
		
		String propertyValue = properties.getProperty(ConfigurationPropertyName.DIRECTORY_ARDUINO_IDE.getName());
		if (propertyValue != null) {
			textFieldIDEArduino.setText(propertyValue);
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.MODEL_ARDUINO_BOARD.getName());
		if (propertyValue != null) {
			textFieldBoard.setText(propertyValue);
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.PORT_ARDUINO_BOARD.getName());
		if (propertyValue != null) {
			textFieldPort.setText(propertyValue);
		}
    	
		propertyValue = properties.getProperty(ConfigurationPropertyName.BUZZER_PORT.getName());
		if (propertyValue != null) {
			comboBoxBuzzerPort.setSelectedItem(AnalogicPort.getPortByValue(propertyValue));
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.DISTANCE_SENSOR_TRIGGER_PORT.getName());
		if (propertyValue != null) {
			comboBoxDistanceSensorTriggerPort.setSelectedItem(AnalogicPort.getPortByValue(propertyValue));
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.DISTANCE_SENSOR_ECHO_PORT.getName());
		if (propertyValue != null) {
			comboBoxDistanceSensorEchoPort.setSelectedItem(AnalogicPort.getPortByValue(propertyValue));
		}		
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.MULTIUSE_MOTOR_PORT.getName());
		if (propertyValue != null) {
			comboBoxMultiuseMotorPort.setSelectedItem(ServoMotorPort.getPortByValue(propertyValue));
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.LEFT_WHEEL_PORT.getName());
		if (propertyValue != null) {
			comboBoxLeftWheelPort.setSelectedItem(MotorPort.getPortByValue(propertyValue));
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.RIGHT_WHEEL_PORT.getName());
		if (propertyValue != null) {
			comboBoxRightWheelPort.setSelectedItem(MotorPort.getPortByValue(propertyValue));
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.COLOR_SENSOR_S0_PORT.getName());
		if (propertyValue != null) {
			comboBoxColotSensorS0Port.setSelectedItem(DigitalPort.getPortByValue(propertyValue));
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.COLOR_SENSOR_S1_PORT.getName());
		if (propertyValue != null) {
			comboBoxColotSensorS1Port.setSelectedItem(DigitalPort.getPortByValue(propertyValue));
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.COLOR_SENSOR_S2_PORT.getName());
		if (propertyValue != null) {
			comboBoxColotSensorS2Port.setSelectedItem(DigitalPort.getPortByValue(propertyValue));
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.COLOR_SENSOR_S3_PORT.getName());
		if (propertyValue != null) {
			comboBoxColotSensorS3Port.setSelectedItem(DigitalPort.getPortByValue(propertyValue));
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.COLOR_SENSOR_OUT_PORT.getName());
		if (propertyValue != null) {
			comboBoxColotSensorOutPort.setSelectedItem(DigitalPort.getPortByValue(propertyValue));
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.LCD_PIN1_PORT.getName());
		if (propertyValue != null) {
			comboBoxLcdPin1Port.setSelectedItem(DigitalPort.getPortByValue(propertyValue));
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.LCD_PIN2_PORT.getName());
		if (propertyValue != null) {
			comboBoxLcdPin2Port.setSelectedItem(PWMPort.getPortByValue(propertyValue));
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.LCD_PIN3_PORT.getName());
		if (propertyValue != null) {
			comboBoxLcdPin3Port.setSelectedItem(PWMPort.getPortByValue(propertyValue));
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.LCD_PIN4_PORT.getName());
		if (propertyValue != null) {
			comboBoxLcdPin4Port.setSelectedItem(DigitalPort.getPortByValue(propertyValue));
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.LCD_PIN5_PORT.getName());
		if (propertyValue != null) {
			comboBoxLcdPin5Port.setSelectedItem(PWMPort.getPortByValue(propertyValue));
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.LCD_PIN6_PORT.getName());
		if (propertyValue != null) {
			comboBoxLcdPin6Port.setSelectedItem(DigitalPort.getPortByValue(propertyValue));
		}
    }

    private void savePreferences() {
    	Properties properties = new Properties();
    	
    	String valueProperty = textFieldIDEArduino.getText();
		if (valueProperty != null) {
    		properties.put(ConfigurationPropertyName.DIRECTORY_ARDUINO_IDE.getName(), valueProperty);
    	}
		
		valueProperty = textFieldBoard.getText();
		if (valueProperty != null) {
    		properties.put(ConfigurationPropertyName.MODEL_ARDUINO_BOARD.getName(), valueProperty);
    	}
		
		valueProperty = textFieldPort.getText();
		if (valueProperty != null) {
    		properties.put(ConfigurationPropertyName.PORT_ARDUINO_BOARD.getName(), valueProperty);
    	}
    	
    	AnalogicPort sensorPortSelected = (AnalogicPort) comboBoxBuzzerPort.getSelectedItem();
		if (sensorPortSelected != AnalogicPort.NONE) {
    		properties.put(ConfigurationPropertyName.BUZZER_PORT.getName(), sensorPortSelected.getValue());
    	}
		
		sensorPortSelected = (AnalogicPort) comboBoxDistanceSensorTriggerPort.getSelectedItem();
		if (sensorPortSelected != AnalogicPort.NONE) {
			properties.put(ConfigurationPropertyName.DISTANCE_SENSOR_TRIGGER_PORT.getName(), sensorPortSelected.getValue());
		}
		
		sensorPortSelected = (AnalogicPort) comboBoxDistanceSensorEchoPort.getSelectedItem();
		if (sensorPortSelected != AnalogicPort.NONE) {
			properties.put(ConfigurationPropertyName.DISTANCE_SENSOR_ECHO_PORT.getName(), sensorPortSelected.getValue());
		}
		
		MotorPort motorPortSelected = (MotorPort) comboBoxLeftWheelPort.getSelectedItem();
		if (motorPortSelected != MotorPort.NONE) {
			properties.put(ConfigurationPropertyName.LEFT_WHEEL_PORT.getName(), motorPortSelected.getValue());
		}
		
		motorPortSelected = (MotorPort) comboBoxRightWheelPort.getSelectedItem();
		if (motorPortSelected != MotorPort.NONE) {
			properties.put(ConfigurationPropertyName.RIGHT_WHEEL_PORT.getName(), motorPortSelected.getValue());
		}
		
		ServoMotorPort servoMotorPortSelected = (ServoMotorPort) comboBoxMultiuseMotorPort.getSelectedItem();
		if (servoMotorPortSelected != ServoMotorPort.NONE) {
			properties.put(ConfigurationPropertyName.MULTIUSE_MOTOR_PORT.getName(), servoMotorPortSelected.getValue());
		}
		
		DigitalPort digitalPortSelected = (DigitalPort) comboBoxColotSensorS0Port.getSelectedItem();
		if (digitalPortSelected != DigitalPort.NONE) {
			properties.put(ConfigurationPropertyName.COLOR_SENSOR_S0_PORT.getName(), digitalPortSelected.getValue());
		}
		
		digitalPortSelected = (DigitalPort) comboBoxColotSensorS1Port.getSelectedItem();
		if (digitalPortSelected != DigitalPort.NONE) {
			properties.put(ConfigurationPropertyName.COLOR_SENSOR_S1_PORT.getName(), digitalPortSelected.getValue());
		}
		
		digitalPortSelected = (DigitalPort) comboBoxColotSensorS2Port.getSelectedItem();
		if (digitalPortSelected != DigitalPort.NONE) {
			properties.put(ConfigurationPropertyName.COLOR_SENSOR_S2_PORT.getName(), digitalPortSelected.getValue());
		}
		
		digitalPortSelected = (DigitalPort) comboBoxColotSensorS3Port.getSelectedItem();
		if (digitalPortSelected != DigitalPort.NONE) {
			properties.put(ConfigurationPropertyName.COLOR_SENSOR_S3_PORT.getName(), digitalPortSelected.getValue());
		}
		
		digitalPortSelected = (DigitalPort) comboBoxColotSensorOutPort.getSelectedItem();
		if (digitalPortSelected != DigitalPort.NONE) {
			properties.put(ConfigurationPropertyName.COLOR_SENSOR_OUT_PORT.getName(), digitalPortSelected.getValue());
		}
		
		digitalPortSelected = (DigitalPort) comboBoxLcdPin1Port.getSelectedItem();
		if (digitalPortSelected != DigitalPort.NONE) {
			properties.put(ConfigurationPropertyName.LCD_PIN1_PORT.getName(), digitalPortSelected.getValue());
		}
		
		PWMPort pwmPortSelected = (PWMPort) comboBoxLcdPin2Port.getSelectedItem();
		if (pwmPortSelected != PWMPort.NONE) {
			properties.put(ConfigurationPropertyName.LCD_PIN2_PORT.getName(), pwmPortSelected.getValue());
		}
		
		pwmPortSelected = (PWMPort) comboBoxLcdPin3Port.getSelectedItem();
		if (pwmPortSelected != PWMPort.NONE) {
			properties.put(ConfigurationPropertyName.LCD_PIN3_PORT.getName(), pwmPortSelected.getValue());
		}
		
		digitalPortSelected = (DigitalPort) comboBoxLcdPin4Port.getSelectedItem();
		if (digitalPortSelected != DigitalPort.NONE) {
			properties.put(ConfigurationPropertyName.LCD_PIN4_PORT.getName(), digitalPortSelected.getValue());
		}
		
		pwmPortSelected = (PWMPort) comboBoxLcdPin5Port.getSelectedItem();
		if (pwmPortSelected != PWMPort.NONE) {
			properties.put(ConfigurationPropertyName.LCD_PIN5_PORT.getName(), pwmPortSelected.getValue());
		}
		
		digitalPortSelected = (DigitalPort) comboBoxLcdPin6Port.getSelectedItem();
		if (digitalPortSelected != DigitalPort.NONE) {
			properties.put(ConfigurationPropertyName.LCD_PIN6_PORT.getName(), digitalPortSelected.getValue());
		}
		
		try {
			Util.saveProperties(properties, new File(PropertyFile.ARDUINO_MEGA_ROBOT_CONFIGURATION_FILE));
		} catch (IOException e) {
			Console.SINGLETON.log("Não foi possível salvar a configuração do robô Arduino Mega.");
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
        if ((comboBoxLeftWheelPort.getSelectedItem() == comboBoxRightWheelPort.getSelectedItem()) && comboBoxLeftWheelPort.getSelectedIndex() != 0) {
        	errors.add("Motores conectados na mesma porta: Esquerdo e Direito.");
        }
        
        
        if ((comboBoxDistanceSensorEchoPort.getSelectedItem() == comboBoxDistanceSensorTriggerPort.getSelectedItem()) && comboBoxDistanceSensorEchoPort.getSelectedIndex() != 0) {
        	errors.add("Pinos do Sensor de Distância conectados na mesma porta: ECHO e TRIGGER.");
        }
        
        
        if ((comboBoxDistanceSensorEchoPort.getSelectedItem() == comboBoxBuzzerPort.getSelectedItem()) && comboBoxDistanceSensorEchoPort.getSelectedIndex() != 0) {
        	errors.add("Sensores conectados na mesma porta: ECHO e BUZZER.");
        }
        
        if ((comboBoxDistanceSensorTriggerPort.getSelectedItem() == comboBoxBuzzerPort.getSelectedItem()) && comboBoxDistanceSensorTriggerPort.getSelectedIndex() != 0) {
        	errors.add("Sensores conectados na mesma porta: TRIGGER e BUZZER.");
        }
        
        
        if ((comboBoxColotSensorOutPort.getSelectedItem() == comboBoxColotSensorS0Port.getSelectedItem()) && comboBoxColotSensorOutPort.getSelectedIndex() != 0) {
        	errors.add("Pinos do Sensor de Cor conectados na mesma porta: OUT e S0.");
        }
        if ((comboBoxColotSensorOutPort.getSelectedItem() == comboBoxColotSensorS1Port.getSelectedItem()) && comboBoxColotSensorOutPort.getSelectedIndex() != 0) {
        	errors.add("Pinos do Sensor de Cor conectados na mesma porta: OUT e S1.");
        }	
        if ((comboBoxColotSensorOutPort.getSelectedItem() == comboBoxColotSensorS2Port.getSelectedItem()) && comboBoxColotSensorOutPort.getSelectedIndex() != 0) {
        	errors.add("Pinos do Sensor de Cor conectados na mesma porta: OUT e S2.");
        }
        if ((comboBoxColotSensorOutPort.getSelectedItem() == comboBoxColotSensorS3Port.getSelectedItem()) && comboBoxColotSensorOutPort.getSelectedIndex() != 0) {
        	errors.add("Pinos do Sensor de Cor conectados na mesma porta: OUT e S3.");
        }
        
        if ((comboBoxColotSensorOutPort.getSelectedItem() == comboBoxLcdPin1Port.getSelectedItem()) && comboBoxColotSensorOutPort.getSelectedIndex() != 0) {
        	errors.add("Pino do Sensor de Cor e LCD conectados na mesma porta: OUT e LCD Pino 1.");
        }        
        if ((comboBoxColotSensorOutPort.getSelectedItem() == comboBoxLcdPin4Port.getSelectedItem()) && comboBoxColotSensorOutPort.getSelectedIndex() != 0) {
        	errors.add("Pino do Sensor de Cor e LCD conectados na mesma porta: OUT e LCD Pino 4.");
        }
        if ((comboBoxColotSensorOutPort.getSelectedItem() == comboBoxLcdPin6Port.getSelectedItem()) && comboBoxColotSensorOutPort.getSelectedIndex() != 0) {
        	errors.add("Pino do Sensor de Cor e LCD conectados na mesma porta: OUT e LCD Pino 6.");
        }
        

        if ((comboBoxColotSensorS0Port.getSelectedItem() == comboBoxColotSensorS1Port.getSelectedItem()) && comboBoxColotSensorS0Port.getSelectedIndex() != 0) {
        	errors.add("Pinos do Sensor de Cor conectados na mesma porta: S0 e S1.");
        }	
        if ((comboBoxColotSensorS0Port.getSelectedItem() == comboBoxColotSensorS2Port.getSelectedItem()) && comboBoxColotSensorS0Port.getSelectedIndex() != 0) {
        	errors.add("Pinos do Sensor de Cor conectados na mesma porta: S0 e S2.");
        }
        if ((comboBoxColotSensorS0Port.getSelectedItem() == comboBoxColotSensorS3Port.getSelectedItem()) && comboBoxColotSensorS0Port.getSelectedIndex() != 0) {
        	errors.add("Pinos do Sensor de Cor conectados na mesma porta: S0 e S3.");
        }
        
        if ((comboBoxColotSensorS0Port.getSelectedItem() == comboBoxLcdPin1Port.getSelectedItem()) && comboBoxColotSensorS0Port.getSelectedIndex() != 0) {
        	errors.add("Pino do Sensor de Cor e LCD conectados na mesma porta: S0 e LCD Pino 1.");
        }        
        if ((comboBoxColotSensorS0Port.getSelectedItem() == comboBoxLcdPin4Port.getSelectedItem()) && comboBoxColotSensorS0Port.getSelectedIndex() != 0) {
        	errors.add("Pino do Sensor de Cor e LCD conectados na mesma porta: S0 e LCD Pino 4.");
        }
        if ((comboBoxColotSensorS0Port.getSelectedItem() == comboBoxLcdPin6Port.getSelectedItem()) && comboBoxColotSensorS0Port.getSelectedIndex() != 0) {
        	errors.add("Pino do Sensor de Cor e LCD conectados na mesma porta: S0 e LCD Pino 6.");
        }
        
        
        if ((comboBoxColotSensorS1Port.getSelectedItem() == comboBoxColotSensorS2Port.getSelectedItem()) && comboBoxColotSensorS1Port.getSelectedIndex() != 0) {
        	errors.add("Pinos do Sensor de Cor conectados na mesma porta: S1 e S2.");
        }
        if ((comboBoxColotSensorS1Port.getSelectedItem() == comboBoxColotSensorS3Port.getSelectedItem()) && comboBoxColotSensorS1Port.getSelectedIndex() != 0) {
        	errors.add("Pinos do Sensor de Cor conectados na mesma porta: S1 e S3.");
        }
        
        if ((comboBoxColotSensorS1Port.getSelectedItem() == comboBoxLcdPin1Port.getSelectedItem()) && comboBoxColotSensorS1Port.getSelectedIndex() != 0) {
        	errors.add("Pino do Sensor de Cor e LCD conectados na mesma porta: S1 e LCD Pino 1.");
        }        
        if ((comboBoxColotSensorS1Port.getSelectedItem() == comboBoxLcdPin4Port.getSelectedItem()) && comboBoxColotSensorS1Port.getSelectedIndex() != 0) {
        	errors.add("Pino do Sensor de Cor e LCD conectados na mesma porta: S1 e LCD Pino 4.");
        }
        if ((comboBoxColotSensorS1Port.getSelectedItem() == comboBoxLcdPin6Port.getSelectedItem()) && comboBoxColotSensorS1Port.getSelectedIndex() != 0) {
        	errors.add("Pino do Sensor de Cor e LCD conectados na mesma porta: S1 e LCD Pino 6.");
        }
        
        
        if ((comboBoxColotSensorS2Port.getSelectedItem() == comboBoxColotSensorS3Port.getSelectedItem()) && comboBoxColotSensorS2Port.getSelectedIndex() != 0) {
        	errors.add("Pinos do Sensor de Cor conectados na mesma porta: S2 e S3.");
        }
        
        if ((comboBoxColotSensorS2Port.getSelectedItem() == comboBoxLcdPin1Port.getSelectedItem()) && comboBoxColotSensorS2Port.getSelectedIndex() != 0) {
        	errors.add("Pino do Sensor de Cor e LCD conectados na mesma porta: S2 e LCD Pino 1.");
        }        
        if ((comboBoxColotSensorS2Port.getSelectedItem() == comboBoxLcdPin4Port.getSelectedItem()) && comboBoxColotSensorS2Port.getSelectedIndex() != 0) {
        	errors.add("Pino do Sensor de Cor e LCD conectados na mesma porta: S2 e LCD Pino 4.");
        }
        if ((comboBoxColotSensorS2Port.getSelectedItem() == comboBoxLcdPin6Port.getSelectedItem()) && comboBoxColotSensorS2Port.getSelectedIndex() != 0) {
        	errors.add("Pino do Sensor de Cor e LCD conectados na mesma porta: S2 e LCD Pino 6.");
        }
        
        if ((comboBoxColotSensorS3Port.getSelectedItem() == comboBoxLcdPin1Port.getSelectedItem()) && comboBoxColotSensorS3Port.getSelectedIndex() != 0) {
        	errors.add("Pino do Sensor de Cor e LCD conectados na mesma porta: S3 e LCD Pino 1.");
        }        
        if ((comboBoxColotSensorS3Port.getSelectedItem() == comboBoxLcdPin4Port.getSelectedItem()) && comboBoxColotSensorS3Port.getSelectedIndex() != 0) {
        	errors.add("Pino do Sensor de Cor e LCD conectados na mesma porta: S3 e LCD Pino 4.");
        }
        if ((comboBoxColotSensorS3Port.getSelectedItem() == comboBoxLcdPin6Port.getSelectedItem()) && comboBoxColotSensorS3Port.getSelectedIndex() != 0) {
        	errors.add("Pino do Sensor de Cor e LCD conectados na mesma porta: S3 e LCD Pino 6.");
        }
        
        if ((comboBoxLcdPin1Port.getSelectedItem() == comboBoxLcdPin4Port.getSelectedItem()) && comboBoxLcdPin1Port.getSelectedIndex() != 0) {
        	errors.add("Pinos do LCD conectados na mesma porta: LCD Pino 1 e LCD Pino 4.");
        }        
        if ((comboBoxLcdPin1Port.getSelectedItem() == comboBoxLcdPin6Port.getSelectedItem()) && comboBoxLcdPin1Port.getSelectedIndex() != 0) {
        	errors.add("Pinos do LCD conectados na mesma porta: LCD Pino 1 e LCD Pino 6.");
        }
        if ((comboBoxLcdPin4Port.getSelectedItem() == comboBoxLcdPin6Port.getSelectedItem()) && comboBoxLcdPin4Port.getSelectedIndex() != 0) {
        	errors.add("Pinos do LCD conectados na mesma porta: LCD Pino 4 e LCD Pino 6.");
        }
        
        if ((comboBoxLcdPin2Port.getSelectedItem() == comboBoxLcdPin3Port.getSelectedItem()) && comboBoxLcdPin2Port.getSelectedIndex() != 0) {
        	errors.add("Pinos do LCD conectados na mesma porta: LCD Pino 2 e LCD Pino 3.");
        }        
        if ((comboBoxLcdPin2Port.getSelectedItem() == comboBoxLcdPin5Port.getSelectedItem()) && comboBoxLcdPin2Port.getSelectedIndex() != 0) {
        	errors.add("Pinos do LCD conectados na mesma porta: LCD Pino 2 e LCD Pino 5.");
        }
        if ((comboBoxLcdPin3Port.getSelectedItem() == comboBoxLcdPin5Port.getSelectedItem()) && comboBoxLcdPin3Port.getSelectedIndex() != 0) {
        	errors.add("Pinos do LCD conectados na mesma porta: LCD Pino 3 e LCD Pino 5.");
        }         
    }

    private enum AnalogicPort {

        NONE("", ""),

        PORT_A0("Porta A0", "A0"),
        
        PORT_A1("Porta A1", "A1"),

        PORT_A2("Porta A2", "A2"),

        PORT_A3("Porta A3", "A3"),

        PORT_A4("Porta A4", "A4"),
    	
    	PORT_A5("Porta A5", "A5"),
        
        PORT_A6("Porta A6", "A6"),

        PORT_A7("Porta A7", "A7"),

        PORT_A8("Porta A8", "A8"),

        PORT_A9("Porta A9", "A9"),
        
        PORT_A10("Porta A10", "A10"),

        PORT_A11("Porta A11", "A11"),
    	
    	PORT_A12("Porta A12", "A12"),
        
        PORT_A13("Porta A13", "A13"),

        PORT_A14("Porta A14", "A14"),

        PORT_A15("Porta A15", "A15");

        private final String name;
        private final String value;

        private AnalogicPort(String name, String value) {
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
        
        public static AnalogicPort getPortByValue(String value) {
			AnalogicPort[] values = AnalogicPort.values();
			for (AnalogicPort sensorPort : values) {
				if (sensorPort.getValue().equals(value)) {
					return sensorPort;
				}
			}
			return null;
		}

    }

    private enum MotorPort {

        NONE("", ""),

        PORT_1("Porta M1", "1"),

        PORT_2("Porta M2", "2"),

        PORT_3("Porta M3", "3"),
    	
    	PORT_4("Porta M4", "4");

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
    
    private enum ServoMotorPort {

        NONE("", ""),

        PORT_9("Porta 9", "9"),

        PORT_10("Porta 10", "10");

        private final String name;
        private final String value;

        private ServoMotorPort(String name, String value) {
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
        
        public static ServoMotorPort getPortByValue(String value) {
        	ServoMotorPort[] values = ServoMotorPort.values();
			for (ServoMotorPort servoMotorPort : values) {
				if (servoMotorPort.getValue().equals(value)) {
					return servoMotorPort;
				}
			}
			return null;
		}
    }
    
    private enum DigitalPort {

        NONE("", ""),

        PORT_22("Porta 22", "22"),
        
        PORT_23("Porta 23", "23"),

        PORT_24("Porta 24", "24"),

        PORT_25("Porta 25", "25"),

        PORT_26("Porta 26", "26"),
    	
    	PORT_27("Porta 27", "27"),
        
        PORT_28("Porta 28", "28"),

        PORT_29("Porta 29", "29"),

        PORT_30("Porta 30", "30"),

        PORT_31("Porta 31", "31"),
        
        PORT_32("Porta 32", "32"),

        PORT_33("Porta 33", "33"),
    	
    	PORT_34("Porta 34", "34"),
        
        PORT_35("Porta 35", "35"),

        PORT_36("Porta 36", "36"),

        PORT_37("Porta 37", "37"),
        
        PORT_38("Porta 38", "38"),
        
        PORT_39("Porta 39", "39"),
        
        PORT_40("Porta 40", "40"),
        
        PORT_41("Porta 41", "41"),
        
        PORT_42("Porta 42", "42"),
        
        PORT_43("Porta 43", "43"),
        
        PORT_47("Porta 47", "47"),
        
        PORT_48("Porta 48", "48"),
        
        PORT_49("Porta 49", "49"),
        
        PORT_50("Porta 50", "50"),
        
        PORT_51("Porta 51", "51"),
        
        PORT_52("Porta 52", "52"),
        
        PORT_53("Porta 53", "53");
        

        private final String name;
        private final String value;

        private DigitalPort(String name, String value) {
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
        
        public static DigitalPort getPortByValue(String value) {
        	DigitalPort[] values = DigitalPort.values();
			for (DigitalPort sensorPort : values) {
				if (sensorPort.getValue().equals(value)) {
					return sensorPort;
				}
			}
			return null;
		}

    }
    
    private enum PWMPort {

        NONE("", ""),

        PORT_44("Porta 44 (PWM)", "44"),

        PORT_45("Porta 45 (PWM)", "45"),
        
        PORT_46("Porta 46 (PWM)", "46");

        private final String name;
        private final String value;

        private PWMPort(String name, String value) {
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
        
        public static PWMPort getPortByValue(String value) {
        	PWMPort[] values = PWMPort.values();
			for (PWMPort sensorPort : values) {
				if (sensorPort.getValue().equals(value)) {
					return sensorPort;
				}
			}
			return null;
		}
    }

}
