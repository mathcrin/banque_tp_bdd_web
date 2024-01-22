package fr.uphf.banque_tp_bdd_web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@AllArgsConstructor
@Value
public class CreationClientReponse {
    Integer id;
    String prenom;
    String nom;
    LocalDateTime dateNaissance;
    String telephone;
    String adressePostale;
    LocalDateTime dateCreation;
}
