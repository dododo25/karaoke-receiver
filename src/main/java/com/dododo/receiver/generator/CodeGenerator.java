package com.dododo.receiver.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CodeGenerator {

    private static final Random RANDOM = new Random();

    public String generate() {
        List<String> parts = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            parts.add(String.valueOf(RANDOM.nextInt(100)));
        }

        return parts.stream()
                .map(p -> "0".repeat(Math.max(0, 2 - p.length())) + p)
                .collect(Collectors.joining());
    }
}
