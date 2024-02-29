package test.taskService.controller;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.taskService.service.TaskService;
import test.taskService.dto.Task;

import java.util.List;

@RestController
@RequestMapping("/tasks") // должен обязательно совпадать с интерфейсом
public class TaskControllerImpl implements TaskController {
    private final MeterRegistry meterRegistry;
    @Autowired
    private TaskService taskService;

    public TaskControllerImpl(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    /**
     * После обновления http://localhost:8081/actuator/metrics в метрике появится наш request_getAllTask
     * при переходе на http://localhost:8081/actuator/metrics/request_getAllTask увидим:
     * {
     *     "name": "request_getAllTask",
     *     "measurements": [
     *         {
     *             "statistic": "COUNT",
     *             "value": 3
     *         }
     *     ],
     *     "availableTags": []
     * }
     */
    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        meterRegistry.counter("request_getAllTask").increment(); // фиксируем кол-во запросов
        meterRegistry.counter("request_server").increment();

        return ResponseEntity.ok(taskService.getTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable(name = "id") Long id) {
        meterRegistry.timer("getTaskById").record(this::getTasks);
        meterRegistry.counter("request_server").increment();
        return ResponseEntity.ok(this.taskService.getTaskById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(@PathVariable(name = "id") Long id, @RequestBody Task task) {
        meterRegistry.counter("request_server").increment();
        String updateTask = this.taskService.getTaskById(id).getName();
        taskService.updateTask(id, task);
        return ResponseEntity.ok(String.format("Задача \"%s\" обновлена", updateTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable(name = "id") Long id) {
        meterRegistry.counter("request_server").increment();
        String delTaskName = taskService.getTaskById(id).getName();
        this.taskService.deleteTask(id);
        return ResponseEntity.ok(String.format("Задача \"%s\" удалена", delTaskName));
    }

    @PostMapping
    public ResponseEntity<String> registerTask(@RequestBody Task task) {
        meterRegistry.counter("request_server").increment();
        taskService.registerTask(task);
        return ResponseEntity.ok(String.format("Задача  \"%s\" успешно зарегистрирована", task.getName()));
    }
}
