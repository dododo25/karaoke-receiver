package com.dododo.receiver.model;

import java.util.List;
import java.util.Map;

public class Track {

    private int id;

    private String name;

    private String artist;

    private List<String> commands;

    private Map<String, GameMode> gameModes;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public List<String> getCommands() {
        return commands;
    }

    public Map<String, GameMode> getGameModes() {
        return gameModes;
    }
}
