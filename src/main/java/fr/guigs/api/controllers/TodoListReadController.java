package fr.guigs.api.controllers;

import fr.guigs.api.models.TodoList;
import fr.guigs.api.services.TodoListService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TodoListReadController {

    private final TodoListService todoListService;

    public TodoListReadController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @GetMapping("/todolists")
    public Page<TodoList> getAllTodoLists(@RequestParam Optional<Integer> page,
                                          @RequestParam Optional<Integer> size) {
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(10);
        return todoListService.getAllTodoLists(currentPage, pageSize);
    }

    @GetMapping("/todolists/{id}")
    public TodoList getTodoListById(@PathVariable Long id) {
        return todoListService.getTodoListById(id);
    }
}
