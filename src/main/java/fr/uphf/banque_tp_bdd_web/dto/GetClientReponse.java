package fr.uphf.banque_tp_bdd_web.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * DTO pour {@link fr.uphf.banque_tp_bdd_web.entities.Client}
 */
@Builder
@Value
public class GetClientReponse {
    Integer id;
    String prenom;
    String nom;
    LocalDateTime dateNaissance;
    String telephone;
    String addressePostale;
    LocalDateTime dateCreation;
}
