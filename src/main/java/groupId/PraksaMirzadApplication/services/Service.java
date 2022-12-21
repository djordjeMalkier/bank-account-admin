package groupId.PraksaMirzadApplication.services;

import groupId.PraksaMirzadApplication.model.AuthenticationRequest;
import groupId.PraksaMirzadApplication.dto.BankAccountDTO;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@org.springframework.stereotype.Service
public class Service {

    private WebClient webClient;

    public Service(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public String auth(AuthenticationRequest request) {
        return this.webClient
                .post()
                .uri("/auth/authenticate")
                .body(Mono.just(request), AuthenticationRequest.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();

    }

    public BankAccountDTO addBankAccount(BankAccountDTO bankAccountDTO, String token){
        return this.webClient
                .post()
                .uri("/users/addBankAccount")
                .headers(h -> h.setBearerAuth(token.substring(7)))
                .body(Mono.just(bankAccountDTO), BankAccountDTO.class)
                .retrieve()
                .bodyToMono(BankAccountDTO.class)
                .block();
    }

    public BankAccountDTO deleteBankAccount(String personalId, Integer bankAccountId, String token){
        return this.webClient
                .delete()
                .uri(uriBuilder -> uriBuilder
                        .path("/users/deleteBankAccount")
                        .queryParam("personalId", personalId)
                        .queryParam("bankAccountId", bankAccountId)
                        .build())
                .headers(h -> h.setBearerAuth(token.substring(7)))
                .retrieve()
                .bodyToMono(BankAccountDTO.class)
                .block();
    }
}
