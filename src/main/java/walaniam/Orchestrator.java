package walaniam;

import java.util.concurrent.TimeoutException;

public class Orchestrator {

    private final Service_A serviceA;
    private final Service_B serviceB;
    private final Service_C serviceC;

    public Orchestrator(Service_A serviceA, Service_B serviceB, Service_C serviceC) {
        this.serviceA = serviceA;
        this.serviceB = serviceB;
        this.serviceC = serviceC;
    }

    /**
     * This method calls three underlying services to fetch the responses. It should return within 500ms, otherwise
     * there's no point in waiting for the response and {@link TimeoutException} should be thrown.
     *
     * OrchestratorResponse should contain at least two internal responses but more is better.
     * Eg. Resource_A + Resource_B or Resource_A + Resource_C or Resource_B + Resource_C is a minimum.
     *
     * @param request
     * @return
     */
    OrchestratorResponse queryServices(OrchestratorRequest request) throws TimeoutException {

        OrchestratorResponse response = null;

        // TODO implement here

        return response;
    }

    static class OrchestratorRequest {
        final String serviceAQuery;
        final String serviceBQuery;
        final String serviceCQuery;

        public OrchestratorRequest(String serviceAQuery, String serviceBQuery, String serviceCQuery) {
            this.serviceAQuery = serviceAQuery;
            this.serviceBQuery = serviceBQuery;
            this.serviceCQuery = serviceCQuery;
        }
    }

    static class OrchestratorResponse {
        Resource_A resourceA;
        Resource_B resourceB;
        Resource_C resourceC;
    }

    static class Resource_A {
    }

    static class Resource_B {
    }

    static class Resource_C {
    }

    interface Service_A {
        Resource_A fetch(String query) throws InterruptedException;
    }

    interface Service_B {
        Resource_B fetch(String query) throws InterruptedException;
    }

    interface Service_C {
        Resource_C fetch(String query) throws InterruptedException;
    }
}
