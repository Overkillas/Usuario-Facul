package com.example.Usuario.Client;

import com.example.Usuario.Entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Component
public class OrderClient {

    private final WebClient webClient;

    public OrderClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://order-api-facul-production.up.railway.app").build();
    }

    public List<Map<String, Object>> getAllOrders() {
        return webClient.get()
                        .uri("/pedido/todos")
                        .retrieve()
                        .bodyToMono(List.class)
                        .block();
    }

    // public List<Map<String, Object>> getAllOrdersByClient(String clientId) {}
    public String addOrder(Map<String, Object> order){
        ResponseEntity<Map<String, Object>> response = webClient.post()
                .uri("/pedido")
                .bodyValue(order)
                .retrieve()
                .bodyToMono(ResponseEntity.class)
                .block();
        return response.getBody().toString();
    }

}