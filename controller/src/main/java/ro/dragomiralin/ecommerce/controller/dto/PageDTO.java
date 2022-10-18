package ro.dragomiralin.ecommerce.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PageDTO<T> {
    private int number;
    private int size;
    private int totalPages;
    private Long totalElements;
    private List<T> content;
}

