package com.dododo.receiver.model;

import lombok.Getter;

import java.util.Map;

@Getter
public class Track {

    private int id;

    private String name;

    private String artist;

    private Map<String, Data> modes;

}
