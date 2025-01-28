package aston.lab.clientserver.controller;

import aston.lab.clientserver.controller.feign.ConfigFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HealthCheck {
    @Autowired
    private ConfigFeignClient feignClient;

    /***
     * Проверка доступа к сервису
     * @param check c true проверяет коннект с feign client других сервисов
     */
    @GetMapping("/api/v1/client")
    public boolean getHealthCheck(@RequestParam(defaultValue = "false") boolean check) {
        log.info("Получен запрос getHealthCheck Boolean: {}", check);
        return !check || getHealthCheck();

    }

    private boolean getHealthCheck() {
        try {
            feignClient.gatewayClient.getHealthCheck(false);
            feignClient.resourceClient.getHealthCheck(false);
            feignClient.authorizationClient.getHealthCheck(false);
            feignClient.notificationsClient.getHealthCheck(false);
            feignClient.orchestratorClient.getHealthCheck(false);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
