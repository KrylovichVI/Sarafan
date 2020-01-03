package com.KrylovichVI.Sarafan.repo;

import com.KrylovichVI.Sarafan.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
