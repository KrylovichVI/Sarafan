package com.KrylovichVI.Sarafan.controller;

import com.KrylovichVI.Sarafan.domain.User;
import com.KrylovichVI.Sarafan.domain.Views;
import com.KrylovichVI.Sarafan.service.ProfileService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("profile")
public class ProfileController {
    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("{id}")
    @JsonView(value = Views.FullProfile.class)
    public User get(@PathVariable("id") User user){
        return user;
    }

    @PostMapping("change-subscription/{channelId}")
    @JsonView(value = Views.FullProfile.class)
    public User changeSubscription(
            @AuthenticationPrincipal User subscriber,
            @PathVariable("channelId") User channel
    ) {
        if (subscriber.equals(channel)) {
            return channel;
        } else {
            return profileService.changeSubscription(channel, subscriber);
        }
    }

}
