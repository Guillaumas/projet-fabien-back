package fr.guigs.api.repositories;

import fr.guigs.api.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
/*
    Page<User> findAllByOrderByUsernameAsc(Pageable pageable);
*/
}
