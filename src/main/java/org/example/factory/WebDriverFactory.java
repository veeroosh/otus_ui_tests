package org.example.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.exceptions.BrowserNameIsInvalidException;
import org.example.factory.settings.ChromeSettings;
import org.example.factory.settings.FirefoxSettings;
import org.example.helpers.ActionsListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class WebDriverFactory {

  private final String browserName = System.getProperty("browser.name", "chrome");

  public WebDriver getDriver() {
    return switch (browserName) {
      case "chrome": {
        WebDriverManager.chromiumdriver().setup();
        yield new EventFiringDecorator<>(new ActionsListener())
            .decorate(new ChromeDriver(new ChromeSettings().getSettings()));
      }
      case "firefox": {
        WebDriverManager.firefoxdriver().setup();
        yield new EventFiringDecorator<>(new ActionsListener())
            .decorate(new FirefoxDriver(new FirefoxSettings().getSettings()));
      }
      default:
        throw new BrowserNameIsInvalidException(browserName);
    };
  }
}
