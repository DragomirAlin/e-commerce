package ro.dragomiralin.ecommerce.domain.storage;

import ro.dragomiralin.ecommerce.domain.storage.objects.CustomFile;

import java.io.IOException;

public interface StorageClient {

    void uploadFile(CustomFile customFile);

    CustomFile downloadFile(String fileName) throws IOException;

    void deleteFile(String fileName);

}
