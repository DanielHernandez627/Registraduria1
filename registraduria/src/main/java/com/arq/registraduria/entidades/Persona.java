package com.arq.registraduria.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    private long numero_documento;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String fecha_Nacimiento;
    private long idTipoDocumento;

    @Override
    public String toString() {
        return numero_documento + "," +  primerNombre + "," + segundoNombre + "," + primerApellido + "," + segundoApellido + "," + fecha_Nacimiento + "," + idTipoDocumento+ "\n";
    }

}