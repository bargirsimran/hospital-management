package org.dnyanyog.services;

import jakarta.validation.Valid;
import org.dnyanyog.dto.PatientRequest;
import org.dnyanyog.dto.PatientResponse;

public interface PatientManagementService {
  public PatientResponse addPatient(@Valid PatientRequest request) throws Exception;

  public PatientResponse updatePatient(String patient_id, PatientRequest request);

  public PatientResponse getSinglePatient(String patient_id);

  public PatientResponse deletePatient(String patient_id);

  public PatientResponse getPatientName(String patient_name_english);
}
