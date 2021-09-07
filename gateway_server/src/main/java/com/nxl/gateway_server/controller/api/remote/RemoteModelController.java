package com.nxl.gateway_server.controller.api.remote;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;

@RestController
@RequestMapping(value = "/model")
public class RemoteModelController {

    @Value("${extra.param.model-server}")
    private String modelServerUrl;


    @GetMapping("/cabb")
    public String remoteExchange(
            @RequestParam("avg_temp") String avgTemp,
            @RequestParam("min_temp") String minTemp,
            @RequestParam("max_temp") String maxTemp,
            @RequestParam("rain_fall") String rainFall
    )
    {
        String url = modelServerUrl + "/cabb";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("avg_temp",avgTemp)
                .queryParam("min_temp",minTemp)
                .queryParam("max_temp",maxTemp)
                .queryParam("rain_fall",rainFall)
                .build(false);

        ResponseEntity response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<String>(headers), String.class);
        return response.getBody().toString();
    }

}