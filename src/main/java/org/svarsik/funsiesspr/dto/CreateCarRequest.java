package org.svarsik.funsiesspr.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateCarRequest(

        @NotBlank
        String brand,

        @NotBlank
        String model,

        @Min(1886)
        @Max(2100)
        int productionYear
) {}