package dev.shalastra.functionstreamdemo;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/names")
@RestController
class HelloController {

    @GetMapping
    public String hello(@RequestParam(defaultValue = "World") String name) {
        return "Hello " + name;
    }
}
