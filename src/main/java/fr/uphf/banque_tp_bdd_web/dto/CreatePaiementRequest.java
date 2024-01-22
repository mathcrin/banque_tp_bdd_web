package fr.uphf.banque_tp_bdd_web.dto;

import lombok.Value;

import java.time.LocalDateTime;

/**
 * DTO pour {@link fr.uphf.banque_tp_bdd_web.entities.Transaction}
 */
@Value
public class CreatePaiementRequest {
    Double montant;
    LocalDateTime datePaiement;
}
