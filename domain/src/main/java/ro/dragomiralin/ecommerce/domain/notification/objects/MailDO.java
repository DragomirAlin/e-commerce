package ro.dragomiralin.ecommerce.domain.notification.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class MailDO {
    private String emailTo;
    private String subject;
    private String body;
    private List<Byte> attachments;
}
