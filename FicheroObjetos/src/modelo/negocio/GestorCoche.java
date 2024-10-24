package modelo.negocio;

import java.util.ArrayList;

import modelo.entidad.Coche;
import modelo.persistencia.DaoCoche;

public class GestorCoche {

	private DaoCoche dc;

	/**
	 * Metodo que guarda un coche
	 * 
	 * @param c Coche a guardar
	 * @return <b>0</b> el coche pasado por parametro es null, <b>1</b> el nombre
	 *         esta vacio o solo tiene espacios en blanco, <b>2</b> la marca esta
	 *         vacio o solo tiene espacios en blanco, <b>3</b> el modelo esta en
	 *         blanco o solo tiene espacios vacios <b>4</b> el TipoMotor esta vacio
	 *         o solo tiene espacios en blanco, <b>5</b> el coche se ha guardado
	 *         <b>666</b> en caso de que haya algún problema de entrada salida
	 */
	public int guardarCoche(Coche c) {
		if (c == null) {
			return 0;
		}
		dc = new DaoCoche();

		try {
			if (c.getNombre().isBlank()) {
				return 1;
			} else if (c.getMarca().isBlank()) {
				return 2;
			} else if (c.getModelo().isBlank()) {
				return 3;
			} else if (c.getTipoMotor().toString().isBlank()) {
				return 4;
			} else {
				dc.registrar(c);
				return 5;
			}
		} catch (Exception e) {
			return 666;
		}
	}

	public int borrarCoche(Coche c) {
		
		
		return 4;
	}

	public ArrayList<Coche> mostrarListaCoches() {
		return null;

	}

}
