package com.dododo.receiver.controller;

import com.dododo.receiver.generator.CodeGenerator;
import com.dododo.receiver.model.GameDetails;
import com.dododo.receiver.service.CodeService;
import com.dododo.receiver.service.TrackService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class PagesController {

    @Autowired
    private CodeService codeService;

    @Autowired
    private TrackService trackService;

    @Autowired
    private CodeGenerator codeGenerator;

    @Autowired
    private GameDetails details;

    @GetMapping("/")
    public String mainPage(Model model, HttpServletRequest request, HttpServletResponse response) {
        details.setConnected(false);
        details.setRefreshed(false);
        details.setGameMode(null);
        details.setTrackId(-1);
        details.setAnswers(null);

        codeService.set(codeGenerator.generate());

        model.addAttribute("code", IntStream.range(0, codeService.get().length() / 2)
                .mapToObj(i -> codeService.get().substring(i * 2, i * 2 + 2))
                .collect(Collectors.joining(" ")));

        return "index";
    }

    @GetMapping(value = "/select")
    public String selectGamePage(Model model) {
        details.setRefreshed(false);
        details.setGameMode(null);
        details.setTrackId(-1);
        details.setAnswers(null);

        if (details.isConnected()) {
            return "select";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/play")
    public String playPage(Model model) {
        details.setAnswers(null);
        details.setRefreshed(false);

        if (details.isConnected() && details.getTrackId() != -1) {
            model.addAttribute("gameMode", details.getGameMode());
            model.addAttribute("track", trackService.findById(details.getTrackId()));
            return "play";
        }

        return "redirect:/select";
    }

    @GetMapping(value = "/answers")
    public String answersPage(Model model) {
        details.setRefreshed(false);

        if (Objects.equals(details.getGameMode(), "karaoke")) {
            return "redirect:/select";
        }

        if (details.isConnected() && details.getGameMode() != null && details.getAnswers() != null) {
            model.addAttribute("track", trackService.findById(details.getTrackId()));
            model.addAttribute("gameMode", details.getGameMode());
            model.addAttribute("answers", details.getAnswers());

            return "answers";
        }

        return "redirect:/select";
    }
}