package fr.uphf.banque_tp_bdd_web.controller;

import fr.uphf.banque_tp_bdd_web.dto.*;
import fr.uphf.banque_tp_bdd_web.entities.Carte;
import fr.uphf.banque_tp_bdd_web.entities.Client;
import fr.uphf.banque_tp_bdd_web.entities.Compte;
import fr.uphf.banque_tp_bdd_web.entities.Transaction;
import fr.uphf.banque_tp_bdd_web.exception.EntityNonEnregistrer;
import fr.uphf.banque_tp_bdd_web.exception.EntityNonTrouveeException;
import fr.uphf.banque_tp_bdd_web.services.ClientService;
import fr.uphf.banque_tp_bdd_web.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/comptes")
public class CompteController {
    private final CompteService compteService;

    private final ClientService clientService;

    @Autowired
    public CompteController(CompteService compteService, ClientService clientService) {
        this.compteService = compteService;
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity<?> getComptes(@RequestParam Integer identifiantClient) {
        try {
            List<Compte> comptes = clientService.getComptes(identifiantClient);
            List<GetComptesResponse> comptesResponse = new ArrayList<>();
            if (comptes == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HttpErrorResponse("Aucun compte lié à cet identifiant client"));
            }

            for (Compte compte : comptes) {
                List<GetTitulairesCompteResponse> titulaires = new ArrayList<>();
                List<GetTransactionsCompteResponse> transactions = new ArrayList<>();
                for (Client titulaire : compte.getTitulaires()) {
                    titulaires.add(new GetTitulairesCompteResponse(titulaire.getId()));
                }
                for (Transaction transaction : compte.getTransactions()) {
                    transactions.add(new GetTransactionsCompteResponse(
                            transaction.getId(),
                            transaction.getMontant(),
                            transaction.getTypeDeTransaction().toString(),
                            transaction.getTypeDeSource().toString(),
                            transaction.getIdSource()
                    ));
                }
                comptesResponse.add(new GetComptesResponse(
                                compte.getIban(),
                                compte.getSolde(),
                                compte.getIntituleCompte(),
                                compte.getTypeDeCompte(),
                                titulaires,
                                transactions
                        ));
            }
            return ResponseEntity.ok(comptesResponse);
        } catch (EntityNonTrouveeException | NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HttpErrorResponse("Aucun compte lié à cet identifiant client"));
        }
    }

    @PostMapping()
    public ResponseEntity<?> createCompte(@RequestBody CreationCompteRequest request) {
        try {
            Compte compte = compteService.save(request);
            List<GetTitulairesCompteResponse> titulaires = new ArrayList<>();
            for (Client titulaire : compte.getTitulaires()) {
                titulaires.add(new GetTitulairesCompteResponse(titulaire.getId()));
            }
            if (compte == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HttpErrorResponse("Compte client non créer"));
            }
            return ResponseEntity.ok(new CreationCompteResponse(
                    compte.getIntituleCompte(),
                    compte.getTypeDeCompte().toString(),
                    titulaires,
                    compte.getIban(),
                    compte.getDateCreation().toString()
            ));
        } catch (EntityNonEnregistrer e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HttpErrorResponse("Compte non enregistré"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HttpErrorResponse("Titulaire spécifié non trouvé"));
        }
    }

    @GetMapping("/{iban}/cartes")
    public ResponseEntity<?> getCartes(@PathVariable String iban) {
        try {
            List<GetCarteResponse> cartes = new ArrayList<>();
            List<GetTitulairesCompteResponse> titulairesResponse = new ArrayList<>();
            for (Client titulaire : compteService.getTitulairesFromIban(iban)) {
                titulairesResponse.add(new GetTitulairesCompteResponse(titulaire.getId()));
            }
            for (Carte carte : compteService.getcartes(iban)) {
                cartes.add(new GetCarteResponse(
                        carte.getNumeroCarte(),
                        carte.getDateExpiration().toString(),
                        titulairesResponse
                ));
            }
            return ResponseEntity.ok(cartes);
        } catch (EntityNonTrouveeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HttpErrorResponse("Aucune carte liée à ce compte"));
        }
    }

    @PostMapping("/{iban}/cartes")
    public ResponseEntity<?> createCarte(@PathVariable String iban, @RequestBody CreationCarteRequest request) {
        try {
            Carte carte = compteService.createCarte(iban, request);
            return ResponseEntity.ok(new CreationCarteResponse(
                    carte.getClient().getId().toString(),
                    carte.getNumeroCarte(),
                    carte.getDateExpiration().toString()
            ));
        } catch (EntityNonEnregistrer e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HttpErrorResponse("Carte non enregistrée"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HttpErrorResponse("iban ou idClient(titullaire) spécifié non trouvé"));
        }
    }



}
