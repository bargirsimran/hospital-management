package org.dnyanyog.service;

import jakarta.validation.Valid;
import java.util.List;
import org.dnyanyog.common.ResponseCode;
import org.dnyanyog.dto.CaseRequest;
import org.dnyanyog.dto.CaseResponse;
import org.dnyanyog.entity.Cases;
import org.dnyanyog.repo.CaseServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaseServiceImp {

  @Autowired private CaseServiceRepository caseRepo;
  @Autowired private CaseResponse caseResponse;

  public CaseResponse addCase(@Valid CaseRequest request) throws Exception {
    try {
      List<Cases> optionalCase = caseRepo.findBypatientNameEnglish(request.getPatientNameEnglish());

      if (!optionalCase.isEmpty()) {
        caseResponse.setStatus(ResponseCode.CASE_FAILED.getStatus());
        caseResponse.setMessage(ResponseCode.CASE_FAILED.getMessage());
        return caseResponse;
      }
      Cases newCases = new Cases();
      newCases.setCase_number(request.getCase_number());
      newCases.setExamination_date(request.getExamination_date());
      newCases.setPatientNameEnglish(request.getPatientNameEnglish());
      newCases.setPrescription(request.getPrescription());
      newCases.setSymptoms(request.getSymptoms());
      newCases.setStatus(Cases.Status.ACTIVE);
      newCases.generateCaseId();
      newCases.generatePatientId();

      newCases = caseRepo.save(newCases);
      populatedCaseResponse(caseResponse, newCases);
      caseResponse.setStatus(ResponseCode.CASE_ADDED.getStatus());
      caseResponse.setMessage(ResponseCode.CASE_ADDED.getMessage());
    } catch (Exception e) {
      caseResponse.setStatus(ResponseCode.CASE_FAILED.getStatus());
      caseResponse.setMessage(ResponseCode.CASE_FAILED.getMessage());
    }
    return caseResponse;
  }

  public CaseResponse updateCase(String case_id, CaseRequest request) {
    List<Cases> optionalCases = caseRepo.findByCaseId(case_id);

    if (optionalCases.isEmpty()) {
      caseResponse.setStatus(ResponseCode.CASE_NOT_UPDATED.getStatus());
      caseResponse.setMessage(ResponseCode.CASE_NOT_UPDATED.getMessage());
    } else {
      Cases cases = optionalCases.get(0);

      cases.setCase_number(request.getCase_number());
      cases.setExamination_date(request.getExamination_date());
      cases.setPatientId(request.getPatientId());
      cases.setPatientNameEnglish(request.getPatientNameEnglish());
      cases.setPrescription(request.getPrescription());
      cases.setSymptoms(request.getSymptoms());

      caseRepo.save(cases);

      populatedCaseResponse(caseResponse, cases);
      caseResponse.setMessage(ResponseCode.CASE_UPDATED.getMessage());
      caseResponse.setStatus(ResponseCode.CASE_UPDATED.getStatus());
    }

    return caseResponse;
  }

  public CaseResponse getSingleCase(String patient_id) {
    List<Cases> optionalCase = caseRepo.findByPatientId(patient_id);

    if (optionalCase.isEmpty()) {
      caseResponse.setMessage(ResponseCode.SEARCH_CASE_FAILED.getMessage());
      caseResponse.setStatus(ResponseCode.SEARCH_CASE_FAILED.getStatus());
    } else {
      Cases cases = optionalCase.get(0);
      populatedCaseResponse(caseResponse, cases);
      caseResponse.setMessage(ResponseCode.SEARCH_CASE.getMessage());
      caseResponse.setStatus(ResponseCode.SEARCH_CASE.getStatus());
    }
    return caseResponse;
  }

  public CaseResponse getCase(String case_id) {
    List<Cases> optionalCase = caseRepo.findByCaseId(case_id);

    if (optionalCase.isEmpty()) {
      caseResponse.setMessage(ResponseCode.SEARCH_CASE_FAILED.getMessage());
      caseResponse.setStatus(ResponseCode.SEARCH_CASE_FAILED.getStatus());
    } else {
      Cases cases = optionalCase.get(0);
      populatedCaseResponse(caseResponse, cases);
      caseResponse.setMessage(ResponseCode.SEARCH_CASE.getMessage());
      caseResponse.setStatus(ResponseCode.SEARCH_CASE.getStatus());
    }
    return caseResponse;
  }

  public CaseResponse deleteCase(String case_id) {
    List<Cases> optionalCase = caseRepo.findByCaseId(case_id);

    if (optionalCase.isEmpty()) {
      caseResponse.setMessage(ResponseCode.NOT_DELETE_CASE.getMessage());
      caseResponse.setStatus(ResponseCode.NOT_DELETE_CASE.getStatus());
    } else {
      Cases cases = optionalCase.get(0);
      cases.setStatus(Cases.Status.DELETED);
      caseRepo.save(cases);

      caseResponse.setMessage(ResponseCode.DELETE_CASE.getMessage());
      caseResponse.setStatus(ResponseCode.DELETE_CASE.getStatus());
      populatedCaseResponse(caseResponse, cases);
    }
    return caseResponse;
  }

  private void populatedCaseResponse(CaseResponse response, Cases cases) {
    response.setCase_number(cases.getCase_number());
    response.setExamination_date(cases.getExamination_date());
    response.setPatientId(cases.getPatientId());
    response.setPatientNameEnglish(cases.getPatientNameEnglish());
    response.setPrescription(cases.getPrescription());
    response.setSymptoms(cases.getSymptoms());
  }
}
