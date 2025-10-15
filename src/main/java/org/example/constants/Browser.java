package org.example.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Browser {
  CHROME("chrome"),
  FIREFOX("firefox");

  private final String browserName;
}
