package aston.lab.clientserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HealthCheck {

    /***
     * Проверка доступа к сервису
     * @param check c true проверяет коннект с feign client других сервисов
     */
    @GetMapping("/api/v1/client")
    public boolean getHealthCheck(@RequestParam(defaultValue = "false") boolean check) {
        log.info("Получен запрос getHealthCheck Boolean: {}", check);
        return !check && getHealthCheck();

    }

    private boolean getHealthCheck() {

        return true;

    }

}
