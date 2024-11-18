package org.dnyanyog.repo;

import java.util.List;
import org.dnyanyog.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface UserRepository extends JpaRepository<Users, Long> {
  List<Users> findBymobileNumber(String mobile_number);

  List<Users> findByuserName(String user_name);

  List<Users> findByuserId(String user_id);
}
