package dev.shalastra.functionstreamdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.function.Function;

@RestController
class HelloController {

    /**
     * curl http://localhost:8080/names -H "Content-type: text/plain"  -d "Szymon"
     */

    @Bean
    public Function<String, String> names() {
        return name -> "Hello " + Optional.ofNullable(name).orElseGet(() -> "World");
    }
}
