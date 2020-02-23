package com.KrylovichVI.Sarafan.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSubscriptionId implements Serializable{
    @JsonView(value = Views.Id.class)
    private String channelId;
    @JsonView(value = Views.Id.class)
    private String subscriberId;
}
