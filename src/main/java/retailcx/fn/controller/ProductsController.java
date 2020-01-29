package retailcx.fn.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import retailcx.fn.service.SubmitService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsController {

    SubmitService submitService;

    @Value("${loyalty.endpoint}")
    private String loyaltyEndpoint;

    @Value("${loyalty.authentication}")
    private String loyaltyAuthentication;

    private Map<String, String> pack = new HashMap<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer productsInbound(@RequestBody Map<String, Object> parsed) throws IOException {

        pack.put("EndDate", "2020-01-21T04:13:13.851Z");
        pack.put("ExternalId", String.valueOf(parsed.get("code")));
        pack.put("MemberActivityTypeId", "string");
        pack.put("Name", String.valueOf(parsed.get("name")));
        pack.put("StartDate", "2020-01-21T04:13:13.851Z");

        return post(URI.create(loyaltyEndpoint), pack);
    }

    Integer post(URI uri, Map<String, String> pack) throws IOException {
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(pack);

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("accept", "*/*")
                .header("Authorization", loyaltyAuthentication)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        return submitService.post(request);
    }
}
