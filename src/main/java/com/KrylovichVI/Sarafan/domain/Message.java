package com.KrylovichVI.Sarafan.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "text"})
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(value = Views.Id.class)
    private Long id;
    @JsonView(value = Views.IdName.class)
    private String text;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(value = Views.FullMessage.class)
    private LocalDateTime creationDate;

    @JsonView(value = Views.FullMessage.class)
    private String link;
    @JsonView(value = Views.FullMessage.class)
    private String linkTitle;
    @JsonView(value = Views.FullMessage.class)
    private String linkDescription;
    @JsonView(value = Views.FullMessage.class)
    private String linkCover;
}
