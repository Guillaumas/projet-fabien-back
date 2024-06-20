package fr.guigs.api.repositories;


import fr.guigs.api.models.Label;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
    @Query("SELECT l FROM Label l JOIN l.tasks t JOIN t.todoList tl JOIN tl.user u WHERE u.id = :userId")
    Page<Label> findAllByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT l FROM Label l JOIN l.tasks t WHERE t.id = :taskId")
    Page<Label> findAllByTaskId(@Param("taskId") Long taskId, Pageable pageable);
}

