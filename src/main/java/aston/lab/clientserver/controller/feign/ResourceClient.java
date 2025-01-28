package aston.lab.clientserver.controller.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "resource-service")
public interface ResourceClient {

    @GetMapping("/api/v1/resource")
    boolean getHealthCheck(@RequestParam(name = "check", defaultValue = "false") Boolean check);

}
