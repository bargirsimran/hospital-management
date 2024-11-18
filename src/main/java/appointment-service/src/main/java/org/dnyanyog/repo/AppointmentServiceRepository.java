package org.dnyanyog.repo;

import java.util.List;
import org.dnyanyog.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface AppointmentServiceRepository extends JpaRepository<Appointment, Long> {

  List<Appointment> findBypatientNameEnglish(String patient_name_english);

  List<Appointment> findByPatientId(String patientId);

  List<Appointment> findByAppointmentId(String appointmentId);
}
