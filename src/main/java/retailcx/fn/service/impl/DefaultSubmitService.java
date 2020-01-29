package retailcx.fn.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import retailcx.fn.service.SubmitService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Service
public class DefaultSubmitService implements SubmitService {

    @Value("${loyalty.authentication}")
    private String loyaltyAuthentication;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Integer submit(String uri, Map<String, String> pack) {

        try {
            return postHttpClient(createRequest(uri, pack));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public HttpRequest createRequest(String uri, Map<String, String> pack) throws JsonProcessingException {

        return HttpRequest.newBuilder(URI.create(uri))
                .header("accept", "*/*")
                .header("Authorization", loyaltyAuthentication)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(pack)))
                .build();
    }

    public Integer postHttpClient(HttpRequest request) {
        return HttpClient.newHttpClient()
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::statusCode)
                .join();
    }
}
