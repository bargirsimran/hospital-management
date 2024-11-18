package org.dnyanyog.service.impl;

import jakarta.validation.Valid;
import java.util.List;
import org.dnyanyog.common.ResponseCode;
import org.dnyanyog.dto.AppointmentRequest;
import org.dnyanyog.dto.AppointmentResponse;
import org.dnyanyog.entity.Appointment;
import org.dnyanyog.repo.AppointmentServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImp {

  @Autowired private AppointmentServiceRepository appointmentRepo;
  @Autowired private AppointmentResponse appointmentResponse;

  public AppointmentResponse addAppointment(@Valid AppointmentRequest request) throws Exception {
    try {
      List<Appointment> optionalAppointment =
          appointmentRepo.findBypatientNameEnglish(request.getPatientNameEnglish());

      if (!optionalAppointment.isEmpty()) {
        appointmentResponse.setStatus(ResponseCode.APPOINTMENT_FAILED.getStatus());
        appointmentResponse.setMessage(ResponseCode.APPOINTMENT_FAILED.getMessage());
        return appointmentResponse;
      }
      Appointment newAppointment = new Appointment();
      newAppointment.setAppointment_time(request.getAppointment_time());
      newAppointment.setPatientNameEnglish(request.getPatientNameEnglish());

      newAppointment.setExamination_date(request.getExamination_date());
      newAppointment.setStatus(Appointment.Status.ACTIVE);
      newAppointment.generateAppointmentId();
      newAppointment.generatePatientId();

      newAppointment = appointmentRepo.save(newAppointment);
      populatedCaseResponse(appointmentResponse, newAppointment);
      appointmentResponse.setStatus(ResponseCode.APPOINTMENT_ADDED.getStatus());
      appointmentResponse.setMessage(ResponseCode.APPOINTMENT_ADDED.getMessage());
    } catch (Exception e) {
      appointmentResponse.setStatus(ResponseCode.APPOINTMENT_FAILED.getStatus());
      appointmentResponse.setMessage(ResponseCode.APPOINTMENT_FAILED.getMessage());
    }
    return appointmentResponse;
  }

  public AppointmentResponse updateAppointment(String appointment_id, AppointmentRequest request) {
    List<Appointment> optionalAppointment = appointmentRepo.findByAppointmentId(appointment_id);

    if (optionalAppointment.isEmpty()) {
      appointmentResponse.setStatus(ResponseCode.APPOINTMENT_NOT_UPDATED.getStatus());
      appointmentResponse.setMessage(ResponseCode.APPOINTMENT_NOT_UPDATED.getMessage());
    } else {
      Appointment appointment = optionalAppointment.get(0);

      appointment.setAppointment_time(request.getAppointment_time());
      appointment.setExamination_date(request.getExamination_date());
      appointment.setPatientId(request.getPatientId());
      appointment.setPatientNameEnglish(request.getPatientNameEnglish());
      appointment.setAppointmentId(request.getAppointmentId());

      appointmentRepo.save(appointment);

      populatedCaseResponse(appointmentResponse, appointment);
      appointmentResponse.setMessage(ResponseCode.APPOINTMENT_UPDATED.getMessage());
      appointmentResponse.setStatus(ResponseCode.APPOINTMENT_UPDATED.getStatus());
    }

    return appointmentResponse;
  }

  public AppointmentResponse getSingleAppointment(String patient_id) {
    List<Appointment> optionalAppointment = appointmentRepo.findByPatientId(patient_id);

    if (optionalAppointment.isEmpty()) {
      appointmentResponse.setMessage(ResponseCode.SEARCH_APPOINTMENT_FAILED.getMessage());
      appointmentResponse.setStatus(ResponseCode.SEARCH_APPOINTMENT_FAILED.getStatus());
    } else {
      Appointment appointment = optionalAppointment.get(0);
      populatedCaseResponse(appointmentResponse, appointment);
      appointmentResponse.setMessage(ResponseCode.SEARCH_APPOINTMENT.getMessage());
      appointmentResponse.setStatus(ResponseCode.SEARCH_APPOINTMENT.getStatus());
    }
    return appointmentResponse;
  }

  public AppointmentResponse getAppointment(String appointment_id) {
    List<Appointment> optionalAppointment = appointmentRepo.findByAppointmentId(appointment_id);

    if (optionalAppointment.isEmpty()) {
      appointmentResponse.setMessage(ResponseCode.SEARCH_APPOINTMENT_FAILED.getMessage());
      appointmentResponse.setStatus(ResponseCode.SEARCH_APPOINTMENT_FAILED.getStatus());
    } else {
      Appointment appointment = optionalAppointment.get(0);
      populatedCaseResponse(appointmentResponse, appointment);
      appointmentResponse.setMessage(ResponseCode.SEARCH_APPOINTMENT.getMessage());
      appointmentResponse.setStatus(ResponseCode.SEARCH_APPOINTMENT.getStatus());
    }
    return appointmentResponse;
  }

  public AppointmentResponse deleteAppointment(String appointment_id) {
    List<Appointment> optionalAppointment = appointmentRepo.findByAppointmentId(appointment_id);

    if (optionalAppointment.isEmpty()) {
      appointmentResponse.setMessage(ResponseCode.NOT_DELETE_APPOINTMENT.getMessage());
      appointmentResponse.setStatus(ResponseCode.NOT_DELETE_APPOINTMENT.getStatus());
    } else {
      Appointment appointment = optionalAppointment.get(0);
      appointment.setStatus(Appointment.Status.DELETED);
      appointmentRepo.save(appointment);

      appointmentResponse.setMessage(ResponseCode.DELETE_APPOINTMENT.getMessage());
      appointmentResponse.setStatus(ResponseCode.DELETE_APPOINTMENT.getStatus());
      populatedCaseResponse(appointmentResponse, appointment);
    }
    return appointmentResponse;
  }

  private void populatedCaseResponse(AppointmentResponse response, Appointment appointment) {
    response.setAppointment_time(appointment.getAppointment_time());
    response.setExamination_date(appointment.getExamination_date());
    response.setPatientId(appointment.getPatientId());
    response.setPatientNameEnglish(appointment.getPatientNameEnglish());
    response.setAppointmentId(appointment.getAppointmentId());
  }
}
