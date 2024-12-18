package com.dododo.receiver.controller;

import com.dododo.receiver.model.GameMode;
import com.dododo.receiver.service.TrackService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class PlayController {

    private final TrackService service;

    @GetMapping("/play")
    public String page(Model model, HttpSession session) {
        boolean connected = Optional.ofNullable(session.getAttribute("connected"))
                .map(Boolean.class::cast)
                .orElse(false);
        GameMode gameMode = Optional.ofNullable(session.getAttribute("gameMode"))
                .map(GameMode.class::cast)
                .orElse(null);
        Integer trackId = Optional.ofNullable(session.getAttribute("trackId"))
                .map(Integer.class::cast)
                .orElse(null);

        if (!connected || gameMode == null || trackId == null) {
            return "redirect:/select";
        }

        session.setAttribute("answers", null);
        session.setAttribute("refreshed", false);

        model.addAttribute("data", service.findById(trackId)
                .getModes()
                .get(gameMode.name().toLowerCase()));

        return "play";
    }

    @GetMapping("/play/ping")
    public ResponseEntity<Integer> ping(HttpSession session) throws InterruptedException {
        boolean refreshed = Optional.ofNullable(session.getAttribute("refreshed"))
                .map(Boolean.class::cast)
                .orElse(false);

        while (!refreshed) {
            Thread.sleep(1000);

            refreshed = Optional.ofNullable(session.getAttribute("refreshed"))
                    .map(Boolean.class::cast)
                    .orElse(false);
        }

        return ResponseEntity.ok(session.getAttribute("answers") == null ? 0 : 1);
    }
}
