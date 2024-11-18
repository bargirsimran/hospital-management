package org.dnyanyog.repo;

import java.util.List;
import org.dnyanyog.entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface PatientsRepository extends JpaRepository<Patients, Long> {
  List<Patients> findByPatientNameEnglish(String patientNameEnglish);

  List<Patients> findByPatientId(String patientId);
}
