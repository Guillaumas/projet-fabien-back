package fr.guigs.api.repositories;

import fr.guigs.api.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByTodoListId(Long todoListId, Pageable pageable);
    Page<Task> findByLabelsId(Long labelId, Pageable pageable);
}

