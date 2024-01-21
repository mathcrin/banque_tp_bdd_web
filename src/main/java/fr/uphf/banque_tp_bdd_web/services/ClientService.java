package fr.uphf.banque_tp_bdd_web.services;

import fr.uphf.banque_tp_bdd_web.dto.CreationClientRequest;
import fr.uphf.banque_tp_bdd_web.dto.UpdateClientRequest;
import fr.uphf.banque_tp_bdd_web.entities.Client;
import fr.uphf.banque_tp_bdd_web.entities.Compte;
import fr.uphf.banque_tp_bdd_web.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final String codeAgence = "02054";

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

    public Client update(UpdateClientRequest updateClientRequest) {
        Client client = clientRepository.findById(updateClientRequest.getId()).orElseThrow();
        if(updateClientRequest.getNom() != null) client.setNom(updateClientRequest.getNom());
        if(updateClientRequest.getPrenom() != null) client.setPrenom(updateClientRequest.getPrenom());
        if(updateClientRequest.getAddressePostale() != null) client.setAddressePostale(updateClientRequest.getAddressePostale());
        if(updateClientRequest.getDateNaissance() != null) client.setDateNaissance(updateClientRequest.getDateNaissance());
        if(updateClientRequest.getTelephone() != null) client.setTelephone(updateClientRequest.getTelephone());
        if(updateClientRequest.getDateNaissance() != null) client.setAge(LocalDateTime.now().getYear() - updateClientRequest.getDateNaissance().getYear());
        return clientRepository.save(client);
    }

    public List<Compte> getComptes(Integer identifiantClient) {
        return clientRepository.findById(identifiantClient).orElseThrow().getComptes();
    }
}
