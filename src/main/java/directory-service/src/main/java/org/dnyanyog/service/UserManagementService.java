package org.dnyanyog.service;

import jakarta.validation.Valid;
import org.dnyanyog.dto.UserRequest;
import org.dnyanyog.dto.UserResponse;

public interface UserManagementService {
  public UserResponse addUser(@Valid UserRequest request) throws Exception;

  public UserResponse updateUser(String user_id, UserRequest request);

  public UserResponse getSingleUser(String user_id);

  public UserResponse deleteUser(String user_id);
}
