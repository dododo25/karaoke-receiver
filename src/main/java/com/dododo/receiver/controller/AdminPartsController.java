package com.dododo.receiver.controller;

import com.dododo.receiver.model.GameDetails;
import com.dododo.receiver.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class AdminPartsController {

    @Autowired
    private CodeService service;

    @Autowired
    private GameDetails details;

    @PostMapping(value = "/join")
    public ResponseEntity<Object> join(@RequestBody RequestCodeData data) {
        boolean res = Objects.equals(service.get(), data.code);

        if (res) {
            details.setConnected(Objects.equals(service.get(), data.code));
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(value = "/selectGame")
    public void selectGame(@RequestBody RequestGameModeData data) {
        details.setGameMode(data.gameMode);
    }

    @PostMapping(value = "/selectTrack")
    public void selectTrack(@RequestBody RequestGameTrackData data) {
        details.setTrackId(data.trackId);
    }

    @PostMapping(value = "/answers")
    public void trackAnswers(@RequestBody List<Boolean> values) {
        details.setAnswers(values);
    }

    @PostMapping(value = "/refresh")
    public void refreshPages() {
        details.setRefreshCount(details.getRefreshCount() + 1);
        details.setRealRefreshCount(1);
    }

    public static class RequestCodeData {

        private String code;

        public void setCode(String code) {
            this.code = code;
        }
    }

    public static class RequestGameModeData {

        private String gameMode;

        public void setGameMode(String gameMode) {
            this.gameMode = gameMode;
        }
    }

    public static class RequestGameTrackData {

        private int trackId;

        public void setTrackId(int trackId) {
            this.trackId = trackId;
        }
    }
}