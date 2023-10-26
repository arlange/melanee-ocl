package org.melanee.ocl2.service.exception;

public class PreConstraintFailedException extends Exception {
  private static final long serialVersionUID = 1L;

  String exceptionMessage;

  public PreConstraintFailedException(Throwable cause) {
    super(cause);
  }

  public PreConstraintFailedException(String message, Throwable cause) {
    super("PreConstraint Failed" + message, cause);
  }

  // Constructor that accepts a message
  public PreConstraintFailedException(String message) {
    super("PreConstraint Failed\n" + message);
  }


}
