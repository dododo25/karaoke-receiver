package com.dododo.receiver.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class SelectGameController {

    @GetMapping("/select")
    public String page(Model model, HttpSession session) {
        boolean isConnected = Optional.ofNullable(session.getAttribute("connected"))
                .map(Boolean.class::cast)
                .orElse(false);

        if (!isConnected) {
            return "redirect:/";
        }

        session.setAttribute("gameMode", null);
        session.setAttribute("trackId", null);
        session.setAttribute("answers", null);
        session.setAttribute("refreshed", false);

        return "select";
    }

    @GetMapping("/select/ping")
    public ResponseEntity<Void> ping(HttpSession session) throws InterruptedException {
        while (session.getAttribute("gameMode") == null || session.getAttribute("trackId") == null) {
            Thread.sleep(1000);
        }

        return ResponseEntity.ok(null);
    }
}
