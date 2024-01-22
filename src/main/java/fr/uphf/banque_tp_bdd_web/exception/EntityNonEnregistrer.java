package fr.uphf.banque_tp_bdd_web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_MODIFIED)
public class EntityNonEnregistrer extends RuntimeException{
}
