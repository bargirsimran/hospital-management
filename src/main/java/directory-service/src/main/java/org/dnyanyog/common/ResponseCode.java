package org.dnyanyog.common;

public enum ResponseCode {
  USER_ADDED("Success", "User added successfully!"),
  USER_FAILED("Fail", "Failed to add User!"),
  SAME_USERNAME_EMAIL("Fail", "User already exists with username or email!"),
  USER_NOT_UPDATED("Fail", "User not found or invalid request!"),
  USER_UPDATED("Success", "User updated successfully!"),
  SEARCH_USER("Success", "User found successfully!"),
  SEARCH_USER_FAILED("Fail", "User not found or invalid request!"),
  NOT_DELETE_USER("Fail", "User not deleted !"),
  DELETE_USER("Success", "User deleted successfully !"),
  LOGIN_SUCCESS("Success", "Login successful"),
  LOGIN_FAIL("Fail", "Login failed");

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
