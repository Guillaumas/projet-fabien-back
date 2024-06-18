package fr.guigs.api.controllers;

import fr.guigs.api.models.Task;
import fr.guigs.api.models.TodoList;
import fr.guigs.api.services.TodoListService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todolists")
public class TodoListWriteController {

    private final TodoListService todoListService;

    public TodoListWriteController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @PostMapping
    public TodoList createTodoList(@RequestBody TodoList todoList) {
        return todoListService.save(todoList);
    }

    @PutMapping("/{listId}")
    public TodoList updateTodoList(@PathVariable Long listId, @RequestBody TodoList todoList) {
        return todoListService.update(listId, todoList);
    }

    @PostMapping("/{listId}/tasks")
    public Task addTaskToList(@PathVariable Long listId, @RequestBody Task task) {
        return todoListService.addTaskToList(listId, task);
    }

    @DeleteMapping("/{listId}/tasks/{taskId}")
    public void deleteTaskFromList(@PathVariable Long listId, @PathVariable Long taskId) {
        todoListService.deleteTaskFromList(listId, taskId);
    }
}
