package org.dev.devops.service;

import org.dev.devops.models.MessageRequest;
import org.dev.devops.models.MessageResponse;
import org.dev.devops.security.jwt.JwtProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DevOpsServiceTest {

    @Mock
    private JwtProvider jwtProvider;

    @InjectMocks
    private DevOpsService devOpsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetResponse() {
        MessageRequest request = new MessageRequest();
        request.setTo("World");

        Mono<MessageResponse> responseMono = devOpsService.getResponse(request);
        MessageResponse response = responseMono.block();

        assert response != null;
        assertEquals("Hello World, your message will be send", response.getMessage());
    }

    @Test
    public void testGenerateToken() {
        String expectedToken = "mockedToken";
        when(jwtProvider.generateToken()).thenReturn(expectedToken);

        Mono<String> tokenMono = devOpsService.generateToken();
        String token = tokenMono.block();

        assertEquals(expectedToken, token);
    }

}
