package com.arq.registraduria;

import com.arq.registraduria.controlador.ctlPersona;
import com.arq.registraduria.controlador.ctlTDocumento;
import com.arq.registraduria.entidades.Persona;
import com.arq.registraduria.entidades.TipoDocumento;
import com.arq.registraduria.utilidades.Utilidades;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class RegistraduriaApplication {

	/*
	registraduría :
		- crear registro de un niño nacido
		- apellidos de los padres (si va solo padre solo él, si va solo madre solo ella)
		- cambiar tipo de documento dependiendo de la edad
		- visualización de datos

	*/
	static Persona persona = new Persona();
	static ctlTDocumento tdocumento = new ctlTDocumento();


	public static void main(String[] args) {

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


	public static void registrarPersona() {

		ctlPersona cPersona = new ctlPersona();
		Utilidades utilidades = new Utilidades();
		Scanner sc1 = new Scanner(System.in);

		int id_documento;

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
			cPersona.savePersona(persona);
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

	public static void actualizarDocumento() {

		ctlPersona cPersona = new ctlPersona();
		Scanner sc2 = new Scanner(System.in);

		long numero_documento;
		int opcion_actualizacion,id_documento_nuevo;


		System.out.println("Digite el numero de documetno de la persona a editar: ");
		numero_documento = sc2.nextLong();

		Persona persona1 = cPersona.getPersonaByDocumento(numero_documento);

		if (persona1 != null){
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

					System.out.println("Seleccione el nuevo documento: ");
					for (TipoDocumento documento1 : tdocumento.GetDocumentos()) {
						System.out.println(documento.getId() + ". " + documento1.getNombre());
					}
					System.out.println("\n");

					System.out.println("Seleccione el numero del nuevo documetno a asignar: ");
					id_documento_nuevo = sc2.nextInt();

					System.out.println("Actualizando documento...");
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

	public static void buscarPersona() {
		System.out.println("Buscando persona por documento...");
		// Aquí puedes implementar la lógica para buscar una persona por documento
	}

	public static TipoDocumento documentoEspecifico(long id){
		for (TipoDocumento documento : tdocumento.GetDocumentos()) {
			if (documento.getId() == id){
				return documento;
			}
		}
		return  null;
	}

}
