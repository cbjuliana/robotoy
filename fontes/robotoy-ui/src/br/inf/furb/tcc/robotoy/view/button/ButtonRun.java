package br.inf.furb.tcc.robotoy.view.button;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Properties;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.SwingWorker;

import br.furb.tcc.robotoy.common.arduino.ConfigurationPropertyName;
import br.inf.furb.tcc.robotoy.common.PropertyFile;
import br.inf.furb.tcc.robotoy.common.util.Util;
import br.inf.furb.tcc.robotoy.integrator.arduino.ArduinoIntegrator;
import br.inf.furb.tcc.robotoy.integrator.lejos.LejosIntegrator;
import br.inf.furb.tcc.robotoy.view.Progress;
import br.inf.furb.tcc.robotoy.view.PropertyName;
import br.inf.furb.tcc.robotoy.view.RobotType;

public class ButtonRun extends ToolbarButton {

	private static final String ENVIRONMENT_VAR_NAME_NXJ_HOME = "NXJ_HOME";
	private static final String MESSAGE_LEJOS_NOT_INSTALLED = "Instale o leJOS para executar o programa: http://www.lejos.org/nxj-downloads.php";

	public ButtonRun() {
		super(new ImageIcon(ClassLoader.getSystemResource("img/run_32px.png")), "Executar (F5)", "F5");
	}

	@Override
	protected AbstractAction getButtonAction() {
		return new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent event) {

				final Progress p = new Progress();
				p.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				p.setVisible(true);
				SwingWorker worker = new SwingWorker() {
					private RobotType robotType;

					@Override
					protected Object doInBackground() throws Exception {
						try {
							console.clear();

							Properties properties = Util
									.loadProperties(new File(PropertyFile.ROBOT_CONFIGURATION_FILE));
							
							robotType = (RobotType) RobotType.getRobotByType(properties.getProperty(PropertyName.ROBOT_TYPE.getName()));
							
							if (robotType == RobotType.LEGO_MINDSTORMS) {
								deployInMindstormsPlatform(properties);
							} else if (robotType == RobotType.ARDUINO_MEGA) {
								deployInArduinoMegaPlatform(properties);
							}
							
						} catch (IOException e) {
							console.log("N�o foi poss�vel acessar o arquivo de propriedades do rob�.");
						} catch (Exception e) {
							System.out.println(e);
							e.printStackTrace();
							console.log(e.getMessage());
						} finally {
							editor.setFocus();
						}
						return null;
					}

					@Override
					protected void done() {
						p.setVisible(false);
					}
				};
				worker.execute();
			}

		};
	}

	private void deployInMindstormsPlatform(Properties robotProperties) throws Exception {
		if (System.getenv(ENVIRONMENT_VAR_NAME_NXJ_HOME) == null) {
			console.log(MESSAGE_LEJOS_NOT_INSTALLED);
			return;
		}

		File javaFile = Paths.get(PropertyFile.GEN_FOLDER, "Program.java").toFile();
		new LejosIntegrator().generateFile(editor.getSourceCode(),
				new File(robotProperties.getProperty(PropertyName.ROBOT_CONFIGURATION_FILE.getName())), javaFile);

		Process process = Runtime.getRuntime().exec(getNxjcCommand(javaFile));

		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String append;
		while ((append = reader.readLine()) != null) {
			System.out.println(append);
			console.log(append);
		}

		reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		while ((append = reader.readLine()) != null) {
			System.out.println(append);
			console.log(append);
		}

		process = Runtime.getRuntime().exec(getNxjCommand());

		reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		while ((append = reader.readLine()) != null) {
			System.out.println(append);
			console.log(append);
		}

		reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		while ((append = reader.readLine()) != null) {
			System.out.println(append);
			console.log(append);
		}
	}

	private void deployInArduinoMegaPlatform(Properties robotProperties) throws Exception {
		File arduinoFile = Paths.get(PropertyFile.GEN_FOLDER, "gen.ino").toFile();
		
		new ArduinoIntegrator().generateFile(editor.getSourceCode(),
				new File(robotProperties.getProperty(PropertyName.ROBOT_CONFIGURATION_FILE.getName())), arduinoFile);

		String ideArduino = Util.getPropertyValue(ConfigurationPropertyName.DIRECTORY_ARDUINO_IDE.getName(),
				new File(robotProperties.getProperty(PropertyName.ROBOT_CONFIGURATION_FILE.getName())));
		String modelBoardArduino = Util.getPropertyValue(ConfigurationPropertyName.MODEL_ARDUINO_BOARD.getName(),
				new File(robotProperties.getProperty(PropertyName.ROBOT_CONFIGURATION_FILE.getName())));
		String portArduino = Util.getPropertyValue(ConfigurationPropertyName.PORT_ARDUINO_BOARD.getName(),
				new File(robotProperties.getProperty(PropertyName.ROBOT_CONFIGURATION_FILE.getName())));

		String[] cmds = { "cd " + ideArduino, "arduino --board arduino:avr:" + modelBoardArduino + " --port "
				+ portArduino + " --upload " + arduinoFile.getPath() };

		try {
			ProcessBuilder builder = new ProcessBuilder("cmd", "/c", String.join(" &", cmds));
			builder.redirectErrorStream(true);

			Process process = builder.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String append;
			while ((append = reader.readLine()) != null) {
				System.out.println(append);
				console.log(append);
			}

			append = null;

			reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((append = reader.readLine()) != null) {
				System.out.println(append);
				console.log(append);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static String getNxjcCommand(File javaFile) {
		String command = "nxjc.bat -cp \"%s\" %s";
		// String command = "nxjc.bat -encoding %s -cp \"%s\" %s";
		// String encoding = "UTF8";
		String classpath = getClasspath("robotoy-generic-robot.jar", "robotoy-lejos-robot.jar");
		String javaFilePath = javaFile.toString();
		// return String.format(command, encoding, classpath, javaFilePath);
		String format = String.format(command, classpath, javaFilePath);
		System.out.println(format);
		return format;
	}

	public static String getNxjCommand() {
		String command = "nxj.bat -cp \"%s\" -r %s";
		String classpath = getClasspath("robotoy-generic-robot.jar", "robotoy-lejos-robot.jar");
		classpath = classpath.concat(";").concat(PropertyFile.GEN_FOLDER);
		String format = String.format(command, classpath, "Program");
		System.out.println(format);
		return format;

	}

	private static String getClasspath(String... classpaths) {
		StringBuilder classpathBuilder = new StringBuilder();
		for (String classpath : classpaths) {
			if (classpathBuilder.toString().length() > 0) {
				classpathBuilder.append(";");
			}
			classpathBuilder.append(Paths.get(PropertyFile.LIB_FOLDER, classpath).toString());
		}
		return classpathBuilder.toString();
	}

}
