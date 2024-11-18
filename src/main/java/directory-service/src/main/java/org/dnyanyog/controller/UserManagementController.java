package org.dnyanyog.controller;

import org.dnyanyog.dto.UserRequest;
import org.dnyanyog.dto.UserResponse;
import org.dnyanyog.service.UserManagementServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserManagementController {

  @Autowired UserManagementServiceImp userService;

  @PostMapping(
      path = "/api/v1/directory/add",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public UserResponse addUser(@RequestBody UserRequest userRequest) throws Exception {
    return userService.addUser(userRequest);
  }

  @GetMapping(path = "/api/v1/directory/{user_id}")
  public UserResponse getSingleUser(@PathVariable String user_id) {

    return userService.getSingleUser(user_id);
  }

  @DeleteMapping(path = "/api/v1/directory/{user_id}")
  public UserResponse deleteUser(@PathVariable String user_id) {
    return userService.deleteUser(user_id);
  }

  @PostMapping(path = "/api/v1/directory/{user_id}")
  public UserResponse updateUser(
      @PathVariable String user_id, @RequestBody UserRequest userRequest) {
    return userService.updateUser(user_id, userRequest);
  }
}
