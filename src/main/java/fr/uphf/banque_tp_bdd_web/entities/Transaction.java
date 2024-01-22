package fr.uphf.banque_tp_bdd_web.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @PositiveOrZero
    @NotNull
    @Column(name = "montant")
    private Double montant;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_de_transaction")
    private TypeDeTransaction typeDeTransaction;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_de_source")
    private TypeDeSource typeDeSource;

    @NotNull
    @Column(name = "id_source")
    private Integer idSource;

    @ManyToOne
    @JoinColumn(name = "compte_id")
    private Compte compte;

    @PastOrPresent
    @NotNull
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

}