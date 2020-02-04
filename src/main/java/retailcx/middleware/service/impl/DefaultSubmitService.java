package retailcx.middleware.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Map;

@Service
public class DefaultSubmitService implements SubmitService {

    private Logger logger = LoggerFactory.getLogger(DefaultSubmitService.class);

    @Value("${loyalty.authentication}")
    private String loyaltyAuthentication;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void submit(String uri, Map<String, String> pack) throws JsonProcessingException {

            postHttpClient(createRequest(uri, pack));
    }

    private HttpRequest createRequest(String uri, Map<String, String> pack) throws JsonProcessingException {

        return HttpRequest.newBuilder(URI.create(uri))
                .header("accept", "*/*")
                .header("Authorization", loyaltyAuthentication)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(pack)))
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
