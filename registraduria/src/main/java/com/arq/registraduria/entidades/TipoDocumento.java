package com.arq.registraduria.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name = "tipodocumentos")
@Table(name = "tipodocumentos")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class TipoDocumento {
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "sigla", nullable = false)
    private String sigla;
    @Column(name = "nombre", nullable = false)
    private String Nombre;
}
