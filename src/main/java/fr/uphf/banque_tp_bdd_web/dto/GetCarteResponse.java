package fr.uphf.banque_tp_bdd_web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class GetCarteResponse {
    @NotBlank
    String numeroCarte;
    @NotBlank
    String dateExpiration;
    @NotNull
    GetTitulairesCompteResponse titulaire;
}
