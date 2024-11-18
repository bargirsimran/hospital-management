package org.dnyanyog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class CaseData {

  @NotNull(message = "Username is mandatory")
  @NotBlank(message = "Username should not be blank")
  @Size(max = 50, message = "Username length should be at most 50 characters")
  private String patientNameEnglish;

  @NotNull(message = "Patient ID is mandatory")
  private String patientId;

  @NotBlank(message = "Case number is mandatory")
  @Size(max = 20, message = "Case number length should be at most 20 characters")
  private String case_number;

  @NotBlank(message = "Examination date is mandatory")
  @Pattern(
      regexp = "\\d{4}-\\d{2}-\\d{2}",
      message = "Examination date should be in the format YYYY-MM-DD")
  private String examination_date;

  @Size(max = 500, message = "Symptoms length should be at most 500 characters")
  private String symptoms;

  @Size(max = 500, message = "Prescription length should be at most 500 characters")
  private String prescription;

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
}
