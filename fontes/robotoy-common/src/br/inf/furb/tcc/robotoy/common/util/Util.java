package br.inf.furb.tcc.robotoy.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Util {

	private Util() {
		// Classe não instanciável.
	}

	public static Properties loadProperties(File propertiesFile) throws IOException {
		Properties properties = new Properties();
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(propertiesFile);
			properties.load(inputStream);
			return properties;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public static void saveProperties(Properties properties, File propertiesFile) throws IOException {
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(propertiesFile);
			properties.store(outputStream, null);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException exception) {
					throw new RuntimeException(exception);
				}
			}
		}
	}
	
	public static String getPropertyValue(String key, File propertiesFile) throws IOException {
		Properties properties = loadProperties(propertiesFile);
		return properties.getProperty(key);
	}

}
