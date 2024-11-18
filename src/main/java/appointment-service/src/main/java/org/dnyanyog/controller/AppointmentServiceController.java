package org.dnyanyog.controller;

import org.dnyanyog.dto.AppointmentRequest;
import org.dnyanyog.dto.AppointmentResponse;
import org.dnyanyog.service.impl.AppointmentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentServiceController {
  @Autowired AppointmentServiceImp appointmentService;

  @PostMapping(
      path = "/api/v1/appointment/add",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public AppointmentResponse addAppointment(@RequestBody AppointmentRequest appointmentRequest)
      throws Exception {
    return appointmentService.addAppointment(appointmentRequest);
  }

  @PostMapping(path = "/api/v1/appointment/{appointment_id}")
  public AppointmentResponse updateAppointment(
      @PathVariable String appointment_id, @RequestBody AppointmentRequest request) {
    return appointmentService.updateAppointment(appointment_id, request);
  }

  @GetMapping(path = "/api/v1/appointment/patient/{patient_id}")
  public AppointmentResponse getSingleAppointment(@PathVariable String patient_id) {

    return appointmentService.getSingleAppointment(patient_id);
  }

  @GetMapping(path = "/api/v1/appointment/{appointment_id}")
  public AppointmentResponse getAppointment(@PathVariable String appointment_id) {

    return appointmentService.getAppointment(appointment_id);
  }

  @DeleteMapping(path = "/api/v1/appointment/{appointment_id}")
  public AppointmentResponse deleteAppointment(@PathVariable String appointment_id) {
    return appointmentService.deleteAppointment(appointment_id);
  }
}
