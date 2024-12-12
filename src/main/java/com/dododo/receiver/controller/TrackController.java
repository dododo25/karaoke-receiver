package com.dododo.receiver.controller;

import com.dododo.receiver.model.GameDetails;
import com.dododo.receiver.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TrackController {

    @Autowired
    private TrackService service;

    @Autowired
    private GameDetails details;

    @GetMapping(value = "/api/tracks")
    public List<TrackData> findAllTrackNames() {
        return service.findAll().stream()
                .map(t -> new TrackData.Builder()
                        .setId(t.getId())
                        .setName(t.getName())
                        .setArtist(t.getArtist())
                        .build())
                .toList();
    }

    @GetMapping(value = "/api/tracks/active")
    public List<TrackData> findAllActiveTracks() {
        return details.getActiveTracks()
                .stream()
                .map(t -> new TrackData.Builder()
                        .setId(t.getId())
                        .setName(t.getName())
                        .setArtist(t.getArtist())
                        .build())
                .toList();
    }

    @GetMapping(value = "/api/options/active")
    public List<String> findAllActiveOptions() {
        return service.findById(details.getTrackId())
                .getGameModes()
                .get(details.getGameMode())
                .getAnswers();
    }

    public static class TrackData {

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

        public static class Builder {

            private int id;

            private String name;

            private String artist;

            public Builder setId(int id) {
                this.id = id;
                return this;
            }

            public Builder setName(String name) {
                this.name = name;
                return this;
            }

            public Builder setArtist(String artist) {
                this.artist = artist;
                return this;
            }

            public TrackData build() {
                TrackData result = new TrackData();

                result.setId(id);
                result.setName(name);
                result.setArtist(artist);

                return result;
            }
        }
    }
}