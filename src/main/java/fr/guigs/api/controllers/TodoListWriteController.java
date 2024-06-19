package fr.guigs.api.controllers;

import fr.guigs.api.models.TodoList;
import fr.guigs.api.services.TodoListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TodoListWriteController {

    private final TodoListService todoListService;

    public TodoListWriteController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @PostMapping("/todolists")
    public TodoList createTodoList(@RequestBody TodoList todoList) {
        return todoListService.createTodoList(todoList);
    }

    @PutMapping("/todolists/{id}")
    public TodoList updateTodoList(@PathVariable Long id, @RequestBody TodoList todoListDetails) {
        return todoListService.updateTodoList(id, todoListDetails);
    }

    @DeleteMapping("/todolists/{id}")
    public ResponseEntity<?> deleteTodoList(@PathVariable Long id) {
        todoListService.deleteTodoList(id);
        return ResponseEntity.ok().build();
    }
}
