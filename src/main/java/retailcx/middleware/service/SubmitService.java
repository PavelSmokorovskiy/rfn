package retailcx.middleware.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface SubmitService {

    void submit(String uri, Map<String, String> pack) throws JsonProcessingException;
}
