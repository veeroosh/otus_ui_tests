package org.example.components;

import com.google.inject.Inject;
import org.example.annotations.Component;
import org.example.commons.AbsCommon;
import org.example.exceptions.ComponentSelectorNotValidException;
import org.example.scoped.GuiceScoped;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public abstract class AbsComponent extends AbsCommon {

  @Inject
  public AbsComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  private By getByComponent() {
    Class clazz = getClass();
    if (clazz.isAnnotationPresent(Component.class)) {
      Component component = (Component) clazz.getDeclaredAnnotation(Component.class);
      String value = component.value();

      return By.xpath(value);
    }
    throw new ComponentSelectorNotValidException();
  }

  protected WebElement getComponentEntity(By locator) {
    if (waiter.waitForElementPresence(locator))
      return getElement(locator);
    else throw new ComponentSelectorNotValidException();
  }

  protected List<WebElement> getAllComponentEntity() {
    if (waiter.waitForElementPresence(getByComponent()))
      return driver.findElements(getByComponent());
    else throw new ComponentSelectorNotValidException();
  }
}
