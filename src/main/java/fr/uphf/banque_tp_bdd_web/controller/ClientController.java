package fr.uphf.banque_tp_bdd_web.controller;

import fr.uphf.banque_tp_bdd_web.dto.CreationClientRequest;
import fr.uphf.banque_tp_bdd_web.dto.GetClientReponse;
import fr.uphf.banque_tp_bdd_web.entities.Client;
import fr.uphf.banque_tp_bdd_web.exception.EntityNonEnregistrer;
import fr.uphf.banque_tp_bdd_web.exception.EntityNonTrouveeException;
import fr.uphf.banque_tp_bdd_web.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
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
            return ResponseEntity.ok(GetClientReponse.builder()
                    .id(client.getId())
                    .nom(client.getNom())
                    .prenom(client.getPrenom())
                    .dateNaissance(client.getDateNaissance())
                    .telephone(client.getTelephone())
                    .addressePostale(client.getAddressePostale())
                    .dateCreation(client.getDateCreation())
                    .build());
        }catch (EntityNonTrouveeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Client> saveClient(@RequestBody CreationClientRequest client) {
        try {
            return ResponseEntity.ok(clientService.save(client));
        }catch (EntityNonEnregistrer e){
            return ResponseEntity.notFound().build();
        }
    }

}
