package com.collab.collabediting.controllers;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CodeEditorController {
    @MessageMapping("/code")
    @SendTo("/code/collaborators.{roomId}")
    public String sendCodeEdit(@DestinationVariable String roomId,String code){
        return code ;
    }

}
