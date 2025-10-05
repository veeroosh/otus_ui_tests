package org.example.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.pages.CatalogPage;
import org.example.pages.CoursePage;
import org.example.pages.MainPage;
import org.openqa.selenium.WebDriver;

public class PageGuiceModule extends AbstractModule {

  private WebDriver driver;

  public PageGuiceModule(WebDriver driver) {
    this.driver = driver;
  }

  @Provides
  @Singleton
  public MainPage getMainPage() {
    return new MainPage(driver);
  }

  @Provides
  @Singleton
  public CatalogPage getCatalogPage() {
    return new CatalogPage(driver);
  }

  @Provides
  @Singleton
  public CoursePage getCoursePage() {
    return new CoursePage(driver);
  }
}
