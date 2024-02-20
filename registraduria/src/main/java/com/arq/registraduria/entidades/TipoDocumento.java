package com.arq.registraduria.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoDocumento {
    private long id;
    private String sigla;
    private String Nombre;
}
