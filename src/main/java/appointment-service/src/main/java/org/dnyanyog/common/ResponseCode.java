package org.dnyanyog.common;

public enum ResponseCode {
  APPOINTMENT_ADDED("Success", "Appointment added successfully!"),
  APPOINTMENT_FAILED("Fail", "Failed to add Appointment!"),
  APPOINTMENT_NOT_UPDATED("Fail", "Appointment not found or invalid request!"),
  APPOINTMENT_UPDATED("Success", "Appointment updated successfully!"),
  SEARCH_APPOINTMENT("Success", "Appointment found successfully!"),
  SEARCH_APPOINTMENT_FAILED("Fail", "Appointment not found or invalid request!"),
  NOT_DELETE_APPOINTMENT("Fail", "Appointment not deleted !"),
  DELETE_APPOINTMENT("Success", "Appointment deleted successfully !");

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
