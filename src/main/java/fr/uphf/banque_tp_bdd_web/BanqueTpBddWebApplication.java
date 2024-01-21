package fr.uphf.banque_tp_bdd_web;

import fr.uphf.banque_tp_bdd_web.entities.Compte;
import fr.uphf.banque_tp_bdd_web.entities.TypeDeCompte;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BanqueTpBddWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanqueTpBddWebApplication.class, args);
	}

}
