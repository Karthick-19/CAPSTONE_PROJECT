package com.teamsync.securityGateway.repo;

import com.teamsync.securityGateway.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);
    Users findById(int id);
}
