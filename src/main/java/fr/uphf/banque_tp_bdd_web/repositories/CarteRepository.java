package fr.uphf.banque_tp_bdd_web.repositories;

import fr.uphf.banque_tp_bdd_web.entities.Carte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteRepository extends JpaRepository<Carte, Integer> {
    Carte findOneByNumeroCarte(String numeroCarte);
}
