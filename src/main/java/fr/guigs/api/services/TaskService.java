package fr.guigs.api.services;

import fr.guigs.api.models.Label;
import fr.guigs.api.models.Task;
import fr.guigs.api.repositories.LabelRepository;
import fr.guigs.api.repositories.TaskRepository;
import org.springframework.stereotype.Service;

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
}
