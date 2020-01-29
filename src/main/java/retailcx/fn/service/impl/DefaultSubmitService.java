package retailcx.fn.service.impl;

import org.springframework.stereotype.Service;
import retailcx.fn.service.SubmitService;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class DefaultSubmitService implements SubmitService {

    @Override
    public Integer submit(HttpRequest request){
        return HttpClient.newHttpClient()
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::statusCode)
                .join();
    }
}
