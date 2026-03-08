package com.aguiardafa;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
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
    String getTime();

    default String getTimeFallback() {
        return "Fallback: " + java.time.LocalDateTime.now() + "\n";
    }
}
