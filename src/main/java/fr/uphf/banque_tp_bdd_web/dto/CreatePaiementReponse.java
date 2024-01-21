package fr.uphf.banque_tp_bdd_web.dto;

import fr.uphf.banque_tp_bdd_web.entities.TypeDeTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * DTO pour {@link fr.uphf.banque_tp_bdd_web.entities.Transaction}
 */
@AllArgsConstructor
@Value
public class CreatePaiementReponse {
    Integer id;
    Integer montant;
    String typeDeTransaction;
    LocalDateTime dateCreation;
}
