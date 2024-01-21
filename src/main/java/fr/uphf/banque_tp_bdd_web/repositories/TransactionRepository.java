package fr.uphf.banque_tp_bdd_web.repositories;

import fr.uphf.banque_tp_bdd_web.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
