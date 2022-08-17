package com.example.courierTracker.courierTracker;

import com.auth0.jwt.algorithms.Algorithm;

public class ConfigVariables {
    private static final String JWTSecretKey = "2192190euqw0wqwdjqwoidwfcnvbdfnwcfkewfhewofjqwpjqwfhqeuwofhqfhqfqwfdq" +
            "dqwjdqwjfqwf0qwjfqwjfdqw8qwjfdqwifdsawufghewufhqdfjqfuhgwqqifvu3i8cvuddh";
    private static final Algorithm JWTAlgorithm = Algorithm.HMAC256(JWTSecretKey.getBytes());

    public static Algorithm getJWTAlgorithm() {
        return JWTAlgorithm;
    }
}
