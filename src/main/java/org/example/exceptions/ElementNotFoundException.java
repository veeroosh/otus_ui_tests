package org.example.exceptions;

public class ElementNotFoundException extends RuntimeException {
  public ElementNotFoundException() {
    super("Element not found");
  }
}
