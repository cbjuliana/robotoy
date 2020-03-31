package br.inf.furb.tcc.robotoy.view.button;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import br.inf.furb.tcc.robotoy.common.PropertyFile;
import br.inf.furb.tcc.robotoy.common.util.Util;
import br.inf.furb.tcc.robotoy.view.Console;
import br.inf.furb.tcc.robotoy.view.PropertyName;
import br.inf.furb.tcc.robotoy.view.RobotType;
import br.inf.furb.tcc.robotoy.view.arduino.ArduinoMegaRobotConfigurationDialog;
import br.inf.furb.tcc.robotoy.view.lejos.MindstormsRobotConfigurationDialog;

public class ButtonConfigure extends ToolbarButton {

    private MindstormsRobotConfigurationDialog mindstormsConfigurationDialog;
    
    private ArduinoMegaRobotConfigurationDialog arduinoMegaConfigurationDialog;
    
    private SelectRobotDialog selectRobotDialog;
    
    private RobotType robotType;
    
    Properties properties;
    
    File file;
    

    public ButtonConfigure() {
        super(new ImageIcon(ClassLoader.getSystemResource("img/configure_32px.png")), "Configurar sensores e motores (F11)", "F11");
        
        file = new File(PropertyFile.ROBOT_CONFIGURATION_FILE);
		if (!file.exists()) {
			JOptionPane.showMessageDialog(null, "Informe o tipo do robô.");
			selectRobotDialog = new SelectRobotDialog();
			selectRobotDialog.setVisible(true);
			editor.setFocus();
		}

		properties = extractPropertiesRobot();		
		
		robotType = (RobotType) RobotType.getRobotByType(properties.getProperty(PropertyName.ROBOT_TYPE.getName()));
		
		if (robotType == RobotType.LEGO_MINDSTORMS) {
			mindstormsConfigurationDialog = new MindstormsRobotConfigurationDialog();
		} else if (robotType == RobotType.ARDUINO_MEGA) {
			arduinoMegaConfigurationDialog = new ArduinoMegaRobotConfigurationDialog();
		}
        
        
    }

	private Properties extractPropertiesRobot() {
		try {
			properties = Util.loadProperties(file);
		} catch (IOException e) {
			Console.SINGLETON.log("Não foi possível carregar o tipo de robô.");
			return null;
		}
		return properties;
	}

    @Override
    protected AbstractAction getButtonAction() {
        return new AbstractAction() {

            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
            	properties = extractPropertiesRobot();
            	robotType = (RobotType) RobotType.getRobotByType(properties.getProperty(PropertyName.ROBOT_TYPE.getName()));
            	
            	if (robotType == RobotType.LEGO_MINDSTORMS) {
            		mindstormsConfigurationDialog = new MindstormsRobotConfigurationDialog();
            		mindstormsConfigurationDialog.setVisible(true);
        		} else if (robotType == RobotType.ARDUINO_MEGA) {
        			arduinoMegaConfigurationDialog = new ArduinoMegaRobotConfigurationDialog();
        			arduinoMegaConfigurationDialog.setVisible(true);
        		}
            	
                editor.setFocus();
            }
        };
    }

}
