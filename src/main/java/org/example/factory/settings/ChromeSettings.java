package org.example.factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;

public class ChromeSettings implements ISettings<ChromeOptions> {

  public ChromeOptions getSettings() {
    ChromeOptions chromeOptions = new ChromeOptions();

    chromeOptions.setCapability("selenoid:options", new HashMap<String, Object>() {
      {
        put("sessionTimeout", "15m");
        put("labels", new HashMap<String, Object>() {
          {
            put("manual", "true");
          }
        });
      }
    });

    return chromeOptions;
  }
}
