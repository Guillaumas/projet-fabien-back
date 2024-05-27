package fr.guigs.api.services;

import fr.guigs.api.models.Task;
import fr.guigs.api.models.TodoList;
import fr.guigs.api.repositories.TaskRepository;
import fr.guigs.api.repositories.TodoListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {

    private final TodoListRepository todoListRepository;
    private final TaskRepository taskRepository;

    public TodoListService(TodoListRepository todoListRepository, TaskRepository taskRepository) {
        this.todoListRepository = todoListRepository;
        this.taskRepository = taskRepository;
    }

    public List<TodoList> findAll() {
        return todoListRepository.findAll();
    }

    public TodoList save(TodoList todoList) {
        return todoListRepository.save(todoList);
    }

    public Task addTaskToList(Long listId, Task task) {
        TodoList todoList = todoListRepository.findById(listId).orElseThrow(() -> new RuntimeException("List not found"));
        task.setTodoList(todoList);
        return taskRepository.save(task);
    }
}