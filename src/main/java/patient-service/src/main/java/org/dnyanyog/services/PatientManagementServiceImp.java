package org.dnyanyog.services;

import jakarta.validation.Valid;
import java.util.List;
import org.dnyanyog.common.ResponseCode;
import org.dnyanyog.dto.PatientRequest;
import org.dnyanyog.dto.PatientResponse;
import org.dnyanyog.entity.Patients;
import org.dnyanyog.repo.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientManagementServiceImp {

  @Autowired private PatientsRepository patientRepo;
  @Autowired private PatientResponse patientResponse;

  public PatientResponse addPatient(@Valid PatientRequest request) throws Exception {
    try {
      List<Patients> optionalPatient =
          patientRepo.findByPatientNameEnglish(request.getPatientNameEnglish());

      if (!optionalPatient.isEmpty()) {
        patientResponse.setStatus(ResponseCode.PATIENT_FAILED.getStatus());
        patientResponse.setMessage(ResponseCode.PATIENT_FAILED.getMessage());
        return patientResponse;
      }

      Patients newPatient = new Patients();
      newPatient.setAddress(request.getAddress());
      newPatient.setBirth_date(request.getBirth_date());
      newPatient.setFirst_examination_date(request.getFirst_examination_date());
      newPatient.setGender(request.getGender());
      newPatient.setMobile(request.getMobile());
      newPatient.setPatientNameEnglish(request.getPatientNameEnglish());
      newPatient.setPatient_name_marathi(request.getPatient_name_marathi());
      newPatient.generatePatientId();
      newPatient.setStatus(Patients.Status.ACTIVE);

      newPatient = patientRepo.save(newPatient);
      populatePatientResponse(patientResponse, newPatient);
      patientResponse.setStatus(ResponseCode.PATIENT_ADDED.getStatus());
      patientResponse.setMessage(ResponseCode.PATIENT_ADDED.getMessage());
    } catch (Exception e) {
      patientResponse.setStatus(ResponseCode.PATIENT_FAILED.getStatus());
      patientResponse.setMessage(ResponseCode.PATIENT_FAILED.getMessage());
    }

    return patientResponse;
  }

  public PatientResponse updatePatient(String patient_id, PatientRequest request) {
    List<Patients> optionalPatient = patientRepo.findByPatientId(patient_id);

    if (optionalPatient.isEmpty()) {
      patientResponse.setStatus(ResponseCode.PATIENT_NOT_UPDATED.getStatus());
      patientResponse.setMessage(ResponseCode.PATIENT_NOT_UPDATED.getMessage());
    } else {
      Patients patients = optionalPatient.get(0);

      patients.setAddress(request.getAddress());
      patients.setMobile(request.getMobile());
      patients.setBirth_date(request.getBirth_date());
      patients.setFirst_examination_date(request.getFirst_examination_date());
      patients.setGender(request.getGender());
      patients.setPatientNameEnglish(request.getPatientNameEnglish());
      patients.setPatient_name_marathi(request.getPatient_name_marathi());

      patientRepo.save(patients);

      populatePatientResponse(patientResponse, patients);
      patientResponse.setMessage(ResponseCode.PATIENT_UPDATED.getMessage());
      patientResponse.setStatus(ResponseCode.PATIENT_UPDATED.getStatus());
    }

    return patientResponse;
  }

  public PatientResponse getSinglePatient(String patient_id) {
    List<Patients> optionalPatient = patientRepo.findByPatientId(patient_id);

    PatientResponse patientResponse = PatientResponse.getInstance();
    if (optionalPatient.isEmpty()) {
      patientResponse.setMessage(ResponseCode.SEARCH_PATIENT_FAILED.getMessage());
      patientResponse.setStatus(ResponseCode.SEARCH_PATIENT_FAILED.getStatus());
    } else {
      Patients patients = optionalPatient.get(0);
      populatePatientResponse(patientResponse, patients);
      patientResponse.setMessage(ResponseCode.SEARCH_PATIENT.getMessage());
      patientResponse.setStatus(ResponseCode.SEARCH_PATIENT.getStatus());
    }
    return patientResponse;
  }

  public PatientResponse getPatientName(String patient_name_english) {
    List<Patients> optionalPatient = patientRepo.findByPatientNameEnglish(patient_name_english);

    PatientResponse patientResponse = PatientResponse.getInstance();
    if (optionalPatient.isEmpty()) {
      patientResponse.setMessage(ResponseCode.SEARCH_PATIENT_FAILED.getMessage());
      patientResponse.setStatus(ResponseCode.SEARCH_PATIENT_FAILED.getStatus());
    } else {
      Patients patients = optionalPatient.get(0);
      populatePatientResponse(patientResponse, patients);
      patientResponse.setMessage(ResponseCode.SEARCH_PATIENT.getMessage());
      patientResponse.setStatus(ResponseCode.SEARCH_PATIENT.getStatus());
    }
    return patientResponse;
  }

  public PatientResponse deletePatient(String patient_id) {

    List<Patients> optionalPatient = patientRepo.findByPatientId(patient_id);

    if (optionalPatient.isEmpty()) {
      patientResponse.setMessage(ResponseCode.NOT_DELETE_PATIENT.getMessage());
      patientResponse.setStatus(ResponseCode.NOT_DELETE_PATIENT.getStatus());
    } else {

      Patients patients = optionalPatient.get(0);
      patients.setStatus(Patients.Status.DELETED);
      patientRepo.delete(patients);

      patientResponse.setMessage(ResponseCode.DELETE_PATIENT.getMessage());
      patientResponse.setStatus(ResponseCode.DELETE_PATIENT.getStatus());
      patientResponse.setAddress(patients.getAddress());
      patientResponse.setBirth_date(patients.getBirth_date());
      patientResponse.setGender(patients.getGender());
      patientResponse.setFirst_examination_date(patients.getFirst_examination_date());
      patientResponse.setMobile(patients.getMobile());
      patientResponse.setPatientNameEnglish(patients.getPatientNameEnglish());
      patientResponse.setPatient_name_marathi(patients.getPatient_name_marathi());
    }
    return patientResponse;
  }

  private void populatePatientResponse(PatientResponse response, Patients patient) {
    response.setAddress(patient.getAddress());
    response.setBirth_date(patient.getBirth_date());
    response.setFirst_examination_date(patient.getFirst_examination_date());
    response.setGender(patient.getGender());
    response.setMobile(patient.getMobile());
    response.setPatientNameEnglish(patient.getPatientNameEnglish());
    response.setPatient_name_marathi(patient.getPatient_name_marathi());
  }
}
