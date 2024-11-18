package org.dnyanyog.service;

import jakarta.validation.Valid;
import org.dnyanyog.dto.CaseRequest;
import org.dnyanyog.dto.CaseResponse;

public interface CaseService {
  public CaseResponse addCase(@Valid CaseRequest request) throws Exception;

  public CaseResponse updateCase(String case_id, CaseRequest request);

  public CaseResponse getSingleCase(String patient_id);

  public CaseResponse deleteCase(String case_id);

  public CaseResponse getCase(String case_id);
}
