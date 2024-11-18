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
public class Appointment {

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

  @Column(name = "appointment_id", nullable = false, insertable = true, updatable = false)
  private String appointmentId;

  @Column private String appointment_time;

  @Column private String examination_date;

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

  public String getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(String appointmentId) {
    this.appointmentId = appointmentId;
  }

  public String getAppointment_time() {
    return appointment_time;
  }

  public void setAppointment_time(String appointment_time) {
    this.appointment_time = appointment_time;
  }

  public String getExamination_date() {
    return examination_date;
  }

  public void setExamination_date(String examination_date) {
    this.examination_date = examination_date;
  }

  private String generateRandomAlphanumeric(int length) {
    return UUID.randomUUID().toString().replace("-", "").substring(0, length).toUpperCase();
  }

  public void generateAppointmentId() {
    this.appointmentId = "APT" + generateRandomAlphanumeric(8);
  }

  public void generatePatientId() {
    this.patientId = "PAT" + generateRandomAlphanumeric(8);
  }
}
