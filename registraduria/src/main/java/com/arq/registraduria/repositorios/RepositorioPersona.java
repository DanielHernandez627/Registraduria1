package com.arq.registraduria.repositorios;

import com.arq.registraduria.entidades.Persona;

public interface RepositorioPersona {

    void SavePersona(Persona persona);

    Persona getPersonaByDocumento(long numero_documento);

}
