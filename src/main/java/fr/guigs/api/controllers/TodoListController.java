package fr.guigs.api.controllers;

import fr.guigs.api.models.Tag;
import fr.guigs.api.models.Task;
import fr.guigs.api.models.TodoList;
import fr.guigs.api.services.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todolists")
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    @GetMapping
    public List<TodoList> getAllTodoLists() {
        return todoListService.findAll();
    }

    @PostMapping
    public TodoList createTodoList(@RequestBody TodoList todoList) {
        return todoListService.save(todoList);
    }

    @PostMapping("/{listId}/tasks")
    public Task addTaskToList(@PathVariable Long listId, @RequestBody Task task) {
        return todoListService.addTaskToList(listId, task);
    }

    @PostMapping("/{taskId}/tags")
    public Tag addTagToTask(@PathVariable Long taskId, @RequestBody Tag tag) {
        return taskService.addTagToTask(taskId, tag);
    }

    public Tag addTagToTask(Long taskId, Tag tag) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        tag.setTask(task);
        return tagRepository.save(tag);
    }
}
