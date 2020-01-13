package courseWork.storage;


import org.springframework.data.jpa.repository.JpaRepository;
import courseWork.logic.Administrator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Administrator, Integer> {
    @Query(value =  "SELECT * FROM admins u WHERE u.Email = :Email", nativeQuery = true)
    Optional<Administrator> findAdminForLogin(@Param("Email") String Email);
}
