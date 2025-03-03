package aston.lab.clientserver.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivationStatusRequest {

    @NotEmpty(message = "Некорректные параметры запроса")
    @JsonProperty("updatedClients")
    private List<@NotNull(message = "Некорректные параметры запроса") UUID> updatedClients;

    @JsonProperty("isActive")
    @NotNull(message = "isActive не может быть null")
    private Boolean isActive;

}
