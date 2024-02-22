package com.arq.registraduria.repositorios;

import com.arq.registraduria.entidades.Persona;

import java.util.List;

public interface RepositorioPersona {

    void SavePersona(Persona persona);

    Persona getPersonaByDocumento(long numero_documento);

    List<Persona> getAllPersona();

}
