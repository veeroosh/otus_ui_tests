package org.example.factory.settings;

import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.HashMap;

public class FirefoxSettings implements ISettings<FirefoxOptions> {
  public FirefoxOptions getSettings() {
    FirefoxOptions options = new FirefoxOptions();

    options.setCapability("browserVersion", "120.0");
    options.setCapability("selenoid:options", new HashMap<String, Object>() {
      {
        put("sessionTimeout", "15m");
        put("labels", new HashMap<String, Object>() {
          {
            put("manual", "true");
          }
        });
      }
    });

    return options;
  }
}
