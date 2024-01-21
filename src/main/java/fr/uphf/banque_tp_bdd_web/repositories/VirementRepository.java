package fr.uphf.banque_tp_bdd_web.repositories;

import fr.uphf.banque_tp_bdd_web.entities.Virement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VirementRepository extends JpaRepository<Virement, Integer> {
}
