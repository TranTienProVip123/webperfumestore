package web.Webperfume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.Webperfume.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByUsernameContaining(String keyword);
    User findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}


