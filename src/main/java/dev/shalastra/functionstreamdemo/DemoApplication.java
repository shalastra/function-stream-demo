package dev.shalastra.functionstreamdemo;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableConfigurationProperties(ProductTrackerProperties.class)
public class DemoApplication {

    private final ProductTrackerProperties productTrackerProperties;

    public DemoApplication(ProductTrackerProperties productTrackerProperties) {
        this.productTrackerProperties = productTrackerProperties;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public Function<KStream<Object, Product>, KStream<Integer, ProductStatus>> process() {
        return input -> input
                .filter((key, product) -> productIds().contains(product.getId()))
                .map((key, value) -> new KeyValue<>(value, value))
                .groupByKey(Grouped.with(new JsonSerde<>(Product.class), new JsonSerde<>(Product.class)))
                .windowedBy(TimeWindows.of(Duration.ofSeconds(60)))
                .count(Materialized.as("product-counts"))
                .toStream()
                .map((key, value) -> new KeyValue<>(key.key().getId(), new ProductStatus(key.key().getId(),
                        value, Instant.ofEpochMilli(key.window().start()).atZone(ZoneId.systemDefault()).toLocalTime(),
                        Instant.ofEpochMilli(key.window().end()).atZone(ZoneId.systemDefault()).toLocalTime())));
    }

    private Set<Integer> productIds() {
        return StringUtils.commaDelimitedListToSet(productTrackerProperties.getProductIds())
                .stream().map(Integer::parseInt).collect(Collectors.toSet());
    }
}
