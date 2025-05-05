package com.quanlydiemsinhvien.qldsv.components;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class VerificationCodeStore {
    private final Map<String, Integer> verificationCodes = new ConcurrentHashMap<>();

    public void saveCode(String email, int code) {
        verificationCodes.put(email, code);
    }

    public Integer getCode(String email) {
        return verificationCodes.get(email);
    }

    public void removeCode(String email) {
        verificationCodes.remove(email);
    }
}