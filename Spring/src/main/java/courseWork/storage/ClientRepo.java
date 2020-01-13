package courseWork.storage;


import org.springframework.data.jpa.repository.JpaRepository;
import courseWork.logic.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
    @Query(value =  "SELECT * FROM clients u WHERE u.Email = :Email AND u.password = :password", nativeQuery = true)
    Optional<Client> getClientForLogin(@Param("Email") String Email, @Param("password") String password);

    @Query(value =  "SELECT * FROM clients u WHERE u.Email = :Email", nativeQuery = true)
    Optional<Client> getClientForLogin1(@Param("Email") String Email);

}
