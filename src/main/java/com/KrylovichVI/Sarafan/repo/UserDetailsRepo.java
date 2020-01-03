package com.KrylovichVI.Sarafan.repo;

import com.KrylovichVI.Sarafan.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}
