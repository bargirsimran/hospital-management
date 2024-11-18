package org.dnyanyog.common;

public enum ResponseCode {
  PATIENT_ADDED("Success", "Patient added successfully!"),
  PATIENT_FAILED("Fail", "Failed to add patient!"),
  SAME_USERNAME_EMAIL("Fail", "Patient already exists with username or email!"),
  PATIENT_NOT_UPDATED("Fail", "Patient not found or invalid request!"),
  PATIENT_UPDATED("Success", "Patient updated successfully!"),
  SEARCH_PATIENT("Success", "Patient found successfully!"),
  SEARCH_PATIENT_FAILED("Fail", "Patient not found or invalid request!"),
  NOT_DELETE_PATIENT("Fail", "Patient not deleted !"),
  DELETE_PATIENT("Success", "Patient deleted successfully !");

  private final String status;
  private final String message;

  ResponseCode(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}
