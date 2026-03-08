package com.aguiardafa;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8081/time-api")
public interface TimeApiService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Timeout(2000L) // Configura um timeout de 2 segundos para respostas da API
    @Fallback(fallbackMethod = "getTimeFallback") // Especifica o fallback method a ser chamado em caso de falha
    @CircuitBreaker(
            requestVolumeThreshold = 4, // Número mínimo de chamadas para considerar o circuito
            failureRatio = .5, // Proporção de falhas para abrir o circuito
            delay = 5000L, // Tempo em milissegundos para manter o circuito aberto antes de tentar fechar novamente
            successThreshold = 2 // Número de chamadas bem-sucedidas para fechar o circuito
    )
    String getTime();

    default String getTimeFallback() {
        return "Fallback: " + java.time.LocalDateTime.now() + "\n";
    }
}
