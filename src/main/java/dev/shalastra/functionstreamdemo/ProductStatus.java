package dev.shalastra.functionstreamdemo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.LocalTime;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public class ProductStatus {

    private final Integer id;
    private final long count;
    private final LocalTime windowStart;
    private final LocalTime windowEnd;
}
