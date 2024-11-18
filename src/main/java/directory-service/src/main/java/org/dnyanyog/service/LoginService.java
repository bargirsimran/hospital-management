package org.dnyanyog.service;

import org.dnyanyog.dto.LoginRequest;
import org.dnyanyog.dto.LoginResponse;

public interface LoginService {
  public LoginResponse validateUser(LoginRequest loginRequest);
}
