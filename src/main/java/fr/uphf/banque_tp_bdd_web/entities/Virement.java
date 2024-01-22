package fr.uphf.banque_tp_bdd_web.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "virement")
public class Virement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank
    @NotEmpty
    @NotNull
    @Column(name = "iban_compte_emetteur", length = 34)
    private String ibanCompteEmetteur;

    @NotBlank
    @NotEmpty
    @NotNull
    @Column(name = "iban_compte_beneficiaire", length = 34)
    private String ibanCompteBeneficiaire;

    @PositiveOrZero
    @Column(name = "montant")
    private Double montant;

    @Column(name = "libelle_virement")
    private String libelleVirement;

    @Column(name = "id_transaction_emmeteur")
    private Integer idTransactionEmmeteur;

    @Column(name = "id_transaction_beneficiaire")
    private Integer idTransactionBeneficiaire;

    @NotNull
    @Column(name = "date_execution")
    private LocalDateTime dateExecution;

    @PastOrPresent
    @NotNull
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

}