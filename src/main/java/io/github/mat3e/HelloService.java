package io.github.mat3e;

import java.util.Optional;

class HelloService {

    static final String FALLBACK_NAME = "world";

    String prepareGreeting(String name){
        // wycięte z servleta
        // Optional.ofNullable(req.getParameter(NAME_PARAM)).orElse("world");
        // i przerobione
        return "Hello " + Optional.ofNullable(name).orElse(FALLBACK_NAME) + "!";
    }
}
