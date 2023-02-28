package ro.dragomiralin.ecommerce.client.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.storage.StorageClient;
import ro.dragomiralin.ecommerce.domain.storage.objects.CustomFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.NoSuchBucketException;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageClientImpl implements StorageClient {
    private final S3Configuration s3Configuration;
    private final S3ClientManager s3ClientManager;

    @Override
    public void uploadFile(CustomFile customFile) {
        createBucketIfNotExists(s3Configuration.getBucketName());

        s3ClientManager.getS3Client().putObject(
                builder -> builder
                        .bucket(s3Configuration.getBucketName())
                        .key(customFile.getName())
                        .build(),
                RequestBody.fromBytes(customFile.getContent()));
        log.info("File {} uploaded to bucket {}", customFile.getName(), s3Configuration.getBucketName());
    }

    @Override
    public CustomFile downloadFile(String fileName) throws IOException {
        ResponseInputStream<GetObjectResponse> objectResponse = s3ClientManager.getS3Client().getObject(
                builder -> builder
                        .bucket(s3Configuration.getBucketName())
                        .key(fileName)
                        .build());

        return CustomFile.builder()
                .content(objectResponse.readAllBytes())
                .name(fileName)
                .build();
    }

    @Override
    public void deleteFile(String fileName) {

    }

    private void createBucketIfNotExists(String bucketName) {
        try {
            s3ClientManager.getS3Client().headBucket(HeadBucketRequest.builder()
                    .bucket(bucketName)
                    .build());
        } catch (NoSuchBucketException e) {
            log.error("Bucket {} does not exist. Creating it now.", bucketName);
            s3ClientManager.getS3Client().createBucket(CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .build());
        }
    }
}
