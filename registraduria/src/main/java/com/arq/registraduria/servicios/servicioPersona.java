package com.arq.registraduria.servicios;

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


    Utilidades utilidades = new Utilidades();
    List<Persona> personaList = new ArrayList<>();

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

    /*

    @Override
    public List<Persona> getAllPersona() {
        return convertirRespuesta(utilidades.leerArchivo(nombreArchivo));
    }

    public static List<Persona> convertirRespuesta(String respuesta) {
        List<Persona> entidades = new ArrayList<>();
        String[] lineas = respuesta.split("\n");
        for (String linea : lineas) {
            String[] partes = linea.split(",");
            if (partes.length == 7) {
                long numero_documento = Long.parseLong(partes[0]);
                String primerNombre = partes[1];
                String segundoNombre = partes[2];
                String primerApellido = partes[3];
                String segundoApellido = partes[4];
                String fecha_Nacimiento = partes[5];
                long idTipoDocumento = Long.parseLong(partes[6]);
                entidades.add(new Persona(numero_documento, primerNombre, segundoNombre,primerApellido,segundoApellido,fecha_Nacimiento,idTipoDocumento));
            } else {
                System.out.println("Formato incorrecto en la línea: " + linea);
            }
        }
        return entidades;
    }*/
}
