package com.KrylovichVI.Sarafan.util;

import com.KrylovichVI.Sarafan.dto.EventType;
import com.KrylovichVI.Sarafan.dto.ObjectType;
import com.KrylovichVI.Sarafan.dto.WsEventDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.logging.log4j.util.BiConsumer;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WsSender {
    private final SimpMessagingTemplate template;
    private final ObjectMapper mapper;

    public WsSender(SimpMessagingTemplate template, ObjectMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    public <T> BiConsumer<EventType, T> getSender(ObjectType objectType, Class view){
        ObjectWriter writer = mapper
                .setConfig(mapper.getSerializationConfig())
                .writerWithView(view);
        return (EventType eventType, T payload) -> {
                String value = null;
            try {
                value = writer.writeValueAsString(payload);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            template.convertAndSend(
                        "/topic/activity",
                        new WsEventDto(objectType, eventType, value)
                );
        };
    }
}