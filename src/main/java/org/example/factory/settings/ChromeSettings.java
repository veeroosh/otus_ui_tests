package org.example.factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeSettings implements ISettings<ChromeOptions> {

  public ChromeOptions getSettings() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--start-fullscreen");

    return chromeOptions;
  }
}
