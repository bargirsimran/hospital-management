package org.dnyanyog.service;

import jakarta.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.dnyanyog.common.ResponseCode;
import org.dnyanyog.dto.UserRequest;
import org.dnyanyog.dto.UserResponse;
import org.dnyanyog.entity.Users;
import org.dnyanyog.exception.PasswordMismatchException;
import org.dnyanyog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImp implements UserManagementService {

  @Autowired private UserRepository userRepo;
  @Autowired private UserResponse userResponse;

  public UserResponse addUser(@Valid UserRequest request) throws Exception {
    try {

      if (!request.isPasswordMatching()) {
        throw new PasswordMismatchException("Password and Confirm Password do not match");
      }

      List<Users> optionalUser = userRepo.findByuserName(request.getUserName());

      if (!optionalUser.isEmpty()) {
        userResponse.setStatus(ResponseCode.USER_FAILED.getStatus());
        userResponse.setMessage(ResponseCode.USER_FAILED.getMessage());
        return userResponse;
      }

      Users newUser = new Users();
      String userKey = generateAESKey();
      newUser.setConfirm_password(encryptAES(request.getConfirm_password(), userKey));
      newUser.setEmail(request.getEmail());
      newUser.setMobileNumber(request.getMobileNumber());
      newUser.setPassword(encryptAES(request.getPassword(), userKey));
      newUser.setRole(request.getRole());
      newUser.setUserName(request.getUserName());
      newUser.setStatus(Users.Status.ACTIVE);
      newUser.setEncryptionKey(userKey);
      newUser.generateUser();

      newUser = userRepo.save(newUser);

      userResponse.setStatus(ResponseCode.USER_ADDED.getStatus());
      userResponse.setMessage(ResponseCode.USER_ADDED.getMessage());
      populateUserResponse(userResponse, newUser);

    } catch (Exception e) {
      userResponse.setStatus(ResponseCode.USER_FAILED.getStatus());
      userResponse.setMessage(ResponseCode.USER_FAILED.getMessage());
    }

    return userResponse;
  }

  public UserResponse updateUser(String user_id, UserRequest request) {

    List<Users> optionalUser = userRepo.findByuserId(user_id);

    if (optionalUser.isEmpty()) {
      userResponse.setMessage(ResponseCode.USER_NOT_UPDATED.getMessage());
      userResponse.setStatus(ResponseCode.USER_NOT_UPDATED.getStatus());
    } else {
      Users user = optionalUser.get(0);
      String userKey = user.getEncryptionKey();

      if (request.getConfirm_password() != null && userKey != null) {
        user.setConfirm_password(encryptAES(request.getConfirm_password(), userKey));
      }
      if (request.getPassword() != null && userKey != null) {
        user.setPassword(encryptAES(request.getPassword(), userKey));
      }
      user.setEmail(request.getEmail());
      user.setMobileNumber(request.getMobileNumber());
      user.setRole(request.getRole());
      user.setUserName(request.getUserName());

      userRepo.save(user);

      populateUserResponse(userResponse, user);
      userResponse.setMessage(ResponseCode.USER_UPDATED.getMessage());
      userResponse.setStatus(ResponseCode.USER_UPDATED.getStatus());
    }
    return userResponse;
  }

  public UserResponse getSingleUser(String user_id) {
    List<Users> optionalUser = userRepo.findByuserId(user_id);

    UserResponse userResponse = UserResponse.getInstance();
    if (optionalUser.isEmpty()) {
      userResponse.setMessage(ResponseCode.SEARCH_USER_FAILED.getMessage());
      userResponse.setStatus(ResponseCode.SEARCH_USER_FAILED.getStatus());
    } else {
      Users user = optionalUser.get(0);
      populateUserResponse(userResponse, user);
      userResponse.setMessage(ResponseCode.SEARCH_USER.getMessage());
      userResponse.setStatus(ResponseCode.SEARCH_USER.getStatus());
    }
    return userResponse;
  }

  public UserResponse deleteUser(String user_id) {
    List<Users> optionalUser = userRepo.findByuserId(user_id);
    if (optionalUser.isEmpty()) {
      userResponse.setMessage(ResponseCode.NOT_DELETE_USER.getMessage());
      userResponse.setStatus(ResponseCode.NOT_DELETE_USER.getStatus());
    } else {
      Users user = optionalUser.get(0);
      user.setStatus(Users.Status.DELETED);
      userRepo.save(user);

      userResponse.setMessage(ResponseCode.DELETE_USER.getMessage());
      userResponse.setStatus(ResponseCode.DELETE_USER.getStatus());
      populateUserResponse(userResponse, user);
    }
    return userResponse;
  }

  private void populateUserResponse(UserResponse response, Users users) {

    response.setEmail(users.getEmail());
    response.setMobileNumber(users.getMobileNumber());
    response.setRole(users.getRole());
    response.setUserName(users.getUserName());
  }

  private String encryptAES(String input, String key) {
    if (input == null || key == null) {
      throw new IllegalArgumentException("Input and key cannot be null");
    }
    try {
      Cipher cipher = Cipher.getInstance("AES");
      SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
      byte[] encryptedBytes = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().encodeToString(encryptedBytes);
    } catch (Exception e) {
      throw new RuntimeException("Error encrypting with AES", e);
    }
  }

  private String generateAESKey() {
    try {
      KeyGenerator keyGen = KeyGenerator.getInstance("AES");
      keyGen.init(256);
      SecretKey secretKey = keyGen.generateKey();
      byte[] encodedKey = secretKey.getEncoded();
      return Base64.getEncoder().encodeToString(encodedKey);
    } catch (Exception e) {
      throw new RuntimeException("Error generating AES key", e);
    }
  }
}
