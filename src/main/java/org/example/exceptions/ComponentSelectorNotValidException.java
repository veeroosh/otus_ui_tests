package org.example.exceptions;

public class ComponentSelectorNotValidException extends RuntimeException {

  public ComponentSelectorNotValidException() {
    super("Component selector ot valid");
  }
}
