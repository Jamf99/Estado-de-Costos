package excepciones;

public class FechaException extends Exception {

	public FechaException() {
		super("La fecha final no puede ser menor a la fecha inicial");
	}
	
}
