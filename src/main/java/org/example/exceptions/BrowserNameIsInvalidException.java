package org.example.exceptions;

public class BrowserNameIsInvalidException extends RuntimeException {

  public BrowserNameIsInvalidException(String name) {
    super(String.format("Browser name %s is invalid", name));
  }
}
