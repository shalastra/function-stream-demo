package dev.shalastra.functionstreamdemo;

import java.util.Optional;
import java.util.function.Function;

class Names implements Function<String, String> {

    /**
     * curl http://localhost:8080/names -H "Content-type: text/plain"  -d "Szymon"
     */

    @Override
    public String apply(String name) {
        return "Hello " + Optional.ofNullable(name).orElseGet(() -> "World");
    }
}
