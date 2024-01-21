package fr.uphf.banque_tp_bdd_web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class VirementRequest {
    @NotBlank
    String ibanCompteEmetteur;
    @NotBlank
    String ibanCompteBeneficiaire;
    @NotNull
    Number montant;
    @NotBlank
    String libelle;
}
