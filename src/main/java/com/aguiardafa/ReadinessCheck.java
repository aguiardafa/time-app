package com.aguiardafa;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Readiness
public class ReadinessCheck implements HealthCheck {

    @RestClient
    TimeApiService timeApiService;

    @Override
    public HealthCheckResponse call() {
        // Aqui você pode adicionar lógica para verificar se a aplicação está pronta para receber tráfego
        // Por exemplo, verificar se a conexão com o banco de dados está estabelecida
        if(timeApiService.getTime().contains("Fallback")) {
            // Se a API de tempo estiver em fallback, consideramos a aplicação não pronta
            return HealthCheckResponse.down("Readiness Check: Application is not ready");
        }
        return HealthCheckResponse.up("Readiness Check: Application is ready");
    }
}
