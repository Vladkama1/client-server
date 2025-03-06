package aston.lab.clientserver.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDto {

    @Schema(description = "ИД клиента", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "ИНН клиента", example = "6321323773")
    private String inn;

    @Schema(description = "ОГРН Клиента", example = "1136320021200")
    private String ogrn;

    @Schema(description = "Идентификатор собственности клиента", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID formOwnershipId;

    @Schema(description = "Полное наименование Клиента", example = "ИнструментТекстиль")
    private String fullNameClient;

    @Schema(description = "Сокращенное имя клиента", example = "ООО ИТекстиль")
    private String nameClient;

    @Schema(description = "Идентификатор видов экономической деятельности", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID okvedId;

    @Schema(description = "Идентификатор размера бизнеса у клиента", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID businessVolId;

    @Schema(description = "Номер расчетного счёта", example = "12345678901234567890")
    private String curAccId;

    @Schema(description = "Адрес", example = "Самарская область, г. Тольятти")
    private String addressLegal;

    @Schema(description = "Идентификатор представителя клиента", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID clientRepresentativId;

    @Schema(description = "Список номеров телефона клиента", example = "+12345678912, +73536402326")
    private List<String> telNumber;

    @Schema(description = "Идентификатор менеджера клиента", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID managerId;

    @Schema(description = "Доход", example = "77.03")
    private Float revenue;

    @Schema(description = "Дата создания", example = "2023-01-01T00:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Дата последнего обновления", example = "2023-01-01T00:00:00")
    private LocalDateTime updatedAt;

    // Additional fields as necessary
}
