package com.example.simbavirtualmoneytransfer.services.currency;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService{

    RestTemplate restTemplate = new RestTemplate();
    String url = "https://v6.exchangerate-api.com/v6/59f4ff8ac64847e11aa8ffee/latest/USD";

    @Override
    public String getCurrencies() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();

    }
}
