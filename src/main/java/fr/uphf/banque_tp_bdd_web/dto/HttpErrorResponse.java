package fr.uphf.banque_tp_bdd_web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class HttpErrorResponse {
    @NotNull
    String message;
}
