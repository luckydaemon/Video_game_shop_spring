package courseWork.storage;


import org.springframework.data.jpa.repository.JpaRepository;
import courseWork.logic.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Integer> {

}