package fr.uphf.banque_tp_bdd_web.dto;

import fr.uphf.banque_tp_bdd_web.entities.Compte;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO pour {@link fr.uphf.banque_tp_bdd_web.entities.Client}
 */
@Value
public class CreationClientRequest {
    @NotBlank
    String prenom;
    @NotBlank
    String nom;
    @NotBlank
    @Past
    LocalDateTime dateNaissance;
    @NotBlank
    String telephone;
    @NotBlank
    String addressePostale;
}