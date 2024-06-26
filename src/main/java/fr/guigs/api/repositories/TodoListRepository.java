package fr.guigs.api.repositories;

import fr.guigs.api.models.TodoList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    Page<TodoList> findAllByUserId(Long userId, Pageable pageable);
}
