package utils;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesReader {

  private static Properties properties;

  private PropertiesReader() {
  }

  public static String getPropertyByName(String property) {
    if (properties == null) {
      readProps();
    }
    return properties.getProperty(property);
  }

  private static void readProps() {
    properties = new Properties();
    String propFileName = "test.properties";
    try {
      properties
          .load(PropertiesReader.class.getClassLoader().getResourceAsStream(propFileName));
    } catch (IOException e) {
      throw new RuntimeException("Reading props failed");
    }
  }
}

