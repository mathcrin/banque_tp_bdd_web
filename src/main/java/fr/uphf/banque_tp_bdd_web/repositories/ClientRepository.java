package fr.uphf.banque_tp_bdd_web.repositories;

import fr.uphf.banque_tp_bdd_web.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByNomAndPrenom(String nom, String prenom);

    //Exemple de quesrry possible :
    //@Query("SELECT c FROM Client c WHERE c.nom = ?1 AND c.prenom = ?2")
    //Client findByNomAndPrenom(String nom, String prenom);
}
