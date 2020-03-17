package dev.shalastra.functionstreamdemo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@AllArgsConstructor
public class Product {

    @Getter
    private final Integer id;
}
