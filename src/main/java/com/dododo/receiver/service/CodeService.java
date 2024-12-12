package com.dododo.receiver.service;

import org.springframework.stereotype.Service;

@Service
public class CodeService {

    private String value;

    public String get() {
        return value;
    }

    public void set(String value) {
        this.value = value;
    }
}
