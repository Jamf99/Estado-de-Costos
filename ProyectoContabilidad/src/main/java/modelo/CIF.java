package modelo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class CIF {
	
	private SimpleStringProperty tipoCIF;
	private SimpleDoubleProperty valor;
		
	public CIF(String tipoCIF, double valor) {
		this.tipoCIF = new SimpleStringProperty(tipoCIF);
		this.valor = new SimpleDoubleProperty(valor);
	}

	public String getTipoCIF() {
		return tipoCIF.get();
	}

	public void setTipoCompra(String tipoCIF) {
		this.tipoCIF = new SimpleStringProperty(tipoCIF);
	}

	public double getValor() {
		return valor.get();
	}

	public void setValor(double valor) {
		this.valor = new SimpleDoubleProperty(valor);
	}

}
