package com.dododo.receiver.model;

import java.util.List;

public class GameDetails {

    private boolean connected;

    private String gameMode;

    private int trackId;

    private List<Boolean> answers;

    private boolean refreshed;

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public List<Boolean> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Boolean> answers) {
        this.answers = answers;
    }

    public boolean isRefreshed() {
        return refreshed;
    }

    public void setRefreshed(boolean refreshed) {
        this.refreshed = refreshed;
    }
}
