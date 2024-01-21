package fr.uphf.banque_tp_bdd_web.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class VirementResponse {
    @NotNull
    Integer idVirement;
    @NotNull
    LocalDateTime dateCreation;
    @NotNull
    GetTransactionsCompteResponse transactions;
}