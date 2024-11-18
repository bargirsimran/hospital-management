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
public class Cases {

  @GeneratedValue
  @Id
  @Column(nullable = false, insertable = true, updatable = false)
  private long patient_code;

  @Column(
      name = "patient_name_english",
      nullable = false,
      insertable = true,
      updatable = false,
      length = 50)
  private String patientNameEnglish;

  @Column(name = "patient_id", nullable = false, insertable = true, updatable = false)
  private String patientId;

  @Column(name = "case_id", nullable = false, insertable = true, updatable = false)
  private String caseId;

  @Column private String case_number;

  @Column private String examination_date;

  @Column private String symptoms;

  @Column private String prescription;

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

  public String getPatientNameEnglish() {
    return patientNameEnglish;
  }

  public void setPatientNameEnglish(String patientNameEnglish) {
    this.patientNameEnglish = patientNameEnglish;
  }

  public String getPatientId() {
    return patientId;
  }

  public void setPatientId(String patientId) {
    this.patientId = patientId;
  }

  public String getCase_number() {
    return case_number;
  }

  public void setCase_number(String case_number) {
    this.case_number = case_number;
  }

  public String getExamination_date() {
    return examination_date;
  }

  public void setExamination_date(String examination_date) {
    this.examination_date = examination_date;
  }

  public String getSymptoms() {
    return symptoms;
  }

  public void setSymptoms(String symptoms) {
    this.symptoms = symptoms;
  }

  public String getPrescription() {
    return prescription;
  }

  public void setPrescription(String prescription) {
    this.prescription = prescription;
  }

  private String generateRandomAlphanumeric(int length) {
    return UUID.randomUUID().toString().replace("-", "").substring(0, length).toUpperCase();
  }

  public void generateCaseId() {
    this.caseId = "CAS" + generateRandomAlphanumeric(8);
  }

  public void generatePatientId() {
    this.patientId = "PAT" + generateRandomAlphanumeric(8);
  }
}
