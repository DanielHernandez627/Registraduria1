package com.arq.registraduria.servicios;

import com.arq.registraduria.dto.ListadoDto;
import com.arq.registraduria.entidades.Persona;
import com.arq.registraduria.entidades.TipoDocumento;
import com.arq.registraduria.repositorios.RepositorioPersona;
import com.arq.registraduria.utilidades.Utilidades;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Service
public class servicioPersona implements Serializable {

    Persona persona;

    @Autowired
    RepositorioPersona repositorioPersona;

    public boolean SavePersona(Persona persona) {
        boolean state = false;
        var registro = repositorioPersona.save(persona);
        if (registro != null){
            state = true;
        }
        return state;
    }

    public Persona getPersonaByDocumento(long numero_documento) {
        persona = repositorioPersona.findPersonaByNumero_documento(numero_documento);

        if (persona.getNumero_documento() == numero_documento){
            return persona;
        }
        return null;
    }

    public List<Persona> getAllPersona(){
        return repositorioPersona.findAll();
    }
}
