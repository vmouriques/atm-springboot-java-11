package br.com.matos.atm.controllers.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonFormat
@Component
public class Data<T> {

    @Valid
    @JsonProperty("data")
    @NotNull(
            message = "Campo Obrigatório"
    )
    private T data;
}
