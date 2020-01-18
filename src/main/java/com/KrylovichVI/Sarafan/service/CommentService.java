package com.KrylovichVI.Sarafan.service;

import com.KrylovichVI.Sarafan.domain.Comment;
import com.KrylovichVI.Sarafan.domain.User;
import com.KrylovichVI.Sarafan.domain.Views;
import com.KrylovichVI.Sarafan.dto.EventType;
import com.KrylovichVI.Sarafan.dto.ObjectType;
import com.KrylovichVI.Sarafan.repo.CommentRepo;
import com.KrylovichVI.Sarafan.util.WsSender;
import org.apache.logging.log4j.util.BiConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepo commentRepo;
    private final BiConsumer<EventType, Comment> wsSender;

    @Autowired
    public CommentService(CommentRepo commentRepo, WsSender wsSender) {
        this.commentRepo = commentRepo;
        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
    }

    public Comment create(Comment comment, User user){
        comment.setAuthor(user);
        Comment commentFromDb = commentRepo.save(comment);

        wsSender.accept(EventType.CREATE, commentFromDb);

        return commentFromDb;
    }
}
