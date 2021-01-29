package utils;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.EnvironmentConfiguration;
import org.apache.commons.configuration.FileConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;

public class Properties {

  //TODO finish properties, todos, add comments and logging

  private static final String PROPERTIES_FILE = "service.properties";
  private static final CompositeConfiguration CONFIG = new CompositeConfiguration();

  static {
    CONFIG.addConfiguration(new EnvironmentConfiguration());
    CONFIG.addConfiguration(new SystemConfiguration());
    try {
      FileConfiguration fileConfiguration = new PropertiesConfiguration(PROPERTIES_FILE);
      CONFIG.addConfiguration(fileConfiguration);
    } catch (ConfigurationException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method gets a configuration value by key
   * @param key
   * @return value if found, null if not found
   */
  public static String get(String key) {
    return CONFIG.getString(key);
  }

  public static String get(String key, String defaultValue) {
    return CONFIG.getString(key, defaultValue);
  }
}
