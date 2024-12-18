package com.dododo.receiver.service;

import com.dododo.receiver.model.Track;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TrackService {

    private final Map<Integer, Track> tracks;

    public TrackService() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Track>> typeReference = new TypeReference<>() {};

        try (InputStream inputStream = TrackService.class.getResourceAsStream("/tracks.json")) {
            this.tracks = mapper.readValue(inputStream, typeReference).stream()
                    .collect(Collectors.toMap(Track::getId, Function.identity()));
        }
    }

    public List<Track> findAll() {
        return List.copyOf(tracks.values());
    }

    public Track findById(int id) {
        return tracks.getOrDefault(id, null);
    }
}
