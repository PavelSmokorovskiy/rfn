package retailcx.fn.service.impl;

import retailcx.fn.service.SubmitService;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DefaultSubmitService implements SubmitService {

    @Override
    public Integer post(HttpRequest request){
        return HttpClient.newHttpClient()
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::statusCode)
                .join();
    }
}
