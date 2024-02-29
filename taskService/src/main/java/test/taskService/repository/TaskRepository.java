package test.taskService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.taskService.dto.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
