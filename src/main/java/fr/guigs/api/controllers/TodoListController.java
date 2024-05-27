package fr.guigs.api.controllers;

import fr.guigs.api.models.Task;
import fr.guigs.api.models.TodoList;
import fr.guigs.api.services.TodoListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todolists")
public class TodoListController {

    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

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
}
