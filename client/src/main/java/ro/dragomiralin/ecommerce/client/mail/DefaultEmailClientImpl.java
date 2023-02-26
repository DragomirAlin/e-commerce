package ro.dragomiralin.ecommerce.client.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.notification.objects.AttachmentDO;
import ro.dragomiralin.ecommerce.domain.notification.objects.MailDO;
import ro.dragomiralin.ecommerce.domain.notification.port.DefaultEmailClient;

import jakarta.mail.internet.MimeMessage;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultEmailClientImpl implements DefaultEmailClient {
    @Value("${ecommerce.mail.from}")
    private String from;

    private final JavaMailSender emailSender;

    @Override
    public void sendEmail(MailDO mailDO) {
        try {
            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(mailDO.getEmailTo());
            helper.setSubject(mailDO.getSubject());
            helper.setText(mailDO.getBody(), true);

            if (Objects.nonNull(mailDO.getAttachments()) && mailDO.getAttachments().size() > 0) {
                List<AttachmentDO> attachments = mailDO.getAttachments();
                for (AttachmentDO attachment : attachments) {
                    ByteArrayResource resource = new ByteArrayResource(attachment.getContent());
                    helper.addAttachment(attachment.getName(), resource);
                }
            }

            emailSender.send(message);
        } catch (Exception e) {
            log.error("Error while sending email", e);
        }
    }
}
