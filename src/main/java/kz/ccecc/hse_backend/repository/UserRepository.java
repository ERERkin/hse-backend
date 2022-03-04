package kz.ccecc.hse_backend.repository;

import kz.ccecc.hse_backend.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User> {
    User findByLogin(String login);
}