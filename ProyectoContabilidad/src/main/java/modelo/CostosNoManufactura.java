package modelo;

public class CostosNoManufactura {
	
	private double gastosEnAdministracion;
	private double gastosMarketingDistribucion;
		
	public CostosNoManufactura(double gastosEnAdministracion, double gastosMarketingDistribucion) {
		super();
		this.gastosEnAdministracion = gastosEnAdministracion;
		this.gastosMarketingDistribucion = gastosMarketingDistribucion;
	}

	public double getGastosEnAdministracion() {
		return gastosEnAdministracion;
	}
	
	public double getGastosMarketingDistribucion() {
		return gastosMarketingDistribucion;
	}
	
	public double calcularCostosDelPeriodo() {
		return gastosEnAdministracion + gastosMarketingDistribucion;
	}

}
