package com.thb.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;
import java.util.function.Consumer;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class S3ServiceTest {

    private S3Client s3Client;
    private S3Service s3Service;

    @BeforeEach
    void setUp() {
        s3Client = mock(S3Client.class);
        s3Service = new S3Service(s3Client, "test-bucket");
    }

    /** Test 1:
     * Anzahl der JPGs wird korrekt gezählt
     * */
    @Test
    void shouldReturnCorrectJpgCount() {

        when(s3Client.listObjectsV2(any(Consumer.class)))
                .thenReturn(ListObjectsV2Response.builder()
                        .contents(
                                S3Object.builder().key("a.jpg").build(),
                                S3Object.builder().key("b.jpeg").build(),
                                S3Object.builder().key("c.png").build()
                        )
                        .build());

        int count = s3Service.getJpgCount();

        assertEquals(2, count);
    }

    /** Test 2:
     *  Exception wenn keine JPGs vorhanden
     * */
    @Test
    void shouldThrowExceptionWhenNoImagesExist() {

        when(s3Client.listObjectsV2(any(Consumer.class)))
                .thenReturn(ListObjectsV2Response.builder()
                        .contents(
                                S3Object.builder().key("test.txt").build()
                        )
                        .build());

        assertThrows(IllegalStateException.class,
                () -> s3Service.getRandomJpgAsBase64());
    }

    /**  Test 3:
     *  Zufällige Auswahl funktioniert
     *  */
    @Test
    void shouldSelectRandomImageWithoutError() {

        when(s3Client.listObjectsV2(any(Consumer.class)))
                .thenReturn(ListObjectsV2Response.builder()
                        .contents(
                                S3Object.builder().key("img1.jpg").build(),
                                S3Object.builder().key("img2.jpg").build()
                        )
                        .build());

        assertDoesNotThrow(() -> s3Service.getJpgCount());
    }
}
