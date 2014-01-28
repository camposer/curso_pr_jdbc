package es.indra.formacion.pr.jdbc.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import es.indra.formacion.pr.jdbc.model.Persona;

public class PersonaHandler {
	private static final String URL = "jdbc:derby://localhost:1527/persona";
	private static final String USUARIO = "user";
	private static final String CLAVE = "123";
	private Connection con;
	
	static {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public PersonaHandler() throws SQLException {
		con = DriverManager.getConnection(URL, USUARIO, CLAVE);
	}
	
	public void agregar(Persona p) throws SQLException {
		String query = "INSERT INTO persona"
				+ "(nombre, apellido, fecha_nacimiento, altura) VALUES('" + 
				p.getNombre() + "', '" + 
				p.getApellido() + "', '" +
				p.getFechaNacimientoStr() + "', " +
				p.getAltura() + ")";
		
		Statement stmt = con.createStatement();
		stmt.executeUpdate(query);
	}

	public void listarPersonasSegunAltura() throws SQLException {
		String query = "SELECT * FROM persona ORDER BY altura ASC";
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		imprimirPersonas(rs);
	}

	private void imprimirPersonas(ResultSet rs) throws SQLException {
		while(rs.next()) {
			System.out.println("\nPersona");
			System.out.println("id = " + rs.getInt("id"));
			System.out.println("nombre = " + rs.getString("nombre"));
			System.out.println("apellido = " + rs.getString("apellido"));
			System.out.println("fechaNacimiento = " + rs.getDate("fecha_nacimiento"));
			System.out.println("altura = " + rs.getFloat("altura"));
		}
	}
	
	public void listarPersonasSegunEdad() throws SQLException {
		String query = "SELECT * FROM persona ORDER BY fecha_nacimiento ASC";
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		imprimirPersonas(rs);
	}

	public void obtenerPersonaMasJoven() throws SQLException {
		String query = "SELECT * FROM persona ORDER BY fecha_nacimiento DESC OFFSET 1 ROWS FETCH NEXT ROW ONLY";
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		imprimirPersonas(rs);
	}
	
	public void cerrar() throws SQLException {
		if (con != null) {
			con.close();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		cerrar();
	}
}
