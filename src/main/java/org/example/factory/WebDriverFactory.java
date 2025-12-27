package org.example.factory;

import org.example.exceptions.BrowserNameIsInvalidException;
import org.example.factory.settings.ChromeSettings;
import org.example.factory.settings.FirefoxSettings;
import org.example.helpers.ActionsListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {

  private final String browserName = System.getProperty("browser.name", "chrome");
  private final String selenoidUrl = System.getProperty("selenoid.url", "http://188.130.251.59/wd/hub");

  public WebDriver getDriver() throws MalformedURLException {
    return switch (browserName) {
      case "chrome":
        yield new EventFiringDecorator<>(new ActionsListener()).decorate(
            new RemoteWebDriver(new URL(selenoidUrl), (new ChromeSettings().getSettings())));
      case "firefox":
        yield new EventFiringDecorator<>(new ActionsListener()).decorate(
            new RemoteWebDriver(new URL(selenoidUrl), (new FirefoxSettings().getSettings())));
      default:
        throw new BrowserNameIsInvalidException(browserName);
    };
  }
}
