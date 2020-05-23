package com.openbanking.accounts.transactions.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TokenControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGenerateToken() throws Exception {
        final Map userMap = new HashMap<>();
        userMap.put("userName", "Lokesh");
        userMap.put("userId", "1234");
        userMap.put("role", "admin");
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/token"),
                HttpMethod.POST, new HttpEntity<Object>(userMap, headers), String.class);
        assertThat(response.getStatusCodeValue(), is(200));
        assertNotNull(response);
        assertNotNull(response.getBody());
    }

    private String createURLWithPort(final String uri) {
        return "http://localhost:" + port + uri;
    }
}
