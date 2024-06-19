package fr.guigs.api.services;

import fr.guigs.api.exceptions.ResourceNotFoundException;
import fr.guigs.api.models.TodoList;
import fr.guigs.api.repositories.TodoListRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TodoListService {

    private final TodoListRepository todoListRepository;

    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public Page<TodoList> getAllTodoLists(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return todoListRepository.findAll(pageable);
    }

    public TodoList getTodoListById(Long id) {
        return todoListRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TodoList not found"));
    }

    public TodoList createTodoList(TodoList todoList) {
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
