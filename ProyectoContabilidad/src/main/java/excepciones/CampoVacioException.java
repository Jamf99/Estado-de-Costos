package excepciones;

public class CampoVacioException extends Exception {
	
	public CampoVacioException() {
		super("Hay un campo vacío, por favor rellénelos todos");
	}
	
	public CampoVacioException(String ms) {
		super("El impuesto de venta debe ser un número entre 0 y 1");
	}

}
