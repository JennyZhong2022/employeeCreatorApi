package nology.employee.common.exceptions;

import nology.employee.common.ValidationErrors;

public class ServiceValidationException extends Exception {
  private ValidationErrors errors;

  public ServiceValidationException(ValidationErrors errors2) {
    this.errors = errors2;
  }

  public ValidationErrors getErrors() {
    return errors;
  }

}
