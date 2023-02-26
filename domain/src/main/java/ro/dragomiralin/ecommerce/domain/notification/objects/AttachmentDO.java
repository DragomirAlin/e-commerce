package ro.dragomiralin.ecommerce.domain.notification.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AttachmentDO {
    private String name;
    private byte[] content;

}

