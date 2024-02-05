package walaniam;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.awaitility.Awaitility.await;

class OrchestratorTest {

    @Test
    void shouldGetAtLeastTwoResponsesIn500ms() {

        final Orchestrator orchestrator = buildOrchestrator(200, 300, 400);

        Orchestrator.OrchestratorRequest request = new Orchestrator.OrchestratorRequest("a", "b", "c");

        await()
            .atMost(500, TimeUnit.MILLISECONDS)
            .until(() -> {
                Orchestrator.OrchestratorResponse response = orchestrator.queryServices(request);
                int notNullCount = 0;
                if (response != null) {
                    if (response.resourceA != null) {
                        notNullCount++;
                    }
                    if (response.resourceB != null) {
                        notNullCount++;
                    }
                    if (response.resourceC != null) {
                        notNullCount++;
                    }
                }
                return notNullCount >= 2;
            });
    }

    private Orchestrator buildOrchestrator(int delayServiceA, int delayServiceB, int delayServiceC) {
        return new Orchestrator(
            query -> {
                Thread.sleep(delayServiceA);
                return new Orchestrator.Resource_A();
            },
            query -> {
                Thread.sleep(delayServiceB);
                return new Orchestrator.Resource_B();
            },
            query -> {
                Thread.sleep(delayServiceC);
                return new Orchestrator.Resource_C();
            }
        );
    }
}