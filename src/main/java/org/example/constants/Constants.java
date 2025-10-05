package org.example.constants;

public interface Constants {
  static String getCourseLocatorByName(String courseName) {
    return String.format("//a/h6[normalize-space(.)='%s']/../div[2]", courseName);
  }
}
