package com.sjitzooi.templatelibrary_sql.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class TemplatePostUpdateController {

    private final SimpMessagingTemplate messagingTemplate;

    public TemplatePostUpdateController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/updatePost")
    public void updatePost(String postId) {
        // Simulate post update logic


        Object updatedInfo = new Object(){
            String id = postId;
            String Description = "This is a new post";

            public String getDescription() {
                return Description;
            }
            public String getId() {
                return id;
            }
        };
        System.out.println(updatedInfo);
        // Send the update to the specific topic for that postId
        messagingTemplate.convertAndSend("/topic/post-updates/" + postId, updatedInfo);
    }
}
