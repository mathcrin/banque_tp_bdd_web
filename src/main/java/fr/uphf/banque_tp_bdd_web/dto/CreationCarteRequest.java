package fr.uphf.banque_tp_bdd_web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class CreationCarteRequest {
    String titulaireCarte;
    @NotNull
    Number code;
}
