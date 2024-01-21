package fr.uphf.banque_tp_bdd_web.dto;

import fr.uphf.banque_tp_bdd_web.entities.TypeDeCompte;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class CreationCompteRequest {
    @NotBlank
    String intituleCompte;
    @NotBlank
    String typeCompte;
    @NotNull
    GetTitulairesCompteResponse titulaire;
}
