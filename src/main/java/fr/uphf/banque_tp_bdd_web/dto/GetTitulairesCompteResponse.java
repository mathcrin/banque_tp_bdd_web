package fr.uphf.banque_tp_bdd_web.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.List;
@Value
public class GetTitulairesCompteResponse implements Serializable {
    Integer idClient;

    @JsonCreator
    public GetTitulairesCompteResponse(@JsonProperty("idClient") Integer idClient) {
        this.idClient = idClient;
    }
}
