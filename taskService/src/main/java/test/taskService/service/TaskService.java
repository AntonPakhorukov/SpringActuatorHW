package test.taskService.service;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.taskService.repository.TaskRepository;
import test.taskService.dto.Task;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll().stream().sorted().collect(Collectors.toList());
    }

    public String registerTask(Task task) {
        if (task.getStatus() == null) task.setStatus(Task.Status.Todo);
        task.setDate(String.valueOf(Calendar.getInstance().getTime()));
        taskRepository.save(task);
        return "Задача зарегистрирована";
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        String.format("Задачи с номером %d не найдено", id)));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task updateTask(Long id, Task task) {
        Task tempTask = getTaskById(id);
        tempTask.setName(task.getName());
        tempTask.setDescription(task.getDescription());
        tempTask.setStatus(task.getStatus());
        taskRepository.save(tempTask);
        return tempTask;
    }
}
