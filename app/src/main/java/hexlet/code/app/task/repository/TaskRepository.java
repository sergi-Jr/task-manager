package hexlet.code.app.task.repository;

import hexlet.code.app.task.model.Task;
import hexlet.code.app.task.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByTaskStatus(TaskStatus status);
}