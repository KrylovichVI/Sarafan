package com.KrylovichVI.Sarafan.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usr")
@Data
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"subscribers", "subscriptions"})
public class User implements Serializable{
    @Id
    @JsonView(value = Views.IdName.class)
    private String id;
    @JsonView(value = Views.IdName.class)
    private String name;
    @JsonView(value = Views.IdName.class)
    private String userpic;
    private String email;
    @JsonView(value = Views.FullProfile.class)
    private String gender;
    @JsonView(value = Views.FullProfile.class)
    private String locale;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(value = Views.FullProfile.class)
    private LocalDateTime lastVisit;

    @JsonView(value = Views.FullProfile.class)
    @OneToMany(
            mappedBy = "subscriber",
            orphanRemoval = true
    )
    private Set<UserSubscription> subscriptions = new HashSet<>();

    @JsonView(value = Views.FullProfile.class)
    @OneToMany(
            mappedBy = "channel",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private Set<UserSubscription> subscribers = new HashSet<>();

}
