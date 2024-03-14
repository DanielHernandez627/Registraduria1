package com.arq.registraduria.servicios;

import com.arq.registraduria.entidades.TipoDocumento;
import com.arq.registraduria.repositorios.RepositorioTDocumento;
import com.arq.registraduria.utilidades.Utilidades;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class servicioTDocumento {

    Utilidades utilidades = new Utilidades();

    @Autowired
    RepositorioTDocumento repositorioTDocumento;

    public List<TipoDocumento> getAllDocumentos() {
        return repositorioTDocumento.findAll();
    }
}
