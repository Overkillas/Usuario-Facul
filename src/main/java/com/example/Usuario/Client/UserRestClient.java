package com.example.Usuario.Client;

import com.example.Usuario.Entities.User;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Map;

@Component
public class UserRestClient {

    private final WebClient webClient;

    // Construtor, injetando o WebClient
    public UserRestClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://order-api-facul-production.up.railway.app/").build();
    }

    public String createOrder(Integer idUsuario, Integer idProduto, Double valorTotal) {
        Map<String, Object> pedidoData = Map.of(
                "idUsuario", idUsuario,
                "idProduto", idProduto,
                "valorTotal", valorTotal,
                "dataCompra", LocalDateTime.now()
        );

        return webClient.post()
                .bodyValue(pedidoData)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public Map<String, Object> getAllOrders() {
        return webClient.get()
                .uri("pedido/todos")
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    public User getUserById(Integer id) {
        return webClient.get()
                .uri("/User/{id}", id)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    // Método para adicionar um usuário
    public String addUser(User user) {
        ResponseEntity<String> response = webClient.post()
                .uri("/User")
                .bodyValue(user)
                .retrieve()
                .toEntity(String.class)
                .block();

        return response.getBody();
    }

    // Método para deletar um usuário por ID
    public String removeUser(Integer id) {
        return webClient.delete()
                .uri("/User/{id}", id)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
