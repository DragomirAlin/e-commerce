package ro.dragomiralin.ecommerce.domain.storage.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CustomFile {
    private String name;
    private byte[] content;
}
