package fr.guigs.api.repositories;


import fr.guigs.api.models.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {
}

