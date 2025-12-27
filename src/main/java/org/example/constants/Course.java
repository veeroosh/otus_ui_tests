package org.example.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Course {
  TEAM_LEAD("Team Lead"),
  JAVA_QA_PRO("Java QA Engineer. Professional"),
  MICROSERVICE_ARCHITECTURE("Microservice Architecture");

  private final String courseName;
}
