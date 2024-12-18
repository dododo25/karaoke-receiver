package com.dododo.receiver.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Data {

    private String src;

    private List<String> text;

    private List<String> commands;

    private List<String> options;

    private List<List<String>> blocks;

}
