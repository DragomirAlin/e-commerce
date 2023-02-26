package ro.dragomiralin.ecommerce.client.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.notification.objects.MailDO;
import ro.dragomiralin.ecommerce.domain.notification.port.DefaultEmailClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultEmailClientImpl implements DefaultEmailClient {


    @Override
    public void sendEmail(MailDO mailDO) {

    }
}
