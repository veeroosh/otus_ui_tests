package org.example.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.constants.Browser;
import org.example.factory.settings.ChromeSettings;
import org.example.factory.settings.FirefoxSettings;
import org.example.helpers.ActionsListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class WebDriverFactory {

  public WebDriver getDriver(Browser browser) {
    return switch (browser) {
      case CHROME -> {
        WebDriverManager.chromiumdriver().setup();
        yield new EventFiringDecorator<>(new ActionsListener())
            .decorate(new ChromeDriver(new ChromeSettings().getSettings()));
      }
      case FIREFOX -> {
        WebDriverManager.firefoxdriver().setup();
        yield new EventFiringDecorator<>(new ActionsListener())
            .decorate(new FirefoxDriver(new FirefoxSettings().getSettings()));
      }
    };
  }
}
