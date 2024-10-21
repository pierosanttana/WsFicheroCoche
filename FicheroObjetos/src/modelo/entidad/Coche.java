package modelo.entidad;

import java.util.Objects;

public class Coche {
	private long ID;
	private String nombre;
	private String marca;
	private String modelo;
	private TipoMotor TipoMotor;
	public Coche(long iD, String nombre, String marca, String modelo, modelo.entidad.TipoMotor tipoMotor) {
		super();
		ID = iD;
		this.nombre = nombre;
		this.marca = marca;
		this.modelo = modelo;
		TipoMotor = tipoMotor;
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
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
		return TipoMotor;
	}
	public void setTipoMotor(TipoMotor tipoMotor) {
		TipoMotor = tipoMotor;
	}
	@Override
	public int hashCode() {
		return Objects.hash(ID, TipoMotor, marca, modelo, nombre);
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
		return ID == other.ID && TipoMotor == other.TipoMotor && Objects.equals(marca, other.marca)
				&& Objects.equals(modelo, other.modelo) && Objects.equals(nombre, other.nombre);
	}
	@Override
	public String toString() {
	    return "Coche {" + "\n" +
	           "   ID        = " + ID + ",\n" +
	           "   Nombre    = " + nombre + ",\n" +
	           "   Marca     = " + marca + ",\n" +
	           "   Modelo    = " + modelo + ",\n" +
	           "   TipoMotor = " + TipoMotor + "\n" +
	           "}";
	}

	

}
