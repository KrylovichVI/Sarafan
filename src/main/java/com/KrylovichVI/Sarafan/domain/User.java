package com.KrylovichVI.Sarafan.domain;


import com.fasterxml.jackson.annotation.*;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = @JoinColumn(name = "subscriber_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id")
    )
    @JsonView(value = Views.FullProfile.class)
    @JsonIdentityReference
    @JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
    private Set<User> subscriptions = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = @JoinColumn(name = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriber_id")
    )
    @JsonView(value = Views.FullProfile.class)
    @JsonIdentityReference
    @JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
    private Set<User> subscribers = new HashSet<>();

}
