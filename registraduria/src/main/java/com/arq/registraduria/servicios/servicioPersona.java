package com.arq.registraduria.servicios;

import com.arq.registraduria.entidades.Persona;
import com.arq.registraduria.entidades.TipoDocumento;
import com.arq.registraduria.repositorios.RepositorioPersona;
import com.arq.registraduria.utilidades.Utilidades;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class servicioPersona implements RepositorioPersona, Serializable {


    Utilidades utilidades = new Utilidades();
    List<Persona> personaList = new ArrayList<>();

    @Override
    public void SavePersona(Persona persona) {
        String personaS = persona.toString();
        utilidades.generarArchivo("Personas",personaS);
    }

    @Override
    public Persona getPersonaByDocumento(long numero_documento) {
        personaList = convertirRespuesta(utilidades.leerArchivo("Personas"));

        for (Persona lista : personaList){
            if (lista.getNumero_documento() == numero_documento){
                return lista;
            }
        }

        return null;
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
    }
}
