package com.arq.registraduria.controlador;

import com.arq.registraduria.entidades.Persona;
import com.arq.registraduria.servicios.servicioPersona;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ctlPersona {

    servicioPersona spersona = new servicioPersona();
    public void savePersona(Persona persona){
        spersona.SavePersona(persona);
    }

    public Persona getPersonaByDocumento(long numero_documento){
        return spersona.getPersonaByDocumento(numero_documento);
    }

}
