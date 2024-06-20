package fr.guigs.api.services;

import fr.guigs.api.exceptions.ResourceNotFoundException;
import fr.guigs.api.models.TodoList;
import fr.guigs.api.models.User;
import fr.guigs.api.repositories.TodoListRepository;
import fr.guigs.api.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TodoListService {

    private final TodoListRepository todoListRepository;

    private final UserRepository userRepository;

    public TodoListService(TodoListRepository todoListRepository, UserRepository userRepository) {
        this.todoListRepository = todoListRepository;
        this.userRepository = userRepository;
    }

    public Page<TodoList> getAllTodoLists(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return todoListRepository.findAll(pageable);
    }

    public Page<TodoList> getAllTodoListsByUserId(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return todoListRepository.findAllByUserId(userId, pageable);
    }

    public TodoList getTodoListById(Long id) {
        return todoListRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TodoList not found"));
    }

    public TodoList createTodoList(TodoList todoList) {
        return todoListRepository.save(todoList);
    }

    public TodoList createTodoList(Long userId, TodoList todoList) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        todoList.setUser(user);
        return todoListRepository.save(todoList);
    }

    public TodoList updateTodoList(Long id, TodoList todoListDetails) {
        TodoList todoList = todoListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TodoList not found"));

        todoList.setTitle(todoListDetails.getTitle());

        return todoListRepository.save(todoList);
    }

    public void deleteTodoList(Long id) {
        TodoList todoList = todoListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TodoList not found"));

        todoListRepository.delete(todoList);
    }

    public TodoList patchTodoList(Long id, TodoList todoListDetails) {
        TodoList todoList = todoListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TodoList not found"));

        if (todoListDetails.getTitle() != null) {
            todoList.setTitle(todoListDetails.getTitle());
        }
        if (todoListDetails.getTasks() != todoList.getTasks()) {
            todoList.setTasks(todoListDetails.getTasks());
        }

        return todoListRepository.save(todoList);
    }
}
