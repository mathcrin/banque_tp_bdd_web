package fr.uphf.banque_tp_bdd_web.repositories;

import fr.uphf.banque_tp_bdd_web.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Integer> {

}