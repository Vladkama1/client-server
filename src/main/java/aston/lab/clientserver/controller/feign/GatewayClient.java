package aston.lab.clientserver.controller.feign;

import aston.lab.clientserver.controller.feign.fallback.GatewayClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gateway-server", fallback = GatewayClientFallback.class)
public interface GatewayClient {

    @GetMapping("/api/v1/gateway")
    boolean getHealthCheck(@RequestParam(name = "check", defaultValue = "false") Boolean check);

}
