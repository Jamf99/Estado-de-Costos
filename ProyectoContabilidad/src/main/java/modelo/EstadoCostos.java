package modelo;

public class EstadoCostos {
	
	private String nombreEmpresa;
	private String mes;
	private int anio;
	private double inventarioInicialMD;
	private double comprasMD;
	private double inventarioFinalMD;
	private double manoObra;
	private double cif;
	private double inventarioInicialPP;
	private double inventarioFinalPP;
	private double inventarioInicialPT;
	private double inventarioFinalPT;
		
	public EstadoCostos(String nombreEmpresa, String mes, int anio, double inventarioInicialMD, double comprasMD, double inventarioFinalMD, double manoObra,
			double cif, double inventarioInicialPP, double inventarioFinalPP, double inventarioInicialPT,
			double inventarioFinalPT) {
		super();
		this.nombreEmpresa = nombreEmpresa;
		this.mes = mes;
		this.anio = anio; 
		this.inventarioInicialMD = inventarioInicialMD;
		this.comprasMD = comprasMD;
		this.inventarioFinalMD = inventarioFinalMD;
		this.manoObra = manoObra;
		this.cif = cif;
		this.inventarioInicialPP = inventarioInicialPP;
		this.inventarioFinalPP = inventarioFinalPP;
		this.inventarioInicialPT = inventarioInicialPT;
		this.inventarioFinalPT = inventarioFinalPT;
	}
	
	public double calcularConsumoMD() {
		return inventarioInicialMD + comprasMD - inventarioFinalMD;
	}
	
	public double calcularCostosAgregadosDeProduccion() {
		return calcularConsumoMD() + manoObra + cif;
	}
	
	public double calcularCostoTotalProductoTerminado() {
		return calcularCostosAgregadosDeProduccion() + inventarioInicialPP - inventarioFinalPP;
	}
	
	public double calcularCostoDeVenta() {
		return calcularCostoTotalProductoTerminado() + inventarioInicialPT - inventarioFinalPT;
	}
	
	public double getInventarioInicialMD() {
		return inventarioInicialMD;
	}
	
	public double getComprasMD() {
		return comprasMD;
	}
	
	public double getInventarioFinalMD() {
		return inventarioFinalMD;
	}
	
	public double getManoObra() {
		return manoObra;
	}
	
	public double getCif() {
		return cif;
	}
	
	public double getInventarioInicialPP() {
		return inventarioInicialPP;
	}
	
	public double getInventarioFinalPP() {
		return inventarioFinalPP;
	}
	
	public double getInventarioInicialPT() {
		return inventarioInicialPT;
	}
	
	public double getInventarioFinalPT() {
		return inventarioFinalPT;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}
		
}
