package com.arq.registraduria.servicios;

import com.arq.registraduria.entidades.TipoDocumento;
import com.arq.registraduria.repositorios.RepositorioTDocumento;
import com.arq.registraduria.utilidades.Utilidades;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class servicioTDocumento implements RepositorioTDocumento, Serializable {

    Utilidades utilidades = new Utilidades();

    @Override
    public List<TipoDocumento> getAllDocumentos() {
        return convertirRespuesta(utilidades.leerArchivo("Documentos"));
    }

    public static List<TipoDocumento> convertirRespuesta(String respuesta) {
        List<TipoDocumento> entidades = new ArrayList<>();
        String[] lineas = respuesta.split("\n");
        for (String linea : lineas) {
            String[] partes = linea.split(",");
            if (partes.length == 3) {
                long id = Long.parseLong(partes[0]);
                String sigla = partes[1];
                String nombre = partes[2];
                entidades.add(new TipoDocumento(id, sigla, nombre));
            } else {
                System.out.println("Formato incorrecto en la línea: " + linea);
            }
        }
        return entidades;
    }
}
