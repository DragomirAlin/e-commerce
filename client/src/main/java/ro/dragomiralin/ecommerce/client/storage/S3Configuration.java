package ro.dragomiralin.ecommerce.client.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "ecommerce.storage.s3")
public class S3Configuration {
    private String region;
    private String endpoint;
    @JsonProperty("access-key")
    private String accessKey;
    @JsonProperty("secret-key")
    private String secretKey;
    @JsonProperty("bucket-name")
    private String bucketName;


}
