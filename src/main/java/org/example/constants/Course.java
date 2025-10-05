package org.example.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Course {
  C_SHARP_DEVELOPER_PRO("C# Developer. Professional"),
  ARCHITECTURE("Архитектура и шаблоны проектирования"),
  DEV_OPS_ADVANCED("DevOps Advanced"),
  JAVA_QA_PRO("Java QA Engineer. Basic");

  private final String courseName;
}
