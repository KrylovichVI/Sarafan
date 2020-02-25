package com.KrylovichVI.Sarafan.repo;


import com.KrylovichVI.Sarafan.domain.User;
import com.KrylovichVI.Sarafan.domain.UserSubscription;
import com.KrylovichVI.Sarafan.domain.UserSubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSubscriptionRepo extends JpaRepository<UserSubscription, UserSubscriptionId>{
    List<UserSubscription> findBySubscriber(User user);

    List<UserSubscription> findByChannel(User channel);

    UserSubscription findByChannelAndSubscriber(User channel, User subscriber);
}
