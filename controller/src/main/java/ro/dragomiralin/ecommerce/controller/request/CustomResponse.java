package ro.dragomiralin.ecommerce.controller.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;
import ro.dragomiralin.ecommerce.controller.dto.ListResponse;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse<T> {
    private T data;
    private Object errors;
    private Redirect redirect;
    private Paging paging;

    public static <T> CustomResponse<List<T>> list(ListResponse<T> listResponse) {
        Assert.notNull(listResponse.getData(), "List data must not be null.");
        CustomResponse<List<T>> customHttpResponse = new CustomResponse<>();
        customHttpResponse.setData(listResponse.getData());
        customHttpResponse.setPaging(
                Paging.builder()
                        .limit(listResponse.getLimit() == null ? 50 : listResponse.getLimit())
                        .offset(listResponse.getOffset() == null ? 0L : listResponse.getOffset())
                        .total(listResponse.getTotal() == null ? listResponse.getData().size() : listResponse
                                .getTotal())
                        .build()
        );

        return customHttpResponse;
    }

    public static CustomResponse<Void> empty(){
        return CustomResponse.<Void>builder().build();
    }

    public static <T> CustomResponse<T> redirect(String url) {
        return CustomResponse.<T>builder()
                .redirect(Redirect.builder().url(url).build())
                .build();
    }

    public static <T> CustomResponse<T> single(T data) {
        CustomResponse<T> customHttpResponse = new CustomResponse<>();
        customHttpResponse.setData(data);
        return customHttpResponse;
    }

    public static CustomResponse error(Object errorObject) {
        CustomResponse customHttpResponse = new CustomResponse();
        customHttpResponse.setErrors(errorObject);
        return customHttpResponse;
    }

    @Data
    @Builder
    public static class Paging {
        private long total;
        private long offset;
        private int limit;
    }

    @Data
    @Builder
    public static class Redirect {
        private String url;
    }
}
