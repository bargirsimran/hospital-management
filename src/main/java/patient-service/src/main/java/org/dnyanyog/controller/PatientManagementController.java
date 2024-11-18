package org.dnyanyog.controller;

import org.dnyanyog.dto.PatientRequest;
import org.dnyanyog.dto.PatientResponse;
import org.dnyanyog.services.PatientManagementServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientManagementController {
  @Autowired PatientManagementServiceImp patientService;

  @PostMapping(
      path = "/api/v1/patient/add",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public PatientResponse addUser(@RequestBody PatientRequest patientRequest) throws Exception {
    return patientService.addPatient(patientRequest);
  }

  @PostMapping("/api/v1/patient/{patient_id}")
  public PatientResponse updatePatient(
      @PathVariable String patient_id, @RequestBody PatientRequest request) {
    return patientService.updatePatient(patient_id, request);
  }

  @GetMapping(path = "/api/v1/patient/{patient_id}")
  public PatientResponse getSinglePatient(@PathVariable String patient_id) {

    return patientService.getSinglePatient(patient_id);
  }

  @GetMapping(path = "/api/v1/patient/name/{patient_name_english}")
  public PatientResponse getPatientName(@PathVariable String patient_name_english) {

    return patientService.getPatientName(patient_name_english);
  }

  @DeleteMapping(path = "/api/v1/patient/{patient_id}")
  public PatientResponse deletePatient(@PathVariable String patient_id) {
    return patientService.deletePatient(patient_id);
  }
}
