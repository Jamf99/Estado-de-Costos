package excepciones;

public class NumeroNegativoException extends Exception{
	
	public NumeroNegativoException() {
		super("No es posible insertar un número negativo");
	}

}
