package aston.lab.clientserver.controller;

import aston.lab.clientserver.dto.response.FormOwnershipDto;
import aston.lab.clientserver.service.FormOwnershipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/client/v1.0/")
@RequiredArgsConstructor
public class FormOwnershipController {

    private final FormOwnershipService formOwnershipService;

    @Operation(
            summary = "Получение всех записей из таблицы form_ownership MS Client",
            description = "Проводит поиск всех форм собственности клиента."
    )
    @ApiResponses(value = {
                     @ApiResponse(responseCode = "404", description = "Информация не найдена",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string")))
    })
    @GetMapping("clients/formownerships")
    public ResponseEntity<?> findAllFormOwnerships(@RequestHeader("X-User-Id") String userId,
    @RequestHeader("X-User-Role") String userRole) {
        try {
            List<FormOwnershipDto> formOwnershipDtos = formOwnershipService.findAllFormOwnerships();
            return ResponseEntity.status(HttpStatus.OK).body(formOwnershipDtos);
        } catch (RuntimeException e) {
            log.error("Information not find: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Информация не найдена");
        }
    }
}
