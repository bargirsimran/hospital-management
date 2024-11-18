package org.dnyanyog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class PatientData {

  @NotNull(message = "Username is mandatory")
  @NotBlank(message = "Username should not be blank")
  @Size(max = 50, message = "Username length should be at most 50 characters")
  private String patientNameEnglish;

  @NotNull(message = "Username in Marathi is mandatory")
  @NotBlank(message = "Username in Marathi should not be blank")
  @Size(max = 50, message = "Username in Marathi length should be at most 50 characters")
  @Pattern(
      regexp = "\"([अ-ज्ञ]\"",
      message = "Username in Marathi should only contain Marathi characters")
  private String patient_name_marathi;

  @Pattern(
      regexp = "^\\d{10}$",
      message = "Invalid mobile number format. It should be a 10-digit number.")
  private String mobile;

  @NotNull(message = "Gender is mandatory")
  @Pattern(
      regexp = "Male|Female|Other",
      message = "Gender should be either 'Male', 'Female' or 'Other'")
  private String gender;

  @NotNull(message = "Birth date is mandatory")
  @PastOrPresent(message = "Birth date cannot be in the future")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private String birth_date;

  @NotNull(message = "First examination date is mandatory")
  @PastOrPresent(message = "First examination date cannot be in the future")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private String first_examination_date;

  @NotNull(message = "Address is mandatory")
  @NotBlank(message = "Address should not be blank")
  @Size(max = 255, message = "Address length should be at most 255 characters")
  private String address;

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
}
