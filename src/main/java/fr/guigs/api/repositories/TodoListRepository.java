package fr.guigs.api.repositories;

import fr.guigs.api.models.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
}
