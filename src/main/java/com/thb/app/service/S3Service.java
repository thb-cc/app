package com.thb.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.util.Base64;
import java.util.List;
import java.util.Random;

@Service
public class S3Service {

    private final S3Client s3Client;
    private final String bucketName;
    private final Random random = new Random();

    public S3Service(S3Client s3Client,
                     @Value("${aws.s3.bucket-name}") String bucketName) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    /** Alle JPG/JPEG-Dateien im Bucket */
    private List<String> getAllJpgKeys() {
        return s3Client.listObjectsV2(b -> b.bucket(bucketName))
                .contents()
                .stream()
                .map(o -> o.key())
                .filter(k ->
                        k.toLowerCase().endsWith(".jpg") ||
                                k.toLowerCase().endsWith(".jpeg"))
                .toList();
    }

    /** Anzahl der JPGs */
    public int getJpgCount() {
        return getAllJpgKeys().size();
    }

    /** Zufälliges JPG als Base64 */
    public String getRandomJpgAsBase64() {

        List<String> keys = getAllJpgKeys();

        if (keys.isEmpty()) {
            throw new IllegalStateException("Keine JPG-Dateien im Bucket");
        }

        int randomIndex = random.nextInt(keys.size());
        String randomKey = keys.get(randomIndex);

        ResponseInputStream<GetObjectResponse> object =
                s3Client.getObject(b -> b.bucket(bucketName).key(randomKey));

        try {
            byte[] bytes = object.readAllBytes();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Lesen des Bildes", e);
        }
    }
}
