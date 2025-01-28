package aston.lab.clientserver.controller.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfigFeignClient {
    @Autowired
    public GatewayClient gatewayClient;

    @Autowired
    public ResourceClient resourceClient;

    @Autowired
    public AuthorizationClient authorizationClient;

    @Autowired
    public NotificationsClient notificationsClient;

    @Autowired
    public OrchestratorClient orchestratorClient;

}
