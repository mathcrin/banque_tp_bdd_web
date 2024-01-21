package fr.uphf.banque_tp_bdd_web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.List;

@Value
public class CreationCompteResponse {
    @NotBlank
    String intituleCompte;
    @NotBlank
    String typeCompte;
    @NotNull
    List<GetTitulairesCompteResponse> titulairesCompte;
    @NotBlank
    String iban;
    @NotBlank
    String dateCreation;
}
