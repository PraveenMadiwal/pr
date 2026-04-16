package com.mypackages.pr.repository;

import com.mypackages.pr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // database repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email); // auto create query
}