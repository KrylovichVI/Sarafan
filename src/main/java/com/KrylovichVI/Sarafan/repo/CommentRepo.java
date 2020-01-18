package com.KrylovichVI.Sarafan.repo;

import com.KrylovichVI.Sarafan.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepo extends JpaRepository<Comment, Long> {
}
