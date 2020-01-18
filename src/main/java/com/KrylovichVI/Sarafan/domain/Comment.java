package com.KrylovichVI.Sarafan.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table
@Data
@EqualsAndHashCode( of = { "id" })
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(value = Views.IdName.class)
    private Long id;
    @JsonView(value = Views.IdName.class)
    private String text;

    @ManyToOne
    @JoinColumn(name = "message_id")
    @JsonView(value = Views.FullComment.class)
    private Message message;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false, updatable = false)
    @JsonView(value = Views.IdName.class)
    private User author;
}
