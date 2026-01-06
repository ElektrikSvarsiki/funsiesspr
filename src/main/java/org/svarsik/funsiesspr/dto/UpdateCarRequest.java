package org.svarsik.funsiesspr.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCarRequest(
        @NotBlank
        String brand,

        @NotBlank
        String model,

        @NotNull
        Integer year
) {}