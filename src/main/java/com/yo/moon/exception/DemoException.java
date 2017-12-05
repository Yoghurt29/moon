package com.yo.moon.exception;

public class DemoException extends Exception {
  private static final long serialVersionUID = -1152032784283748943L;

  public DemoException() {
    super();
    // TODO Auto-generated constructor stub
  }

  public DemoException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public DemoException(String message, Throwable cause) {
    super(message, cause);
  }

  public DemoException(String message) {
    super(message);
  }

  public DemoException(Throwable cause) {
    super(cause);
  }

}
