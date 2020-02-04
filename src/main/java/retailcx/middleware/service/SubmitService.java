package retailcx.middleware.service;

import net.minidev.json.JSONObject;

public interface SubmitService {

    void submit(String uri, JSONObject json);
}
