package org.dnyanyog.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Users {

  @GeneratedValue
  @Id
  @Column(name = "patient_code", nullable = false, insertable = true, updatable = false)
  private long patient_code;

  @Column(name = "user_id", nullable = false, insertable = true, updatable = false, length = 50)
  private String userId;

  @Column(name = "user_name", nullable = false, insertable = true, updatable = false, length = 50)
  private String userName;

  @Column private String email;

  @Column(name = "mobile_number")
  private String mobileNumber;

  @Column private String role;

  @Column private String password;

  @Column private String confirm_password;

  @Column private String encryptionKey;

  public enum Status {
    ACTIVE,
    EXPIRED,
    DELETED
  }

  @Enumerated(EnumType.STRING)
  private Status status;

  public String getEncryptionKey() {
    return encryptionKey;
  }

  public void setEncryptionKey(String encryptionKey) {
    this.encryptionKey = encryptionKey;
  }

  public long getPatient_code() {
    return patient_code;
  }

  public void setPatient_code(long patient_code) {
    this.patient_code = patient_code;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirm_password() {
    return confirm_password;
  }

  public void setConfirm_password(String confirm_password) {
    this.confirm_password = confirm_password;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Users generateUser() {
    this.userId = "USR" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    return this;
  }
}
