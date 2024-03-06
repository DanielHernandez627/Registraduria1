package com.arq.registraduria.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Personas")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Persona {
    @Id
    @Column(name = "numero_documento", nullable = false)
    private long numero_documento;

    @Column(name = "primernombre", nullable = false)
    private String primerNombre;

    @Column(name = "segundonombre")
    private String segundoNombre;

    @Column(name = "primerapellido", nullable = false)
    private String primerApellido;

    @Column(name = "segundoapellido")
    private String segundoApellido;

    @Column(name = "fecha_nacimiento", nullable = false)
    private String fecha_Nacimiento;

    @Column(name = "idtipodocumento", nullable = false)
    private long idTipoDocumento;

  /*  @Override
    public String toString() {
        return numero_documento + "," +  primerNombre + "," + segundoNombre + "," + primerApellido + "," + segundoApellido + "," + fecha_Nacimiento + "," + idTipoDocumento+ "\n";
    }*/

}