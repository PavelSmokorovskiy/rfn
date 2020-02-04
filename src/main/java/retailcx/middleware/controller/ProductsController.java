package retailcx.middleware.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import retailcx.middleware.dto.ProductDto;
import retailcx.middleware.service.SubmitService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsController {

    private Logger logger = LoggerFactory.getLogger(ProductsController.class);

    @Autowired
    private SubmitService submitService;

    @Value("${loyalty.endpoint}" + "${loyalty.products}")
    private String loyaltyEndpoint;

    @PostMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void productsInbound(@RequestBody @Valid ProductDto dto) {

        System.out.println("dto.toString() = " + dto.toString());



//        Map<String, String> pack = new HashMap<>();
//        pack.put("EndDate", "2020-01-21T04:13:13.851Z");
//        pack.put("ExternalId", String.valueOf(parsed.get("code")));
//        pack.put("MemberActivityTypeId", "string");
//        pack.put("Name", String.valueOf(parsed.get("name")));
//        pack.put("StartDate", "2020-01-21T04:13:13.851Z");
//
//        logger.info("Json created {}", pack);
//        submitService.submit(loyaltyEndpoint, pack);
    }
}
