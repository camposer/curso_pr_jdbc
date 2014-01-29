package es.indra.formacion.pr.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PrincipalConOrdenador {
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
	
	public PrincipalConOrdenador() throws SQLException {
		con = DriverManager.getConnection(URL, USUARIO, CLAVE);
	}
	
	public void iniciar() throws SQLException {
		String queryPersona = "INSERT INTO persona(nombre, apellido, fecha_nacimiento, altura) "
				+ "VALUES(?, ?, ?, ?)";
		String queryOrdenador = "INSERT INTO ordenador(nombre, persona_id, serial) "
				+ "VALUES(?, ?, ?)";
						
		try {
			// Habilitando transacciones
			con.setAutoCommit(false);
			
			// Agregando persona
			PreparedStatement pstmt = con.prepareStatement(queryPersona, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, "nombre");
			pstmt.setString(2, "apellido");
			pstmt.setDate(3, new java.sql.Date(new Date().getTime()));
			pstmt.setFloat(4, 180);
			pstmt.execute();
			
			// Capturando id recién creado
			ResultSet rs = pstmt.getGeneratedKeys();
			Integer personaId = null; 
			if (rs.next())
				personaId = rs.getInt(1);
				
			// Agregando ordenador asociado
			pstmt = con.prepareStatement(queryOrdenador);
			pstmt.setString(1, "nombre");
			pstmt.setInt(2, personaId);
			pstmt.setString(3, "serial");
			pstmt.execute();
			
			// Comprometiendo transacción si todo fue exitoso
			con.commit();
		} catch(Exception e) {
			// Deshaciendo transacción si hubo un error
			con.rollback();
		} finally {
			cerrar();
		}
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

	public static void main(String[] args) throws SQLException {
		PrincipalConOrdenador pco = new PrincipalConOrdenador();
		pco.iniciar();
	}

}
