package modelo;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class ComprasMD {
		
	public final static String FLETES_COMPRA = "Fletes compra (+)";
	public final static String DESCUENTOS = "Descuentos (-)";
	public final static String DEVOLUCIONES = "Devoluciones (-)";
		
	private SimpleStringProperty tipoCompra;
	private SimpleDoubleProperty valor;
		
	public ComprasMD(String tipoCompra, double valor) {
		this.tipoCompra = new SimpleStringProperty(tipoCompra);
		this.valor = new SimpleDoubleProperty(valor);
	}

	public String getTipoCompra() {
		return tipoCompra.get();
	}

	public void setTipoCompra(String tipoCompra) {
		this.tipoCompra = new SimpleStringProperty(tipoCompra);
	}

	public double getValor() {
		return valor.get();
	}

	public void setValor(double valor) {
		this.valor = new SimpleDoubleProperty(valor);
	}
	
}