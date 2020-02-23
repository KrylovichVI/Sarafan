package com.KrylovichVI.Sarafan.controller;

import com.KrylovichVI.Sarafan.domain.User;
import com.KrylovichVI.Sarafan.domain.Views;
import com.KrylovichVI.Sarafan.dto.MessagePageDto;
import com.KrylovichVI.Sarafan.repo.UserDetailsRepo;
import com.KrylovichVI.Sarafan.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    private final MessageService messageService;
    private final UserDetailsRepo userDetailsRepo;

    @Value("${spring.profiles.active}")
    private String profile;
    private final ObjectWriter messageWriter;
    private final ObjectWriter profileWriter;

    @Autowired
    public MainController(MessageService messageService, ObjectMapper mapper, UserDetailsRepo userDetailsRepo) {
        this.messageService = messageService;
        this.userDetailsRepo = userDetailsRepo;

        ObjectMapper objectMapper = mapper
                .setConfig(mapper.getSerializationConfig());
        this.messageWriter = objectMapper
                .writerWithView(Views.FullMessage.class);

        this.profileWriter = objectMapper
                .writerWithView(Views.FullProfile.class);
    }

    @GetMapping
    public String main(@AuthenticationPrincipal User user, Model model) throws JsonProcessingException {
        HashMap<Object, Object> data = new HashMap<>();

        if(user != null) {
            User userFromDb = userDetailsRepo.findById(user.getId()).get();
            String serializedProfile = profileWriter.writeValueAsString(userFromDb);
            model.addAttribute("profile", serializedProfile);

            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            PageRequest pageRequest = PageRequest.of(0, MessageController.MESSAGES_PER_PAGE, sort);
            MessagePageDto messagePageDto = messageService.findForUser(pageRequest, user);

            String messages = messageWriter.writeValueAsString(messagePageDto.getMessages());

            model.addAttribute("messages", messages);
            data.put("currentPage", messagePageDto.getCurrentPage());
            data.put("totalPages", messagePageDto.getTotalPages());
        } else {
            model.addAttribute("messages", "[]");
            model.addAttribute("profile", "null");
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "index";
    }
}
