package com.arq.registraduria.utilidades;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@NoArgsConstructor
public class Utilidades {

    public static void generarArchivo(String nombreArchivo, String nuevoDato) {
        List<String> lineas = new ArrayList<>();

        // Leer el archivo y cargar las líneas existentes
        try {
            if (Files.exists(Paths.get(nombreArchivo))) {
                lineas = Files.readAllLines(Paths.get(nombreArchivo));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Buscar si el número ya existe en el archivo
        boolean encontrado = false;
        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            String[] partes = linea.split(",");
            if (partes.length > 0 && partes[0].equals(nuevoDato.split(",")[0])) {
                lineas.set(i, nuevoDato); // Actualizar la línea existente
                encontrado = true;
                break;
            }
        }

        // Si el número no se encontró, agregarlo al final
        if (!encontrado) {
            lineas.add(nuevoDato);
        }

        // Escribir las líneas actualizadas en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (String linea : lineas) {
                writer.write(linea + "\n");
            }
            System.out.println("Archivo '" + nombreArchivo + "' generado o actualizado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  String leerArchivo(String nombreArchivo) {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contenido.toString();
    }

    public long generarNumeroAleatorio() {
        Random random = new Random();
        long numeroAleatorio = 0;

        for (int i = 0; i < 10; i++) {
            numeroAleatorio = numeroAleatorio * 10 + random.nextInt(10);
        }

        return numeroAleatorio;
    }
}
