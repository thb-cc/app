package com.thb.app;

import com.thb.app.service.S3Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@ActiveProfiles("test")
class S3WebIntegrationIT {

    /**
     * Wird von Spring Boot AUTOMATISCH bereitgestellt,
     * wenn @SpringBootTest mit WebEnvironment aktiv ist.
     */
    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Ersetzt den echten S3Service im ApplicationContext.
     * Kein AWS-Zugriff im Integrationstest!
     */
    @MockitoBean
    private S3Service s3Service;

    @Test
    void homepageShouldRenderImageAndCount() {

        // Arrange: Mock Service-Verhalten
        when(s3Service.getJpgCount()).thenReturn(5);
        when(s3Service.getRandomJpgAsBase64())
                .thenReturn("data:image/jpeg;base64,FAKE_BASE64_IMAGE");

        // Act: echter HTTP-Aufruf auf den Embedded Server
        ResponseEntity<String> response =
                restTemplate.getForEntity("/", String.class);

        // Assert: HTTP OK
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

        String body = response.getBody();
        assertThat(body).isNotNull();

        // HTML-Inhalte prüfen
        assertThat(body).contains("Zufälliges Bild aus S3");
        assertThat(body).contains("Anzahl der Bilder im Bucket");
        assertThat(body).contains("5");

        // IMG-Tag prüfen
        assertThat(body).contains("data:image/jpeg;base64,FAKE_BASE64_IMAGE");
    }
}
