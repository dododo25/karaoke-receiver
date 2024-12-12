package com.dododo.receiver.model;

import java.util.List;

public class GameMode {

    private String src;

    private List<String> text;

    private List<List<String>> options;

    public String getSrc() {
        return src;
    }

    public List<String> getText() {
        return text;
    }

    public List<List<String>> getOptions() {
        return options;
    }
}
