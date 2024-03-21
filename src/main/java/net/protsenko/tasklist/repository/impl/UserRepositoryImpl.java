package net.protsenko.tasklist.repository.impl;

import net.protsenko.tasklist.domain.user.Role;
import net.protsenko.tasklist.domain.user.User;
import net.protsenko.tasklist.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void create(User user) {

    }

    @Override
    public void insertUserRole(Long userId, Role role) {

    }

    @Override
    public boolean isTaskHolder(Long userId, Long taskId) {
        return false;
    }

    @Override
    public void delete(Long id) {

    }

}
