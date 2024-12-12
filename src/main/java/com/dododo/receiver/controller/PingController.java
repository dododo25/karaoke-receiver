package com.dododo.receiver.controller;

import com.dododo.receiver.model.GameDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @Autowired
    private GameDetails details;

    @GetMapping(value = "/ping")
    public String indexPing() {
        return details.isConnected() ? "true" : "false";
    }

    @GetMapping(value = "/select/ping")
    public String selectPing() {
        return details.getGameMode() == null ? "false" : "true";
    }

    @GetMapping(value = "/answers/ping")
    public String answersPing() {
        return details.getAnswers() == null ? "false" : "true";
    }
}