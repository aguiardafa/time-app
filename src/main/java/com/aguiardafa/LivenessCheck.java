package com.aguiardafa;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
public class LivenessCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        // Aqui você pode adicionar lógica para verificar se a aplicação está viva
        // Por exemplo, verificar se um recurso essencial está disponível
        boolean isAlive = true; // Simulando que a aplicação está viva

        if (isAlive) {
            return HealthCheckResponse.up("Liveness Check: Application is alive");
        } else {
            return HealthCheckResponse.down("Liveness Check: Application is not alive");
        }
    }
}
