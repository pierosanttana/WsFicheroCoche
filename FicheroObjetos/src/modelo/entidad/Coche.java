package modelo.entidad;

import java.io.Serializable;
import java.util.Objects;

public class Coche implements Serializable{
	
    private static final long serialVersionUID = 1L;
    private int id;
	private String nombre;
	private String marca;
	private String modelo;
	private TipoMotor tipoMotor;
	
	
	public Coche(String nombre, String marca, String modelo, modelo.entidad.TipoMotor tipoMotor) {
		super();
		this.nombre = nombre;
		this.marca = marca;
		this.modelo = modelo;
		this.tipoMotor = tipoMotor;
	}
	public long getID() {
		return id;
	}
	public void setID(int iD) {
		id = iD;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public TipoMotor getTipoMotor() {
		return tipoMotor;
	}
	public void setTipoMotor(TipoMotor tipoMotor) {
		this.tipoMotor = tipoMotor;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, tipoMotor, marca, modelo, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		return id == other.id && tipoMotor == other.tipoMotor && Objects.equals(marca, other.marca)
				&& Objects.equals(modelo, other.modelo) && Objects.equals(nombre, other.nombre);
	}
	@Override
	public String toString() {
	    return "Coche {" + "\n" +
	           "   ID        = " + id + ",\n" +
	           "   Nombre    = " + nombre + ",\n" +
	           "   Marca     = " + marca + ",\n" +
	           "   Modelo    = " + modelo + ",\n" +
	           "   TipoMotor = " + tipoMotor + "\n" +
	           "}";
	}

	

}
