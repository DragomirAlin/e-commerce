package ro.dragomiralin.ecommerce.controller.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record PageDTO<T>(
        int number,
        int size,
        int totalPages,
        long totalElements,
        List<T> content) {
}

