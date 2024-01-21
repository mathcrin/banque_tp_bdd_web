package fr.uphf.banque_tp_bdd_web.dto;

import jakarta.validation.constraints.NotNull;

public class HttpErrorResponse {
    @NotNull
    String message;
}
