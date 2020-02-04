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

    @Value("${loyalty.endpoint}" + "${loyalty.product}")
    private String productEndpoint;

    @Value("${loyalty.endpoint}" + "${loyalty.category}")
    private String categoryEndpoint;

    @PostMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void productsInbound(@RequestBody @Valid ProductDto dto) throws JsonProcessingException {

        logger.info("Inbound product: {}", dto);
        submitService.submit(productEndpoint, dto.toJSON());
        for(var category: dto.getSupercategories()){
            submitService.submit(productEndpoint, category.toJSON());
        }
    }
}
