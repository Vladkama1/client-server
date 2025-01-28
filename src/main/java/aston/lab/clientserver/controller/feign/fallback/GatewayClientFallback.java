package aston.lab.clientserver.controller.feign.fallback;

import aston.lab.clientserver.controller.exception.FeignException;
import aston.lab.clientserver.controller.feign.GatewayClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GatewayClientFallback implements GatewayClient {

    @Override
    public boolean getHealthCheck(Boolean check) {
        log.error("Ошибка запроса через Feign ResourceClient");
        throw new FeignException("Ошибка запроса через Feign GatewayClient");
    }

}
