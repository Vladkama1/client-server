package aston.lab.clientserver.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ActivationStatusResponse {

    @JsonProperty("updatedClients")
    private List<ClientStatus> updatedClients;

    @Data
    @AllArgsConstructor
    @Builder
    public static class ClientStatus {

        @JsonProperty("clientId")
        private UUID clientId;

        @JsonProperty("isActive")
        private boolean isActive;

    }

}
