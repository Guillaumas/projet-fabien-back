package fr.guigs.api.controllers;

import fr.guigs.api.models.TodoList;
import fr.guigs.api.services.TodoListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/todolists")
public class TodoListReadController {

    private final TodoListService todoListService;

    public TodoListReadController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @GetMapping
    public List<TodoList> getAllTodoLists() {
        return todoListService.findAll();
    }
}