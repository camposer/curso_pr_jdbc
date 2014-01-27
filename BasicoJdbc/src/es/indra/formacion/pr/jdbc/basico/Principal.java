package es.indra.formacion.pr.jdbc.basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Principal {
	public static final String URL = "jdbc:derby://localhost:1527/persona";
	public static final String USUARIO = "user";
	public static final String CLAVE = "123";
	public static Connection con;
		
	// 0.- Añadir al proyecto el driver (librería - JAR)!!
	static {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver"); // 1.- Cargar el Driver
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  
	}
	
	public static void main(String[] args) throws SQLException {
		abrirConexion(); // 2.- Abrir la conexión
		
		// 3 y 4.- Crear comandos y ejecutar 
		//insertarPersona();
		consultarPersonas();
		
		cerrarConexion(); // 5.- Liberar la conexión
	}

	private static void consultarPersonas() throws SQLException {
		String query = "SELECT * FROM persona";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		while (rs.next()) {
			System.out.println("\nPersona: ");
			System.out.println("id = " + rs.getInt(1)); // Comienza en 1 no en 0
			System.out.println("nombre = " + rs.getString("nombre"));
			System.out.println("apellido = " + rs.getString("apellido"));
			System.out.println("fechaNacimiento = " + rs.getDate("fecha_nacimiento"));
			System.out.println("altura = " + rs.getFloat("altura"));
		}
	}

	private static void insertarPersona() throws SQLException {
		String query = "INSERT INTO persona"
				+ "(nombre, apellido, fecha_nacimiento, altura) "
				+ "VALUES('Silvia', 'Rodríguez', '1980-03-13', 80)";
		Statement stmt = con.createStatement();
		int numRegistros = stmt.executeUpdate(query);
		System.out.println("Ha modificado " + numRegistros + " registros");		
	}
	
	public static void abrirConexion() throws SQLException  {
		con = DriverManager.getConnection(URL, USUARIO, CLAVE);
	}

	public static void cerrarConexion() throws SQLException {
		if (con != null)
			con.close();
	}

}
