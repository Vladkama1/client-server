package aston.lab.clientserver.controller.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "orchestrator-server")
public interface OrchestratorClient {

    @GetMapping("/api/v1/orchestrator")
    boolean getHealthCheck(@RequestParam(name = "check", defaultValue = "false") Boolean check);

}
