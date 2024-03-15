package com.example.booknook.repo;

import com.example.booknook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface UserRepo extends JpaRepository<User,Long> {
    User findByNameAndPassword(String name, String password);
}
