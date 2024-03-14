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
public class RegistraduriaApplication implements CommandLineRunner {

	/*
	registraduría :
		- crear registro de un niño nacido
		- apellidos de los padres (si va solo padre solo él, si va solo madre solo ella)
		- cambiar tipo de documento dependiendo de la edad
		- visualización de datos

	*/
	static Persona persona = new Persona();
	static ctlTDocumento tdocumento = new ctlTDocumento();

	static SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");

	@Autowired
	RepositorioPersona repositorioPersona;

	@Autowired
	RepositorioTDocumento repositorioTDocumento;

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(RegistraduriaApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);

		int opcion;

		while (true) {
			System.out.println("Menú Principal");
			System.out.println("1. Registrar persona");
			System.out.println("2. Actualizar documento");
			System.out.println("3. Búsqueda de persona por documento");
			System.out.println("4. Salir");
			System.out.print("Seleccione una opción: ");

			opcion = sc.nextInt();

			switch (opcion) {
				case 1:
					registrarPersona();
					break;
				case 2:
					actualizarDocumento();
					break;
				case 3:
					buscarPersona();
					break;
				case 4:
					System.out.println("Saliendo del programa...");
					sc.close();
					System.exit(0);
				default:
					System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
			}
		}
	}

	public  void registrarPersona() {

		ctlPersona cPersona = new ctlPersona();
		Utilidades utilidades = new Utilidades();
		Scanner sc1 = new Scanner(System.in);

		System.out.println("Digite el primer nombre: ");
		persona.setPrimerNombre(sc1.nextLine());
		System.out.println("Digite el segundo nombre (Opcional): ");
		persona.setSegundoNombre(sc1.nextLine());
		System.out.println("Digite el primer apellido: ");
		persona.setPrimerApellido(sc1.nextLine());
		System.out.println("Digite el segundo apellido (Opcional): ");
		persona.setSegundoApellido(sc1.nextLine());
		System.out.println("Digite la fecha de nacimiento: ");
		persona.setFecha_Nacimiento(sc1.nextLine());

		persona.setIdTipoDocumento(1);

		persona.setNumero_documento(utilidades.generarNumeroAleatorio());

		if(validacionSavePersona()){
			repositorioPersona.save(persona);
			System.out.println("Registrando persona...");
			System.out.println("Su numero de documento es " + persona.getNumero_documento());
		}else{
			System.out.println("Error verifique el Log");
		}

	}

	public static boolean validacionSavePersona(){

		boolean ind = true;

		if (persona.getPrimerNombre().isEmpty()){
			System.out.println("Primer nombre es obligatorio");
			ind = false;
		} else if (persona.getPrimerApellido().isEmpty()) {
			System.out.println("Primer apellido es obligatorio");
			ind = false;
		} else if (persona.getFecha_Nacimiento().isEmpty()) {
			System.out.println("Fecha de nacimiento es obligatoria");
			ind = false;
		} else if (persona.getIdTipoDocumento() == 0) {
			System.out.println("La seleccion de documento es obligatoria");
			ind = false;
		}

		return ind;
	}

	public void actualizarDocumento() throws ParseException {

		LocalDate fechaActual = LocalDate.now();

		ctlPersona cPersona = new ctlPersona();
		Scanner sc2 = new Scanner(System.in);

		long numero_documento;
		int opcion_actualizacion,id_documento_nuevo = 0;


		System.out.println("Digite el numero de documetno de la persona a editar: ");
		numero_documento = sc2.nextLong();

		Optional<Persona> personaOptional = repositorioPersona.findById(numero_documento);
		Persona persona1 = personaOptional.orElseThrow(() -> new NoSuchElementException("No se encontró ninguna persona"));

		if (persona1 != null){
			Date fecha = formatoFecha.parse(persona1.getFecha_Nacimiento());
			LocalDate fechaLocal = fecha.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
			Period periodo = Period.between(fechaLocal, fechaActual);

			int aniosTranscurridos = periodo.getYears();

			TipoDocumento documento = documentoEspecifico(persona1.getIdTipoDocumento());
			if (documento != null){
				System.out.println("Persona a editar: " + persona1.getNumero_documento());

				System.out.println("Nombre: "+ persona1.getPrimerNombre() + " "
						+ persona1.getSegundoNombre()+ " "
						+ persona1.getPrimerApellido() + " " + persona1.getSegundoApellido());

				System.out.println("Fecha de nacimiento: "+ persona1.getFecha_Nacimiento());
				System.out.println("Tipo de documento: "+ documento.getId() + "." + documento.getNombre() + "\n");

				System.out.println("Si la informcion es correcta digite 1 si no 2");
				opcion_actualizacion = sc2.nextInt();

				if (opcion_actualizacion == 1){
					System.out.println("**** Recuerde para cambiar el tipo de documento a Tarjeta de identidad debe tener 12 años ****");
					System.out.println("**** Recuerde para cambiar el tipo de documento a Cedula de ciudadania debe tener 18 años **** \n");

					while (id_documento_nuevo == 0) {
						System.out.println("Seleccione el nuevo documento: ");

						for (TipoDocumento documento1 : repositorioTDocumento.findAll()) {
							System.out.println(documento1.getId() + ". " + documento1.getNombre());
						}

						System.out.println("\nSeleccione el número del nuevo documento a asignar: ");
						id_documento_nuevo = sc2.nextInt();

						if (id_documento_nuevo < 1 || id_documento_nuevo > 3) {
							id_documento_nuevo = 0;
							System.out.println("Seleccione un documento válido");
						} else {
							persona1.setIdTipoDocumento(id_documento_nuevo);
						}
					}

					if (persona1.getIdTipoDocumento() == 1 && aniosTranscurridos > 7) {
						System.out.println("La edad no coincide con el rango aplicable para este tipo de documento REGISTRO CIVIL");
					} else if (persona1.getIdTipoDocumento() == 2 && (aniosTranscurridos < 7 || aniosTranscurridos > 17)) {
						System.out.println("La edad actual no es válida para este tipo de documento TARJETA DE IDENTIDAD");
					} else if (persona1.getIdTipoDocumento() == 3 && aniosTranscurridos < 18) {
						System.out.println("La edad actual no es válida para este tipo de documento CÉDULA");
					}else{
						System.out.println("Actualizando documento...");
						repositorioPersona.save(persona1);
						System.out.println("Actualizacion exitosa");
					}

				}else{
					System.out.println("Informacion errornea \nretornando al menu principal \n");
				}

			}else{
				System.out.println("No se encontro documento");
			}
		}else{
			System.out.println("No se encontro a la persona");
		}
	}

	public TipoDocumento documentoEspecifico(long id){
		for (TipoDocumento documento : repositorioTDocumento.findAll()) {
			if (documento.getId() == id){
				return documento;
			}
		}
		return  null;
	}

	public void buscarPersona() {
		System.out.println("Personas Registradas \n");

		List<Persona> personaList = repositorioPersona.findAll();

		for (Persona persona1 : personaList){
			System.out.println("*****************************************************");
			System.out.println("Numero de documento: "+ persona1.getNumero_documento());
			System.out.println("Nombre: "+ persona1.getPrimerNombre() + " "
					+ persona1.getSegundoNombre()+ " "
					+ persona1.getPrimerApellido() + " " + persona1.getSegundoApellido());
			System.out.println("Fecha de nacimiento: "+ persona1.getFecha_Nacimiento());

			TipoDocumento documento = documentoEspecifico(persona1.getIdTipoDocumento());

            assert documento != null;
            System.out.println("Tipo de documento: "+ documento.getId() + "." + documento.getNombre());
			System.out.println("*****************************************************");

		}
	}
}
