package es.indra.formacion.pr.jdbc.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Persona implements Serializable {
	private static final long serialVersionUID = -2942233358558244767L;
	
	private Integer id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private Float altura;

	public Persona() { 
		this(null, null, null, null);
	}
	
	public Persona(String nombre, String apellido, Date fechaNacimiento, Float altura) {
		this(null, nombre, apellido, fechaNacimiento, altura);
	}
	
	public Persona(Integer id, String nombre, String apellido, Date fechaNacimiento, Float altura) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.altura = altura;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getFechaNacimientoStr() {
		return new SimpleDateFormat("yyyy-MM-dd").format(fechaNacimiento);
	}
	
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Float getAltura() {
		return altura;
	}
	public void setAltura(Float altura) {
		this.altura = altura;
	}
	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", apellido="
				+ apellido + ", fechaNacimiento=" + fechaNacimiento
				+ ", altura=" + altura + "]";
	}
}
