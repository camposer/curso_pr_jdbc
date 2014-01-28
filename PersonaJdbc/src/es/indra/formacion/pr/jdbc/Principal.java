package es.indra.formacion.pr.jdbc;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import es.indra.formacion.pr.jdbc.handler.PersonaHandler;
import es.indra.formacion.pr.jdbc.model.Persona;

public class Principal {
	public Scanner scanner;
	private PersonaHandler personaHandler; 
	
	public Principal() throws SQLException {
		this.scanner = new Scanner(System.in);
		this.personaHandler = new PersonaHandler();
	}
	
	public static void main(String[] args) throws SQLException {
		new Principal().iniciar();
	}
	
	public void iniciar() throws SQLException {
		while(true) {
			System.out.println();
			System.out.println("1. Agregar una persona");	
			System.out.println("2. Lista de personas según altura (asc)");
			System.out.println("3. Lista de personas según edad (desc)");
			System.out.println("4. Persona más joven");
			System.out.println("5. Salir");
			System.out.println("? ");
			
			String opcion = scanner.nextLine();
			if (opcion.equals("1")) {
				agregarPersona();
			} else if (opcion.equals("2")) {
				personaHandler.listarPersonasSegunAltura();
			} else if (opcion.equals("3")) {
				personaHandler.listarPersonasSegunEdad();
			} else if (opcion.equals("4")) {
				personaHandler.obtenerPersonaMasJoven();
			} else if (opcion.equals("5")) {
				personaHandler.cerrar();
				break;
			}
		}		
	}

	private void agregarPersona() throws SQLException {
		System.out.print("Nombre: ");
		String nombre = scanner.nextLine();
		
		System.out.print("Apellido: ");
		String apellido = scanner.nextLine();
		
		System.out.print("Fecha de Nacimiento (yyyy-MM-dd): ");
		String sfechaNacimiento = scanner.nextLine();
		Date fechaNacimiento = null;
		try {
			fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd")
				.parse(sfechaNacimiento);
		} catch(Exception e) {}
		
		System.out.print("Altura: ");
		String saltura = scanner.nextLine();
		Float altura = null;
		try {
			altura = Float.parseFloat(saltura);
		} catch(Exception e) {}
		
		Persona p = new Persona(nombre, apellido, fechaNacimiento, altura);
		
		personaHandler.agregar(p);
	}
}
