package com.example.Usuario.Client;

import com.example.Usuario.Entities.User;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.ResponseEntity;

@Component
public class UserRestClient {

    private final WebClient webClient;

    // Construtor, injetando o WebClient
    public UserRestClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://successful-gentleness-production.up.railway.app").build();
    }

    // Método para obter o usuário por ID
    public User getUserById(Integer id) {
        return webClient.get()
                .uri("/User/{id}", id) // Substitui o {id} pela variável id
                .retrieve()  // Realiza a requisição
                .bodyToMono(User.class)  // Converte o corpo da resposta para o tipo User
                .block();  // Bloqueia até que a resposta seja recebida (pode ser assíncrono também)
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