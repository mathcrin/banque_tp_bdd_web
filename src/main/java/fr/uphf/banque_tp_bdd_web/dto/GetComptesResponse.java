package fr.uphf.banque_tp_bdd_web.dto;

import fr.uphf.banque_tp_bdd_web.entities.TypeDeCompte;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@AllArgsConstructor
@Value
public class GetComptesResponse {
    String iban;
    Number solde;
    String intituleCompte;
    TypeDeCompte typeDeCompte;
    List<GetTitulairesCompteResponse> titulaires;
    List<GetTransactionsCompteResponse> transactions;
}
