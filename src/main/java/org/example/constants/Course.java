package org.example.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Course {
  DEV_OPS_PRACTICES_INSTRUMENTS("DevOps практики и инструменты"),
  DEV_OPS_ADVANCED("DevOps Advanced"),
  JAVA_QA_PRO("Java QA Engineer. Basic");

  private final String courseName;
}
