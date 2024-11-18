package org.dnyanyog.dto;

import org.springframework.stereotype.Component;

@Component
public class LoginRequest {
  private String mobileNumber;
  private String password;

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
