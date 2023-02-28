package ro.dragomiralin.ecommerce.client.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class S3ClientManager {
    private final S3Configuration s3Configuration;

    public S3Client getS3Client() {
        AwsCredentialsProvider awsCredentialsProvider = StaticCredentialsProvider.create(
                AwsBasicCredentials.create(s3Configuration.getAccessKey(), s3Configuration.getSecretKey()));

        return S3Client.builder()
                .credentialsProvider(awsCredentialsProvider)
                .endpointOverride(URI.create(s3Configuration.getEndpoint()))
                .region(Region.of(s3Configuration.getRegion()))
                .build();
    }
}
