package ro.dragomiralin.ecommerce.client.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "ecommerce.payment.stripe")
public class StripeClientProperties {
    @JsonProperty("secret-key")
    private String secretKey;
    @JsonProperty("public-key")
    private String publicKey;
}
