package fr.guigs.api.controllers;

import fr.guigs.api.models.Task;
import fr.guigs.api.services.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TaskReadController {

    private final TaskService taskService;

    public TaskReadController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public Page<Task> getAllTasks(@RequestParam Optional<Integer> page,
                                  @RequestParam Optional<Integer> size) {
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(10);
        return taskService.getAllTasks(currentPage, pageSize);
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("todoLists/{todoListId}/tasks")
    public Page<Task> getTasksByTodoListId(@PathVariable Long todoListId,
                                          @RequestParam Optional<Integer> page,
                                          @RequestParam Optional<Integer> size) {
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(10);
        return taskService.getTasksByTodoListId(todoListId, currentPage, pageSize);
    }
}