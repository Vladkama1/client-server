package aston.lab.clientserver.exception.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String error;
    private String message;

}
