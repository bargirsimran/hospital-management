package org.dnyanyog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class AppointmentResponse {

  private String patientNameEnglish;

  private String patientId;

  private String appointmentId;

  private String examination_date;

  private String appointment_time;

  private String status;

  private String message;

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

  public String getExamination_date() {
    return examination_date;
  }

  public void setExamination_date(String examination_date) {
    this.examination_date = examination_date;
  }

  public String getAppointment_time() {
    return appointment_time;
  }

  public void setAppointment_time(String appointment_time) {
    this.appointment_time = appointment_time;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
