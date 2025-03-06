package aston.lab.clientserver.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDto {

    @NotNull
    @Schema(description = "ИНН клиента", example = "6321323773")
    private String inn;

    @NotNull
    @Schema(description = "ОГРН Клиента", example = "1136320021200")
    private String ogrn;

    @NotNull
    @Schema(description = "Идентификатор собственности клиента", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID formOwnershipId;

    @NotNull
    @Size(max = 255)
    @Schema(description = "Полное наименование Клиента", example = "ИнструментТекстиль")
    private String fullNameClient;

    @NotNull
    @Size(max = 255)
    @Schema(description = "Сокращенное имя клиента", example = "ООО ИТекстиль")
    private String nameClient;

    @NotNull
    @Schema(description = "Идентификатор видов экономической деятельности", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID okvedId;

    @NotNull
    @Schema(description = "Идентификатор размера бизнеса у клиента", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID businessVolId;

    @Schema(description = "Номер расчетного счёта", example = "12345678901234567890")
    private String curAccId;

    @NotNull
    @Schema(description = "Адрес", example = "Самарская область, г. Тольятти")
    private String addressLegal;

    @NotNull
    @Schema(description = "Идентификатор представителя клиента", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID clientRepresentativId;

    @NotNull
    @Schema(description = "Список номеров телефона клиента", example = "+12345678912, +73536402326")
    private List<String> telNumber;

    @NotNull
    @Schema(description = "Идентификатор менеджера клиента", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID managerId;

    @Schema(description = "Доход", example = "77.03")
    private Float revenue;

    // Additional fields as necessary
}
