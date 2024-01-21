package fr.uphf.banque_tp_bdd_web.services;

import fr.uphf.banque_tp_bdd_web.dto.CreationClientRequest;
import fr.uphf.banque_tp_bdd_web.entities.Client;
import fr.uphf.banque_tp_bdd_web.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final String codeAgence = "00001";

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client findByNomAndPrenom(String nom, String prenom) {
        return clientRepository.findByNomAndPrenom(nom, prenom);
    }

    public Client save(CreationClientRequest client) {
        return clientRepository.save(Client.builder()
                        .nom(client.getNom())
                        .prenom(client.getPrenom())
                        .addressePostale(client.getAddressePostale())
                        .dateNaissance(client.getDateNaissance())
                        .telephone(client.getTelephone())
                        .age(LocalDateTime.now().getYear() - client.getDateNaissance().getYear())
                        .codeAgence(codeAgence)
                        .dateCreation(LocalDateTime.now())
                .build());
    }
}
