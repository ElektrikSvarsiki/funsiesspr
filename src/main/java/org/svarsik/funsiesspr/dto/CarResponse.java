package org.svarsik.funsiesspr.dto;

public record CarResponse(
        Long id,
        String brand,
        String model,
        int year
) {}
