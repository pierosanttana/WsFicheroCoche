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

	/**
	 * Metodo que borrar un coche pasado por parametro
	 * 
	 * @param c Coche a borrar
	 * @return <b>0</b> el Coche es null, <b>1</b> el Coche es valido pero no se
	 *         encuentra guardado, <b>2</b> el Coche se ha borrado, <b>666</b> en
	 *         caso de que haya algún problema en el de entrada salida
	 */
	public int borrarCoche(Coche c) {

		if (c == null) {
			return 0;
		}

		dc = new DaoCoche();

		try {
			Coche cBorrar = dc.getById(c.getID());

			if (cBorrar == null) {
				return 1;
			} else {
				dc.borrarCocheById(cBorrar.getID());
				return 2;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 666;
		}

	}

	/**
	 * * Método que devuelve una lista de coches. Si no hay coches o la lista es
	 * null, devuelve una lista vacía.
	 * 
	 * @return <b>Array</b> de coches o un <b>Array</b> vacía si no hay coches.
	 * @throws <b>Exception</b> si ocurre algún error al intentar recuperar la lista
	 */
	public ArrayList<Coche> mostrarListaCoches() throws Exception {
		dc = new DaoCoche();

		ArrayList<Coche> listCoches = dc.getListaCoches();

		if (listCoches == null) {
			return new ArrayList<>();
		} else {
			return listCoches;
		}
	}

}
