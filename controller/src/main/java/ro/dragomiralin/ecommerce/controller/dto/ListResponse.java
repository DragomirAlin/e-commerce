package ro.dragomiralin.ecommerce.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListResponse<T> {
    private List<T> data;
    private long total;
    private Integer limit;
    private long offset;

    public static <T> ListResponse<T> build(List<T> data, long total, Integer limit, long offset) {
        return new ListResponse<>(data, total, limit, offset);
    }

    public static <T> ListResponse<T> build(PageDTO<T> page) {
        return new ListResponse<T>(page.getContent(), page.getTotalElements(), page.getTotalPages(), (long) page.getNumber());
    }
}