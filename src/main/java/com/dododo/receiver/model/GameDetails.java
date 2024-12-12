package com.dododo.receiver.model;

import java.util.List;

public class GameDetails {

    private boolean connected;

    private String gameMode;

    private List<Track> activeTracks;

    private int trackId;

    private List<Boolean> answers;

    private int refreshCount;

    private int realRefreshCount;

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

    public List<Track> getActiveTracks() {
        return activeTracks;
    }

    public void setActiveTracks(List<Track> activeTracks) {
        this.activeTracks = activeTracks;
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

    public int getRefreshCount() {
        return refreshCount;
    }

    public void setRefreshCount(int refreshCount) {
        this.refreshCount = refreshCount;
    }

    public int getRealRefreshCount() {
        return realRefreshCount;
    }

    public void setRealRefreshCount(int realRefreshCount) {
        this.realRefreshCount = realRefreshCount;
    }
}
