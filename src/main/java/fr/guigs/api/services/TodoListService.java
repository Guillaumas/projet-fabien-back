package fr.guigs.api.services;

import org.springframework.stereotype.Service;

@Service
public class TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;
    @Autowired
    private TaskRepository taskRepository;

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
