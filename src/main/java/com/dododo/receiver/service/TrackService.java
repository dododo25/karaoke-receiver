package com.dododo.receiver.service;

import com.dododo.receiver.model.Track;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Service
public class TrackService {

    private final List<Track> tracks;

    public TrackService() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Track>> typeReference = new TypeReference<>() {};

        try (InputStream inputStream = TrackService.class.getResourceAsStream("/tracks.json")) {
            this.tracks = Collections.unmodifiableList(mapper.readValue(inputStream, typeReference));
        }
    }

    public List<Track> findAll() {
        return tracks;
    }

    public Track findById(int id) {
        return tracks.stream()
                .filter(v -> v.getId() == id)
                .findAny()
                .orElse(null);
    }
}
