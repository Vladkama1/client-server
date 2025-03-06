package aston.lab.clientserver.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormOwnershipDto {
    @Schema(description = "Идентификатор собственности клиента", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;
    @Schema(description = "Полное наименование формы собственности", example = "Общество с ограниченной ответственностью")
    private String fullName;
    @Schema(description = "Сокращенное наименование формы собственности", example = "ООО")
    private String name;

}
