package modelo;

public class EstadoResultados {
	
	private double ventas;
	private double porcentajeImpuestos;
	
	private EstadoCostos estadoCostos;
	private CostosNoManufactura costosNoManufactura;
	
	public EstadoResultados(double ventas, double porcentajeImpuestos) {
		this.ventas = ventas;
		this.porcentajeImpuestos = porcentajeImpuestos;
	}
	
	public void crearEstadoCostos(String nombreEmpresa, String mes, int anio, double inventarioInicialMD, double comprasMD, double inventarioFinalMD, double manoObra,
			double cif, double inventarioInicialPP, double inventarioFinalPP, double inventarioInicialPT,
			double inventarioFinalPT) {
		estadoCostos = new EstadoCostos(nombreEmpresa, mes, anio, inventarioInicialMD, comprasMD, inventarioFinalMD, manoObra, cif, inventarioInicialPP,
				inventarioFinalPP, inventarioInicialPT, inventarioFinalPT);
	}
	
	public void crearCostosNoManufactura(double gastosEnAdministracion, double gastosMarketingDistribucion) {
		costosNoManufactura = new CostosNoManufactura(gastosEnAdministracion, gastosMarketingDistribucion);
	}
	
	public double calcularUtilidadBruta() {
		return ventas - estadoCostos.calcularCostoDeVenta();
	}
	
	public double calcularUtilidadOperativa() {
		return calcularUtilidadBruta() - costosNoManufactura.calcularCostosDelPeriodo();	
	}
	
	public double calcularUtilidadNeta() {
		return calcularUtilidadOperativa() - ventas*porcentajeImpuestos;
	}

	public double getPorcentajeImpuestos() {
		return porcentajeImpuestos;
	}
	
	public double getVentas() {
		return ventas;
	}

	public EstadoCostos getEstadoCostos() {
		return estadoCostos;
	}

	public CostosNoManufactura getCostosNoManufactura() {
		return costosNoManufactura;
	}
	
}
