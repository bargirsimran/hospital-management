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
public class Patients {

  @GeneratedValue
  @Id
  @Column(nullable = false, insertable = true, updatable = false)
  private long patient_code;

  @Column(name = "patient_id", nullable = false, insertable = true, updatable = false)
  private String patientId;

  @Column(
      name = "patient_name_english",
      nullable = false,
      insertable = true,
      updatable = false,
      length = 50)
  private String patientNameEnglish;

  @Column(nullable = false, insertable = true, updatable = false, length = 50)
  private String patient_name_marathi;

  @Column private String mobile;

  @Column private String gender;

  @Column private String birth_date;

  @Column private String first_examination_date;

  @Column private String address;

  public enum Status {
    ACTIVE,
    EXPIRED,
    DELETED
  }

  @Enumerated(EnumType.STRING)
  private Status status;

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public long getPatient_code() {
    return patient_code;
  }

  public void setPatient_code(long patient_code) {
    this.patient_code = patient_code;
  }

  public String getPatientId() {
    return patientId;
  }

  public void setPatientId(String patientId) {
    this.patientId = patientId;
  }

  public String getPatientNameEnglish() {
    return patientNameEnglish;
  }

  public void setPatientNameEnglish(String patientNameEnglish) {
    this.patientNameEnglish = patientNameEnglish;
  }

  public String getPatient_name_marathi() {
    return patient_name_marathi;
  }

  public void setPatient_name_marathi(String patient_name_marathi) {
    this.patient_name_marathi = patient_name_marathi;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getBirth_date() {
    return birth_date;
  }

  public void setBirth_date(String birth_date) {
    this.birth_date = birth_date;
  }

  public String getFirst_examination_date() {
    return first_examination_date;
  }

  public void setFirst_examination_date(String first_examination_date) {
    this.first_examination_date = first_examination_date;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  private String generateRandomAlphanumeric(int length) {
    return UUID.randomUUID().toString().replace("-", "").substring(0, length).toUpperCase();
  }

  public void generatePatientId() {
    this.patientId = "PAT" + generateRandomAlphanumeric(8);
  }
}
