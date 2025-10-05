package org.example.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.components.CourseCard;
import org.openqa.selenium.WebDriver;

public class ComponentGuiceModule extends AbstractModule {

  private WebDriver driver;

  public ComponentGuiceModule(WebDriver driver) {
    this.driver = driver;
  }

  @Provides
  @Singleton
  public CourseCard getCourseCard() {
    return new CourseCard(driver);
  }
}
