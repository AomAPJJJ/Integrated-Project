package sit.int221.servicetasksj3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.servicetasksj3.entities.Task;
import sit.int221.servicetasksj3.entities.TaskStatus;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
//    List<TaskStatus> findByStatus(String status);
}
