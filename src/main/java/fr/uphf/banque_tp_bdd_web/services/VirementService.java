package fr.uphf.banque_tp_bdd_web.services;

import fr.uphf.banque_tp_bdd_web.dto.VirementRequest;
import fr.uphf.banque_tp_bdd_web.entities.*;
import fr.uphf.banque_tp_bdd_web.repositories.CompteRepository;
import fr.uphf.banque_tp_bdd_web.repositories.TransactionRepository;
import fr.uphf.banque_tp_bdd_web.repositories.VirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VirementService {
    private final CompteRepository compteRepository;
    private final VirementRepository virementRepository;

    private final TransactionRepository transactionRepository;
    @Autowired
    public VirementService(CompteRepository compteRepository, VirementRepository virementRepository, TransactionRepository transactionRepository) {
        this.compteRepository = compteRepository;
        this.virementRepository = virementRepository;
        this.transactionRepository = transactionRepository;
    }
    public Virement virement(VirementRequest request) {
        Compte compteEmetteur = compteRepository.findOneByIban(request.getIbanCompteEmetteur());
        Compte compteBeneficiaire = compteRepository.findOneByIban(request.getIbanCompteBeneficiaire());
        if(compteEmetteur.getSolde() < request.getMontant().doubleValue()) {
            throw new IllegalArgumentException("Solde compte Emetteur insuffisant");
        }

        Transaction transactionEmetteur = Transaction.builder()
                .montant(request.getMontant().doubleValue())
                .typeDeTransaction(TypeDeTransaction.CREDIT)
                .typeDeSource(TypeDeSource.VIREMENT)
                .compte(compteEmetteur)
                .dateCreation(LocalDateTime.now())
                .build();

        Transaction transactionBeneficiaire = Transaction.builder()
                .montant(request.getMontant().doubleValue())
                .typeDeTransaction(TypeDeTransaction.DEBIT)
                .typeDeSource(TypeDeSource.VIREMENT)
                .compte(compteBeneficiaire)
                .dateCreation(LocalDateTime.now())
                .build();

        Virement virement = Virement.builder()
                .ibanCompteEmetteur(request.getIbanCompteEmetteur())
                .ibanCompteEmetteur(request.getIbanCompteBeneficiaire())
                .montant(request.getMontant().doubleValue())
                .libelleVirement(request.getLibelle())
                .idTransactionBeneficiaire(transactionBeneficiaire.getId())
                .idTransactionEmmeteur(transactionEmetteur.getId())
                .dateCreation(LocalDateTime.now())
                .dateExecution(LocalDateTime.MAX.plusDays(2))
                .build();

        transactionEmetteur.setIdSource(virement.getId());
        transactionBeneficiaire.setIdSource(virement.getId());

        transactionRepository.save(transactionEmetteur);
        transactionRepository.save(transactionBeneficiaire);
        return virementRepository.save(virement);
    }
}
