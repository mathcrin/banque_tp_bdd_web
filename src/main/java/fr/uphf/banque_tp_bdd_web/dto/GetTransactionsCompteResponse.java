package fr.uphf.banque_tp_bdd_web.dto;

import fr.uphf.banque_tp_bdd_web.entities.TypeDeSource;
import fr.uphf.banque_tp_bdd_web.entities.TypeDeTransaction;
import lombok.Value;

@Value
public class GetTransactionsCompteResponse {
    Integer id;
    Number montant;
    String typeDeTransaction;
    String typeSource;
    Integer idSource;
}
