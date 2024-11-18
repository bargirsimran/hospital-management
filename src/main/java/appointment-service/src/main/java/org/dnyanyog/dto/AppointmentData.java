package org.dnyanyog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class AppointmentData {

  @NotNull(message = "Username is mandatory")
  @NotBlank(message = "Username should not be blank")
  @Size(max = 50, message = "Username length should be at most 50 characters")
  private String patientNameEnglish;

  @NotNull(message = "Patient ID is mandatory")
  private String patientId;

  private String appointmentId;

  @NotBlank(message = "Examination date is mandatory")
  @Pattern(
      regexp = "\\d{4}-\\d{2}-\\d{2}",
      message = "Examination date should be in the format YYYY-MM-DD")
  private String examination_date;

  @NotBlank(message = "Appointment time is mandatory")
  @Pattern(
      regexp = "^([01]\\d|2[0-3]):?([0-5]\\d)$",
      message = "Appointment time should be in the format HH:mm")
  private String appointment_time;

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
}
