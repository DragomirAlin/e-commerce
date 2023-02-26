package ro.dragomiralin.ecommerce.domain.notification.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.notification.NotificationService;
import ro.dragomiralin.ecommerce.domain.notification.objects.MailDO;
import ro.dragomiralin.ecommerce.domain.notification.port.DefaultEmailClient;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final DefaultEmailClient defaultEmailClient;

    @Override
    public void sendMail(MailDO mailDO) {
        defaultEmailClient.sendEmail(mailDO);
    }
}
