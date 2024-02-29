package test.taskService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.taskService.dto.Task;

import java.util.List;

@RequestMapping("/tasks")
public interface TaskController {
    @PostMapping
    public ResponseEntity<String> registerTask(@RequestBody Task task);

    @GetMapping
    public ResponseEntity<List<Task>> getTasks();

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") Long id);

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(@PathVariable(value = "id") Long id, @RequestBody Task task);

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable(value = "id") Long id);
}
