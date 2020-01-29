package retailcx.fn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import retailcx.fn.service.SubmitService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsController {

    @Autowired
    private SubmitService submitService;

    @Value("${loyalty.endpoint}")
    private String loyaltyEndpoint;

    private Map<String, String> pack = new HashMap<>();

    @PostMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer productsInbound(@RequestBody Map<String, Object> parsed) throws IOException {

        pack.put("EndDate", "2020-01-21T04:13:13.851Z");
        pack.put("ExternalId", String.valueOf(parsed.get("code")));
        pack.put("MemberActivityTypeId", "string");
        pack.put("Name", String.valueOf(parsed.get("name")));
        pack.put("StartDate", "2020-01-21T04:13:13.851Z");

        return submitService.submit(loyaltyEndpoint, pack);
    }

}
