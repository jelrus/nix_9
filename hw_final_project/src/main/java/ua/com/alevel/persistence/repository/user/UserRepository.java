package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.BaseRepository;

@Repository
public interface UserRepository extends BaseRepository<User> {

    User findByUsername(String username);

    boolean existsByUsername(String username);
}
