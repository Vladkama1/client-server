package aston.lab.clientserver.dto.responsedto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientFindByInnAndOgrnResponseDto {

    @Schema(description = "Полное наименование Клиента", example = "ИнструментТекстиль")
    private String fullNameClient;

    @Schema(description = "ИНН клиента", example = "6321323773")
    private Long inn;

    @Schema(description = "ОГРН Клиента", example = "1136320021200")
    private Long ogrn;

    @Schema(description = "Сокращенное имя клиента", example = "ООО ИТекстиль")
    private String nameClient;

    @Schema(description = "Идентификатор собственности клиента", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID formOwnershipId;

    @Schema(description = "Идентификатор видов экономической деятельности", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID okvedId;

    @Schema(description = "Идентификатор размера бизнеса у клиента", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID businessVolId;

    @Schema(description = "Адрес", example = "Самарская область, г. Тольятти")
    private String addressLegal;

    @Schema(description = "Идентификатор представителя клиента", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID clientRepresentativId;

    @Schema(description = "Доход", example = "77.03")
    private Float revenue;

    @Schema(description = "Номер расчетного счёта", example = "12345678901234567890")
    private String curAccId;

    @Schema(description = "Идентификатор менеджера клиента", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID managerId;

    @Schema(description = "Список номеров телефона клиента", example = "+12345678912, +73536402326")
    private List<String> telNumber;
    @Schema(description = "Статус клиента", example = "true")
    private Boolean isActive;
}