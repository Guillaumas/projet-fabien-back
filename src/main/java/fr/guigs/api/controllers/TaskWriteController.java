package fr.guigs.api.controllers;

import fr.guigs.api.models.Task;
import fr.guigs.api.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks/")
public class TaskWriteController {

    private final TaskService taskService;

    public TaskWriteController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping()
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        return taskService.updateTask(id, taskDetails);
    }

    @PatchMapping("{id}")
    public Task patchTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        return taskService.patchTask(id, taskDetails);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}
