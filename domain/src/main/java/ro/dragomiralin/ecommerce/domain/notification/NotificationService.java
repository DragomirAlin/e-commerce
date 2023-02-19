package ro.dragomiralin.ecommerce.domain.notification;


import ro.dragomiralin.ecommerce.domain.notification.objects.MailDO;

public interface NotificationService {

    void sendMail(MailDO mailDO);
}
