package fr.uphf.banque_tp_bdd_web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class CreationCarteResponse {
    @NotBlank
    String titulaireCarte;
    @NotBlank
    String numeroCarte;
    @NotBlank
    String dateExpiration;
}
