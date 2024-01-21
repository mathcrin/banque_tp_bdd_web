package fr.uphf.banque_tp_bdd_web.dto;

import lombok.Value;

@Value
public class GetComptesResponse {
    String iban;
    Number solde;
    String intituleCompte;
    String typeDeCompte;
    GetTitulairesCompteResponse titulaires;
    GetTransactionsCompteResponse transactions;
}
