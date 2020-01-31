package retailcx.fn.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import retailcx.fn.service.SubmitService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsController {

    @Autowired
    private SubmitService submitService;

    @Value("${loyalty.endpoint}" + "${loyalty.products}")
    private String loyaltyEndpoint;

    @PostMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void productsInbound(@RequestBody Map<String, Object> parsed) throws JsonProcessingException {

        Map<String, String> pack = new HashMap<>();
        pack.put("EndDate", "2020-01-21T04:13:13.851Z");
        pack.put("ExternalId", String.valueOf(parsed.get("code")));
        pack.put("MemberActivityTypeId", "string");
        pack.put("Name", String.valueOf(parsed.get("name")));
        pack.put("StartDate", "2020-01-21T04:13:13.851Z");

        submitService.submit(loyaltyEndpoint, pack);
    }
}
