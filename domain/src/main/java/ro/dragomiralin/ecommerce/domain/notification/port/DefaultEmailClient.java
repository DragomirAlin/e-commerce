package ro.dragomiralin.ecommerce.domain.notification.port;


import ro.dragomiralin.ecommerce.domain.notification.objects.MailDO;

import java.util.List;

public interface DefaultEmailClient {

    void sendEmail(MailDO mailDO);

}
