package com.arq.registraduria;

import com.arq.registraduria.controlador.ctlPersona;
import com.arq.registraduria.controlador.ctlTDocumento;
import com.arq.registraduria.entidades.Persona;
import com.arq.registraduria.entidades.TipoDocumento;
import com.arq.registraduria.repositorios.RepositorioPersona;
import com.arq.registraduria.repositorios.RepositorioTDocumento;
import com.arq.registraduria.utilidades.Utilidades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.Period;

@SpringBootApplication
public class RegistraduriaApplication {

	/*
	registraduría :
		- crear registro de un niño nacido
		- apellidos de los padres (si va solo padre solo él, si va solo madre solo ella)
		- cambiar tipo de documento dependiendo de la edad
		- visualización de datos

	*/

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(RegistraduriaApplication.class, args);
	}
}
