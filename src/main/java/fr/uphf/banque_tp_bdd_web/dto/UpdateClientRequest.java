package fr.uphf.banque_tp_bdd_web.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Value;

import java.time.LocalDateTime;
@Value
public class UpdateClientRequest {
    Integer id;
    String prenom;
    String nom;
    LocalDateTime dateNaissance;
    String telephone;
    String adressePostale;
}
