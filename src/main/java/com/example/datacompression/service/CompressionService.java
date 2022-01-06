package com.example.datacompression.service;

import com.example.datacompression.model.Result;
import com.example.datacompression.util.Input;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;

@Service
public class CompressionService {
    private RestTemplate restTemplate;
    private final String apiKey = "API_KEY";
    private final String type = "objects";
    private final Integer maxLabels = 20;
    private final Integer minConfidence = 80;
    private final String url = "https://imagerecognize.com/api/v2/";

    public CompressionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Result recognize(String path) throws IOException{

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("apikey", apiKey);
        body.add("type", type);
        body.add("file", Input.getTestFile(path));
        body.add("max_labels", maxLabels);
        body.add("min_confidence", minConfidence);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        String response = restTemplate.postForObject(url, requestEntity, String.class);

        Gson g = new Gson();

        return g.fromJson(response, Result.class);
    }
}
