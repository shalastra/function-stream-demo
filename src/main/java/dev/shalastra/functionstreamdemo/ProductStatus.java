package dev.shalastra.functionstreamdemo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class ProductStatus {

    private Integer id;
    private long count;
    private LocalTime windowStart;
    private LocalTime windowEnd;
}
