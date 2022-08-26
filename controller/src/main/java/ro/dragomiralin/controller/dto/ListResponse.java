package ro.dragomiralin.controller.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
public class ListResponse<T> {
    private List<T> data;
    private Long total;
    private Integer limit;
    private Long offset;

    public static <T> ListResponse<T> build(List<T> data, Long total, Integer limit, Long offset) {
        return new ListResponse<>(data, total, limit, offset);
    }

    public static <T> ListResponse<T> build(Page<T> page) {
        return new ListResponse<T>(page.getContent(), page.getTotalElements(), page.getTotalPages(), (long) page.getNumber());
    }
}