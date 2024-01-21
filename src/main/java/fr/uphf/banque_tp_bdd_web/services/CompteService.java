package fr.uphf.banque_tp_bdd_web.services;

import fr.uphf.banque_tp_bdd_web.dto.CreationCarteRequest;
import fr.uphf.banque_tp_bdd_web.dto.CreationCompteRequest;
import fr.uphf.banque_tp_bdd_web.dto.GetTitulairesCompteResponse;
import fr.uphf.banque_tp_bdd_web.entities.Carte;
import fr.uphf.banque_tp_bdd_web.entities.Client;
import fr.uphf.banque_tp_bdd_web.entities.Compte;
import fr.uphf.banque_tp_bdd_web.entities.TypeDeCompte;
import fr.uphf.banque_tp_bdd_web.repositories.CarteRepository;
import fr.uphf.banque_tp_bdd_web.repositories.ClientRepository;
import fr.uphf.banque_tp_bdd_web.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompteService {
    private final CompteRepository compteRepository;
    private final ClientRepository clientRepository;
    private final CarteRepository carteRepository;

    private final String codePays = "FR76";
    private final String codeBanque = "30003";
    private final String codeAgence = "02054";


    @Autowired
    public CompteService(CompteRepository compteRepository, ClientRepository clientRepository,
                         CarteRepository carteRepository) {
        this.compteRepository = compteRepository;
        this.clientRepository = clientRepository;
        this.carteRepository = carteRepository;
    }

    public List<Client> getTitulairesFromIban(String iban) {
        return compteRepository.findByIban(iban).getTitulaires();
    }

    public String generateIban() {
        Long numeroDeCompte = compteRepository.count() + 1;
        String numeroDeCompteFormatte = String.format("%011d", numeroDeCompte);
        String cleRIB = String.format("%02d", 97 - (89 * Long.parseLong(codeBanque) + 15 * Long.parseLong(codeAgence) + numeroDeCompte) % 97);
        return codePays + codeBanque + codeAgence + numeroDeCompteFormatte + cleRIB;
    }

    public Compte save(CreationCompteRequest request) {
        List<Client> titulaires = new ArrayList<>();
        for (GetTitulairesCompteResponse titulaire : request.getTitulairesCompte()) {
            titulaires.add(clientRepository.findById(titulaire.getIdClient()).orElseThrow());
        }
        Compte compte = Compte.builder()
                .solde(0.00)
                .iban(this.generateIban())
                .intituleCompte(request.getIntituleCompte())
                .typeDeCompte(TypeDeCompte.valueOf(request.getTypeCompte()))
                .titulaires(titulaires)
                .dateCreation(LocalDateTime.now())
                .build();
        return compteRepository.save(compte);
    }

    public List<Carte> getcartes(String ibanCompte) {
        return compteRepository.findByIban(ibanCompte).getCartes();
    }

    public String generateNumeroCarte() {
        Long numeroCarte = compteRepository.count() + 1;
        String numeroCarteFormatte = String.format("%016d", numeroCarte);
        return numeroCarteFormatte;
    }

    public Carte createCarte(String ibanCompte, CreationCarteRequest request) {
        Compte compte = compteRepository.findByIban(ibanCompte);
        Client client = clientRepository.findById(request.getTitulaireCarte()).orElseThrow();
        Carte carte = Carte.builder()
                .numeroCarte(this.generateNumeroCarte())
                .motDePasse(request.getCode().toString())
                .estActif(true)
                .compte(compte)
                .client(client)
                .dateExpiration(LocalDate.now().plusYears(3))
                .dateCreation(LocalDateTime.now())
                .build();
        carteRepository.save(carte);
        compte.getCartes().add(carte);
        compteRepository.save(compte);
        return carte;
    }
}
