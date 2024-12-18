package com.dododo.receiver.controller;

import com.dododo.receiver.model.GameMode;
import com.dododo.receiver.service.TrackService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class AnswersController {

    private final TrackService service;

    @GetMapping("/answers")
    public String page(Model model, HttpSession session) {
        boolean isConnected = Optional.ofNullable(session.getAttribute("connected"))
                .map(Boolean.class::cast)
                .orElse(false);
        GameMode gameMode = Optional.ofNullable(session.getAttribute("gameMode"))
                .map(GameMode.class::cast)
                .orElse(null);
        Integer trackId = Optional.ofNullable(session.getAttribute("trackId"))
                .map(Integer.class::cast)
                .orElse(null);
        List<Boolean> answers = Optional.ofNullable(session.getAttribute("answers"))
                .map(List.class::cast)
                .orElse(null);

        if (!isConnected || gameMode == null || trackId == null || answers == null) {
            return "redirect:/select";
        }

        session.setAttribute("refreshed", false);

        model.addAttribute("data", service.findById(trackId)
                .getModes()
                .get(gameMode.name().toLowerCase()));
        model.addAttribute("answers", answers);

        return "answers";
    }

    @GetMapping(value = "/answers/ping")
    public ResponseEntity<Integer> ping(HttpSession session) throws InterruptedException {
        boolean refreshed = Optional.ofNullable(session.getAttribute("refreshed"))
                .map(Boolean.class::cast)
                .orElse(false);

        for (int i = 0; i < 10 && !refreshed; i++) {
            Thread.sleep(1000);

            refreshed = Optional.ofNullable(session.getAttribute("refreshed"))
                    .map(Boolean.class::cast)
                    .orElse(false);
        }

        return ResponseEntity.ok(refreshed ? 0 : 1);
    }
}