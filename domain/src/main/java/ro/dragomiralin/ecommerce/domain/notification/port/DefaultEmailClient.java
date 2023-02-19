package ro.dragomiralin.ecommerce.domain.notification.port;


import java.util.List;

public interface DefaultEmailClient {

    void sendEmail(String to, String subject, String body);

    void sendEmail(String to, String subject, String body, List<Byte> attachments);
}
