package org.example.factory.settings;

import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxSettings implements ISettings<FirefoxOptions> {
  public FirefoxOptions getSettings() {
    FirefoxOptions firefoxOptions = new FirefoxOptions();
    firefoxOptions.addArguments("--start-fullscreen");

    return firefoxOptions;
  }
}
