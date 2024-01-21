package fr.uphf.banque_tp_bdd_web.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank
    @Column(name = "prenom", length = 50)
    private String prenom;

    @NotBlank
    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "addresse_postale")
    private String addressePostale;

    @Past(message = "date de naissance invalide")
    @Column(name = "date_naissance")
    private LocalDateTime dateNaissance;

    @Column(name = "telephone", length = 50)
    private String telephone;

    @Column(name = "age")
    private Integer age;

    @NotBlank
    @Column(name = "code_agence")
    private String codeAgence;

    @ManyToMany
    @JoinTable(name = "clients_comptes",
            joinColumns = @JoinColumn(name = "clients_id"),
            inverseJoinColumns = @JoinColumn(name = "comptes_id"))
    private List<Compte> comptes = new ArrayList<>();

    @PastOrPresent
    @NotNull
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

}