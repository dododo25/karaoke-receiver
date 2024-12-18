package com.dododo.receiver.controller;

import com.dododo.receiver.controller.dto.TokenRequestDTO;
import com.dododo.receiver.holder.SessionsHolder;
import com.dododo.receiver.model.GameMode;
import com.dododo.receiver.model.Track;
import com.dododo.receiver.service.TrackService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TrackController {

    private final SessionsHolder sessionsHolder;

    private final TrackService service;

    @GetMapping("/api/tracks")
    public List<TrackResponseDTO> findAllTrackNames() {
        return service.findAll().stream()
                .map(TrackResponseDTO::create)
                .toList();
    }

    @PostMapping("/api/options/active")
    public ResponseEntity<List<String>> findAllActiveOptions(@RequestBody TokenRequestDTO dto) {
        HttpSession session = sessionsHolder.get(dto.getToken());

        if (session == null) {
            return ResponseEntity.badRequest().build();
        }

        GameMode gameMode = (GameMode) session.getAttribute("gameMode");
        Integer trackId = (Integer) session.getAttribute("trackId");

        return ResponseEntity.ok(service.findById(trackId)
                .getModes()
                .get(gameMode.name().toLowerCase())
                .getOptions());
    }

    public static class TrackResponseDTO {

        private int id;

        private String name;

        private String artist;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public static TrackResponseDTO create(Track track) {
            TrackResponseDTO dto = new TrackResponseDTO();

            dto.setId(track.getId());
            dto.setName(track.getName());
            dto.setArtist(track.getArtist());

            return dto;
        }
    }
}