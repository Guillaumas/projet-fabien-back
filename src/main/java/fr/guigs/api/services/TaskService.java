package fr.guigs.api.services;

import fr.guigs.api.models.Label;
import fr.guigs.api.models.Task;
import fr.guigs.api.repositories.LabelRepository;
import fr.guigs.api.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final LabelRepository labelRepository;

    public TaskService(TaskRepository taskRepository, LabelRepository labelRepository) {
        this.taskRepository = taskRepository;
        this.labelRepository = labelRepository;
    }

    public Label addLabelToTask(Long taskId, Label label) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        task.getLabels().add(label);
        label.getTasks().add(task);
        labelRepository.save(label);
        taskRepository.save(task);
        return label;
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task update(Long id, Task newTask) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(newTask.getTitle());
                    task.setDescription(newTask.getDescription());
                    task.setDone(newTask.isDone());
                    return taskRepository.save(task);
                })
                .orElseGet(() -> {
                    newTask.setId(id);
                    return taskRepository.save(newTask);
                });
    }
}
