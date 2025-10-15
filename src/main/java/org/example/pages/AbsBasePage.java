package org.example.pages;

import com.google.inject.Inject;
import org.example.annotations.Path;
import org.example.commons.AbsCommon;
import org.example.exceptions.PathPageNotFoundException;
import org.example.scoped.GuiceScoped;

public abstract class AbsBasePage<T> extends AbsCommon {

  protected String baseUrl = System.getProperty("base.url");

  @Inject
  public AbsBasePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  private String getPath() {
    Class clazz = this.getClass();
    if (clazz.isAnnotationPresent(Path.class)) {
      Path path = (Path) clazz.getDeclaredAnnotation(Path.class);
      return path.value();
    }
    throw new PathPageNotFoundException(clazz);
  }

  public T open() {
    driver.get(baseUrl + getPath());

    return (T) this;
  }
}
