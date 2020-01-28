package retailcx.fn.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class Inbound {

    URI ENDPOINT = URI.create("http://loyalty-api-aws.eu-central-1.elasticbeanstalk.com/v1/api/Loyalties/2/Products");
    String AUTH = "Poiu1234Key";
    Map<String, String> pack = new HashMap<>();

    @PostMapping(value = "/test", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, String> postTest(@RequestBody Map<String, Object> parsed) throws IOException {

        pack.put("EndDate", "2020-01-21T04:13:13.851Z");
        pack.put("ExternalId", String.valueOf(parsed.get("code")));
        pack.put("MemberActivityTypeId", "string");
        pack.put("Name", String.valueOf(parsed.get("name")));
        pack.put("StartDate", "2020-01-21T04:13:13.851Z");

        Integer responseCode = post(ENDPOINT, pack);
        System.out.println(responseCode);

        return pack;
    }

    Integer post(URI uri, Map<String, String> map) throws IOException {
        String requestBody = new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(map);

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("accept", "*/*")
                .header("Authorization", AUTH)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        return HttpClient.newHttpClient()
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::statusCode)
                .join();
    }

}
