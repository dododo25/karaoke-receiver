package com.dododo.receiver.holder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.Map;

public class TokensHolder {

    public static final HexFormat FORMAT = HexFormat.of();

    private final Map<String, String> tokenMap;

    private final MessageDigest digest;

    public TokensHolder() throws NoSuchAlgorithmException {
        this.tokenMap = new HashMap<>();
        this.digest = MessageDigest.getInstance("SHA-256");
    }

    public String generateAndSave(String code) {
        String generated = prepareHex(digest.digest(code.getBytes()));
        tokenMap.put(code, generated);
        return generated;
    }

    public String get(String code) {
        return tokenMap.getOrDefault(code, null);
    }

    private String prepareHex(byte[] arr) {
        StringBuilder builder = new StringBuilder();

        for (byte b : arr) {
            builder.append(FORMAT.toHexDigits(b));
        }

        return builder.toString();
    }
}
