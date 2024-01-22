package fr.uphf.banque_tp_bdd_web.controller;

import fr.uphf.banque_tp_bdd_web.dto.GetTransactionsCompteResponse;
import fr.uphf.banque_tp_bdd_web.dto.HttpErrorResponse;
import fr.uphf.banque_tp_bdd_web.dto.VirementRequest;
import fr.uphf.banque_tp_bdd_web.dto.VirementResponse;
import fr.uphf.banque_tp_bdd_web.entities.Transaction;
import fr.uphf.banque_tp_bdd_web.entities.TypeDeSource;
import fr.uphf.banque_tp_bdd_web.entities.TypeDeTransaction;
import fr.uphf.banque_tp_bdd_web.entities.Virement;
import fr.uphf.banque_tp_bdd_web.services.VirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/virements")
public class VirementController {

    private final VirementService virementService;

    @Autowired
    public VirementController(VirementService virementService) {
        this.virementService = virementService;
    }

    @PostMapping()
    public ResponseEntity<?> virement(@RequestBody VirementRequest request) {
        try {
            Virement virement  = virementService.virement(request);
            List<GetTransactionsCompteResponse> transactions = new ArrayList<>();
            transactions.add(new GetTransactionsCompteResponse(
                    virement.getIdTransactionEmmeteur(),
                    virement.getMontant(),
                    TypeDeTransaction.CREDIT.toString(),
                    TypeDeSource.VIREMENT.toString(),
                    virement.getId()
            ));
            transactions.add(new GetTransactionsCompteResponse(
                    virement.getIdTransactionBeneficiaire(),
                    virement.getMontant(),
                    TypeDeTransaction.DEBIT.toString(),
                    TypeDeSource.VIREMENT.toString(),
                    virement.getId()
            ));
            return ResponseEntity.ok(new VirementResponse(
                    virement.getId(),
                    virement.getDateCreation(),
                    transactions
            ));
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HttpErrorResponse(e.getMessage()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HttpErrorResponse("iban ou idClient(titullaire) spécifié non trouvé"));
        }
    }

}
