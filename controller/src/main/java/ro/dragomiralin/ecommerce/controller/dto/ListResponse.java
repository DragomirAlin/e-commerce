package ro.dragomiralin.ecommerce.controller.dto;

import lombok.Builder;
import java.util.List;

@Builder
public record ListResponse<T>(
        List<T> data,
        long total,
        Integer limit,
        long offset) {

    public static <T> ListResponse<T> build(List<T> data, long total, Integer limit, long offset) {
        return new ListResponse<>(data, total, limit, offset);
    }

    public static <T> ListResponse<T> build(PageDTO<T> page) {
        return new ListResponse<T>(page.content(), page.totalElements(), page.totalPages(), (long) page.number());
    }
}