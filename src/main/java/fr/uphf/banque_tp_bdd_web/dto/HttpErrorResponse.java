package fr.uphf.banque_tp_bdd_web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HttpErrorResponse {
    @NotNull
    String message;
}
