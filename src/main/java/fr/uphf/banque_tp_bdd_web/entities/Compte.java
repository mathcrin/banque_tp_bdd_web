package fr.uphf.banque_tp_bdd_web.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "compte")
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "solde")
    private Double solde;

    @Column(name = "iban", length = 34)
    private String iban;

    @Column(name = "intitule_compte")
    private String intituleCompte;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_de_compte")
    private TypeDeCompte typeDeCompte;

    @OneToMany(mappedBy = "compte", orphanRemoval = true)
    @OrderBy("dateCreation DESC")
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany(mappedBy = "compte", orphanRemoval = true)
    @OrderBy("dateCreation DESC")
    private List<Carte> cartes = new ArrayList<>();

    @PastOrPresent
    @NotNull
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @ManyToMany
    @JoinTable(name = "clients_comptes",
            joinColumns = @JoinColumn(name = "comptes_id"),
            inverseJoinColumns = @JoinColumn(name = "clients_id"))
    private List<Client> titulaires = new ArrayList<>();

}