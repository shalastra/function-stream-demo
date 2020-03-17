package dev.shalastra.functionstreamdemo;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Accessors(fluent = true)
@ConfigurationProperties(prefix = "app.product.tracker")
public class ProductTrackerProperties {
    private String productIds;
}
