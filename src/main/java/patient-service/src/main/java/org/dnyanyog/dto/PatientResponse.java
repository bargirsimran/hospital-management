package org.dnyanyog.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientResponse {

  private String status;
  private String message;
  private String patientNameEnglish;
  private String patient_name_marathi;
  private String mobile;
  private String gender;
  private String birth_date;
  private String first_examination_date;
  private String address;
  private long patient_id;
  @Autowired private PatientData patientData;

  public PatientData getPatientData() {
    return patientData;
  }

  public void setPatientData(PatientData patientData) {
    this.patientData = patientData;
  }

  public long getPatient_id() {
    return patient_id;
  }

  public void setPatient_id(long patient_id) {
    this.patient_id = patient_id;
  }

  public static PatientResponse getInstance() {
    return new PatientResponse();
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
