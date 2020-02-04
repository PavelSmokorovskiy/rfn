package retailcx.middleware.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import retailcx.middleware.service.SubmitService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class DefaultSubmitService implements SubmitService {

    private Logger logger = LoggerFactory.getLogger(DefaultSubmitService.class);

    @Value("${loyalty.authentication}")
    private String loyaltyAuthentication;

    @Override
    public void submit(String uri, JSONObject json) {

        postHttpClient(createRequest(uri, json));
    }

    private HttpRequest createRequest(String uri, JSONObject json) {

        return HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("accept", "*/*")
                .header("Authorization", loyaltyAuthentication)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(json.toJSONString()))
                .build();
    }

    private void postHttpClient(HttpRequest request) {
        Integer statusCode = HttpClient.newHttpClient()
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::statusCode)
                .join();
        logger.info("Data sent. Returned status code: {}", statusCode);
    }
}
