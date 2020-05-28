package br.com.senai.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) //quando a requisição receber atributos que não estão
                                            // definidos na classe DTO como propriedade, serão ignorados pela aplicação;
public class DomainDTO {

    private Long id;
    private String phrase;

}
