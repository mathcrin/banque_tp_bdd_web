package fr.uphf.banque_tp_bdd_web.controller;

import fr.uphf.banque_tp_bdd_web.dto.*;
import fr.uphf.banque_tp_bdd_web.entities.Client;
import fr.uphf.banque_tp_bdd_web.exception.EntityNonEnregistrer;
import fr.uphf.banque_tp_bdd_web.exception.EntityNonTrouveeException;
import fr.uphf.banque_tp_bdd_web.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity<GetClientReponse> getClientByNomAndPrenom(@RequestParam String nom, @RequestParam String prenom) {
        Client client = clientService.findByNomAndPrenom(nom, prenom);
        try {
            if(client == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(new GetClientReponse(
                    client.getId(),
                    client.getPrenom(),
                    client.getNom(),
                    client.getDateNaissance(),
                    client.getTelephone(),
                    client.getAddressePostale(),
                    client.getDateCreation()
            ));
        }catch (EntityNonTrouveeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<CreationClientReponse> saveClient(@RequestBody CreationClientRequest creationClientRequest) {
        try {
            Client client = clientService.save(creationClientRequest);
            if(client == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(new CreationClientReponse(
                    client.getId(),
                    client.getPrenom(),
                    client.getNom(),
                    client.getDateNaissance(),
                    client.getTelephone(),
                    client.getAddressePostale(),
                    client.getDateCreation()
            ));
        }catch (EntityNonEnregistrer e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping()
    public ResponseEntity<UpdateClientReponse> updateClient(@RequestBody UpdateClientRequest updateClientRequest) {
        try {
            Client client = clientService.update(updateClientRequest);
            if(client == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(new UpdateClientReponse(
                    client.getId(),
                    client.getPrenom(),
                    client.getNom(),
                    client.getDateNaissance(),
                    client.getTelephone(),
                    client.getAddressePostale(),
                    client.getDateCreation()
            ));
        }catch (EntityNonEnregistrer e){
            return ResponseEntity.internalServerError().build();
        }
    }

}