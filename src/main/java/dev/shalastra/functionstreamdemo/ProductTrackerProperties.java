package dev.shalastra.functionstreamdemo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.product.tracker")
public class ProductTrackerProperties {
    private String productIds;
}
