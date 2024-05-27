package fr.guigs.api.controllers;

import fr.guigs.api.models.Label;
import fr.guigs.api.services.TaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/{taskId}/labels")
    public Label addLabelToTask(@PathVariable Long taskId, @RequestBody Label label) {
        return taskService.addLabelToTask(taskId, label);
    }
}
