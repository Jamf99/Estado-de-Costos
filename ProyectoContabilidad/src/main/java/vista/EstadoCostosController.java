package vista;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import excepciones.CampoVacioException;
import excepciones.NumeroNegativoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.CIF;
import modelo.ComprasMD;
import modelo.EstadoResultados;

public class EstadoCostosController implements Initializable {
	
     DecimalFormat formateador = new DecimalFormat("###'###,###.##");
	
	 @FXML
	 private TextField inventarioInicialMD;

	 @FXML
	 private Label mes;

	 @FXML
	 private Label anio;

	 @FXML
	 private ComboBox<String> tipoCompraMD;

	 @FXML
	 private TextField valorCompra;

	 @FXML
	 private Button agregarCompraMD;

	 @FXML
	 private Label nombreEmpresa;
	 
	 @FXML
	 private TableColumn<ComprasMD, String> columnaElemento;

	 @FXML
	 private TableColumn<ComprasMD, Double> columnaValor;
	 
	 @FXML
	 private TableView<ComprasMD> compraMD;
	 
	 private ObservableList<ComprasMD> listaMD = FXCollections.observableArrayList();
	 
	 private ObservableList<CIF> listaCIF = FXCollections.observableArrayList();
	 
	 @FXML
	 private TextField inventarioFinalMD;

	 @FXML
	 private Label consumoMD;

	 @FXML
	 private TextField MOD;

	 @FXML
	 private TextField valorCIF;

	 @FXML
	 private Button agregarCIF;
	 
	 @FXML
	 private TableView<CIF> tablaCIF;

	 @FXML
	 private TextField tipoCIF;

	 @FXML
	 private Label costoAgregadoProduccion;

	 @FXML
	 private TextField inventarioInicialPP;

	 @FXML
	 private TextField inventarioFinalPP;

	 @FXML
	 private Label costoTotalPT;

	 @FXML
	 private TextField inventarioInicialPT;

	 @FXML
	 private TextField inventarioFinalPT;

	 @FXML
	 private Label costoDeVenta;

	 @FXML
	 private TextField gastosEnAdministracion;

	 @FXML
	 private TextField gastosMarketingDistribucion;

	 @FXML
	 private TextField impuestoVenta;

	 @FXML
	 private Label totalCompraMD;

	 @FXML
	 private Label totalCIF;
	 
	 @FXML
	 private TableColumn<CIF, String> columnaCIF;

	 @FXML
	 private TableColumn<CIF, Double> columnaCosto;
	 
	 @FXML
	 private TextField ventas;
		 
	 private double cantidadCompra = 0;
	 
	 private double cantidadCIF = 0;
	 
	 public double darInventarioInicialMD() throws CampoVacioException, NumeroNegativoException{
		if(inventarioInicialMD.getText().equals("")) {
			throw new CampoVacioException();
		}else if(Double.parseDouble(inventarioInicialMD.getText()) < 0){
			throw new NumeroNegativoException();
		}
		return Double.parseDouble(inventarioInicialMD.getText());
	 }
	 
	 public double darInventarioFinalMD() throws CampoVacioException, NumeroNegativoException {
		 if(inventarioFinalMD.getText().equals("")) {
		 	 throw new CampoVacioException();
		 }else if(Double.parseDouble(inventarioFinalMD.getText()) < 0){
				 throw new NumeroNegativoException();
		 }
		 return Double.parseDouble(inventarioFinalMD.getText());
	 }
	 
	 @FXML
	 void refrescarConsumoMDPorInventarioInicialMD(MouseEvent event) {
		 if(!inventarioFinalMD.getText().equals("")) {
			 refrescarConsumoMD();
		 }
	 }
	 
	 public void refrescarConsumoMD() {
		 try {
			 double resultado = darInventarioInicialMD() + cantidadCompra - darInventarioFinalMD();
			 if(resultado >= 0) {
				 consumoMD.setText(formateador.format(resultado)+"");
			 }else {
				 Alert alert = new Alert(AlertType.ERROR);
		         alert.setTitle("Error");
		         alert.setHeaderText("El consumo de materiales directos no pueden ser negativos");
		       	 alert.showAndWait();
			 }
		 }catch(CampoVacioException s) {
			 Alert alert = new Alert(AlertType.ERROR);
	         alert.setTitle("Error");
	         alert.setHeaderText("Empty fields");
	       	 alert.setContentText(s.getMessage());
	       	 alert.showAndWait();
		 }catch(NumeroNegativoException e) {
			 Alert alert = new Alert(AlertType.ERROR);
			 alert.setTitle("Error");
			 alert.setHeaderText("Dato inválido");
			 alert.setContentText(e.getMessage());
			 alert.showAndWait();
		 }catch(NumberFormatException n) {
			 Alert alert = new Alert(AlertType.ERROR);
			 alert.setTitle("Error");
			 alert.setHeaderText("Dato inválido");
			 alert.setContentText(n.getMessage());
			 alert.showAndWait();
		 }	
	 }
	 
	 @FXML
	 void refrescarConsumoMDPorInventarioFinalMD(MouseEvent event) {
		refrescarConsumoMD();
	 }

	 @FXML
	 void agregarCompraMD(ActionEvent event) {
		 try {
			 if(Double.parseDouble(valorCompra.getText()) <= 0) {
				 Alert alert = new Alert(AlertType.ERROR);
				 alert.setTitle("Error");
				 alert.setHeaderText("No es posible ingresar un número negativo");
				 alert.showAndWait();
			 }else {
				 listaMD.add(new ComprasMD(tipoCompraMD.getSelectionModel().getSelectedItem(), Double.parseDouble(valorCompra.getText())));
				 if(tipoCompraMD.getSelectionModel().getSelectedItem().equals(ComprasMD.DEVOLUCIONES) ||
		 	 		 tipoCompraMD.getSelectionModel().getSelectedItem().equals(ComprasMD.DESCUENTOS)) {
					 cantidadCompra -= Double.parseDouble(valorCompra.getText());
				 }else {
					 cantidadCompra += Double.parseDouble(valorCompra.getText());
				 }
				 calcularTotalComprasMD();
				 if(!inventarioFinalMD.getText().equals("")) {
					 refrescarConsumoMD();
				 }
			 }
		 }catch(NumberFormatException s) {
			 Alert alert = new Alert(AlertType.ERROR);
	         alert.setTitle("Error");
	         alert.setHeaderText("Invalid input");
	       	 alert.setContentText(s.getMessage());
	       	 alert.showAndWait();
		 }
	 }
	 
	 public void calcularTotalComprasMD() {
		 totalCompraMD.setText(formateador.format(cantidadCompra)+"");
	 }
	 
	 public double darMOD() throws CampoVacioException, NumeroNegativoException {
		 if(MOD.getText().equals("")) {
		 	 throw new CampoVacioException();
		 }else if(Double.parseDouble(MOD.getText()) < 0){
				 throw new NumeroNegativoException();
		 }
		 return Double.parseDouble(MOD.getText());
	 }
	 
	 @FXML
	 void refrescarCostoAgregadoProduccionPorMOD(MouseEvent event) {
		 refrescarCostoAgregadoDeProduccion();
	 }
	 
	 public void refrescarCostoAgregadoDeProduccion() {
		 try {
			 double resultado = Double.parseDouble(consumoMD.getText()) + darMOD() + cantidadCIF;
			 if(resultado >= 0) {
				 costoAgregadoProduccion.setText(formateador.format(resultado)+"");
			 }else {
				 Alert alert = new Alert(AlertType.ERROR);
		         alert.setTitle("Error");
		         alert.setHeaderText("El costo agregado de producción no puede ser negativo");
		       	 alert.showAndWait();
			 }
		 }catch(CampoVacioException s) {
			 Alert alert = new Alert(AlertType.ERROR);
	         alert.setTitle("Error");
	         alert.setHeaderText("Empty fields");
	       	 alert.setContentText(s.getMessage());
	       	 alert.showAndWait();
		 }catch(NumeroNegativoException e) {
			 Alert alert = new Alert(AlertType.ERROR);
			 alert.setTitle("Error");
			 alert.setHeaderText("Dato inválido");
			 alert.setContentText(e.getMessage());
			 alert.showAndWait();
		 }catch(NumberFormatException n) {
			 Alert alert = new Alert(AlertType.ERROR);
			 alert.setTitle("Error");
			 alert.setHeaderText("Dato inválido");
			 alert.setContentText(n.getMessage());
			 alert.showAndWait();
		 }
	 }
	 
	 @FXML
	 void agregarCIF(ActionEvent event) {
		 try {
			 if(Double.parseDouble(valorCIF.getText()) <= 0) {
				 Alert alert = new Alert(AlertType.ERROR);
				 alert.setTitle("Error");
				 alert.setHeaderText("No es posible insertar un número negativo");
				 alert.showAndWait();
			 }else {
				 listaCIF.add(new CIF(tipoCIF.getText(), Double.parseDouble(valorCIF.getText())));
				 cantidadCIF += Double.parseDouble(valorCIF.getText());
				 calcularTotalCIF();
				 refrescarCostoAgregadoDeProduccion();
			 }
		 }catch(NumberFormatException s) {
			 Alert alert = new Alert(AlertType.ERROR);
	         alert.setTitle("Error");
	         alert.setHeaderText("Invalid input");
	       	 alert.setContentText(s.getMessage());
	       	 alert.showAndWait();
		 }
	 }
	 
	 public void refrescarCostoTotalPT() {
		 try {
			 double valor = Double.parseDouble(costoAgregadoProduccion.getText()) + darInventarioInicialPP() - darInventarioFinalPP();
			 if(valor < 0) {
				 Alert alert = new Alert(AlertType.ERROR);
		         alert.setTitle("Error");
		         alert.setHeaderText("El costo total del producto terminado no puede ser negativo");
		       	 alert.showAndWait();
			 }else {
				 costoTotalPT.setText(valor+"");
			 }
		 }catch(CampoVacioException s) {
			 Alert alert = new Alert(AlertType.ERROR);
	         alert.setTitle("Error");
	         alert.setHeaderText("Empty fields");
	       	 alert.setContentText(s.getMessage());
	       	 alert.showAndWait();
		 }catch(NumeroNegativoException e) {
			 Alert alert = new Alert(AlertType.ERROR);
			 alert.setTitle("Error");
			 alert.setHeaderText("Dato inválido");
			 alert.setContentText(e.getMessage());
			 alert.showAndWait();
		 }catch(NumberFormatException n) {
			 Alert alert = new Alert(AlertType.ERROR);
			 alert.setTitle("Error");
			 alert.setHeaderText("Dato inválido");
			 alert.setContentText(n.getMessage());
			 alert.showAndWait();
		 } 
	 }
	 
	 public double darInventarioInicialPP() throws CampoVacioException, NumeroNegativoException {
		 if(inventarioInicialPP.getText().equals("")) {
		 	 throw new CampoVacioException();
		 }else if(Double.parseDouble(inventarioInicialPP.getText()) < 0){
				 throw new NumeroNegativoException();
		 }
		 return Double.parseDouble(inventarioInicialPP.getText());
	 }
	 
	 public double darInventarioFinalPP() throws CampoVacioException, NumeroNegativoException {
		 if(inventarioFinalPP.getText().equals("")) {
		 	 throw new CampoVacioException();
		 }else if(Double.parseDouble(inventarioFinalPP.getText()) < 0){
				 throw new NumeroNegativoException();
		 }
		 return Double.parseDouble(inventarioFinalPP.getText());
	 }
	 
	 @FXML
	 void refrescarCostoTotalPTPorInventarioFinalPP(MouseEvent event) {
		 if(!inventarioInicialPP.getText().equals("")) {
			 refrescarCostoTotalPT();
		 }
	 }

	 @FXML
	 void refrescarCostoTotalPTPorInventarioInicialPP(MouseEvent event) {
		 if(!inventarioFinalPP.getText().equals("")) {
			 refrescarCostoTotalPT();
		 }
	 }
	 
	 public void calcularTotalCIF() {
		 totalCIF.setText(formateador.format(cantidadCIF+""));	 
	 }
	 
	 public void recibeDatos2(String mes, int anio, String empresa) {
		 nombreEmpresa.setText(empresa);
		 this.anio.setText(anio+"");
		 this.mes.setText(mes);
	 }
	 
	 public double darInventarioInicialPT() throws CampoVacioException, NumeroNegativoException {
		 if(inventarioInicialPT.getText().equals("")) {
		 	 throw new CampoVacioException();
		 }else if(Double.parseDouble(inventarioInicialPT.getText()) < 0){
				 throw new NumeroNegativoException();
		 }
		 return Double.parseDouble(inventarioInicialPT.getText());
	 }
	 
	 public double darInventarioFinalPT() throws CampoVacioException, NumeroNegativoException {
		 if(inventarioFinalPT.getText().equals("")) {
		 	 throw new CampoVacioException();
		 }else if(Double.parseDouble(inventarioFinalPT.getText()) < 0){
				 throw new NumeroNegativoException();
		 }
		 return Double.parseDouble(inventarioFinalPT.getText());
	 }
	 
	 public void refrescarCostoDeVenta() {
		 try {
			 double valor = Double.parseDouble(costoTotalPT.getText()) + darInventarioInicialPT() - darInventarioFinalPT();
			 if(valor < 0) {
				 Alert alert = new Alert(AlertType.ERROR);
		         alert.setTitle("Error");
		         alert.setHeaderText("El costo de venta no puede ser negativo");
		       	 alert.showAndWait();
			 }else {
				 costoDeVenta.setText(formateador.format(valor)+"");
			 }
		 }catch(CampoVacioException s) {
			 Alert alert = new Alert(AlertType.ERROR);
	         alert.setTitle("Error");
	         alert.setHeaderText("Empty fields");
	       	 alert.setContentText(s.getMessage());
	       	 alert.showAndWait();
		 }catch(NumeroNegativoException e) {
			 Alert alert = new Alert(AlertType.ERROR);
			 alert.setTitle("Error");
			 alert.setHeaderText("Dato inválido");
			 alert.setContentText(e.getMessage());
			 alert.showAndWait();
		 }catch(NumberFormatException n) {
			 Alert alert = new Alert(AlertType.ERROR);
			 alert.setTitle("Error");
			 alert.setHeaderText("Dato inválido");
			 alert.setContentText(n.getMessage());
			 alert.showAndWait();
		 } 
	 }
	 
	 @FXML
	 void refrescarCostoVentaPorInventarioFinalPT(MouseEvent event) {
		 if(!inventarioInicialPT.getText().equals("")) {
			 refrescarCostoDeVenta();
		 }
	 }

	 @FXML
	 void refrescarCostoVentaPorInventarioInicialPT(MouseEvent event) {
		 if(!inventarioFinalPT.getText().equals("")) {
			 refrescarCostoDeVenta();
		 }
	 }
	 	 
	 public double darGastosEnAdministracion() throws CampoVacioException, NumeroNegativoException {
		 if(gastosEnAdministracion.getText().equals("")) {
		 	 throw new CampoVacioException();
		 }else if(Double.parseDouble(gastosEnAdministracion.getText()) < 0){
				 throw new NumeroNegativoException();
		 }
		 return Double.parseDouble(gastosEnAdministracion.getText());
	 }
	 
	 public double darGastosEnMarketingYDistribucion() throws CampoVacioException, NumeroNegativoException {
		 if(gastosMarketingDistribucion.getText().equals("")) {
		 	 throw new CampoVacioException();
		 }else if(Double.parseDouble(gastosMarketingDistribucion.getText()) < 0){
				 throw new NumeroNegativoException();
		 }
		 return Double.parseDouble(gastosMarketingDistribucion.getText());
	 }
	 
	 public double darVentas() throws CampoVacioException, NumeroNegativoException {
		 if(ventas.getText().equals("")) {
		 	 throw new CampoVacioException();
		 }else if(Double.parseDouble(ventas.getText()) < 0){
				 throw new NumeroNegativoException();
		 }
		 return Double.parseDouble(ventas.getText());
	 }
	 
	 public double darImpuestoVenta() throws CampoVacioException, NumeroNegativoException {
		 if(impuestoVenta.getText().equals("")) {
		 	 throw new CampoVacioException();
		 }else if(Double.parseDouble(impuestoVenta.getText()) < 0){
				 throw new NumeroNegativoException();
		 }else if(Double.parseDouble(impuestoVenta.getText()) >  1) {
			 throw new CampoVacioException("Error");
		 }
		 return Double.parseDouble(impuestoVenta.getText());
	 }
	 
	 @FXML
	 void irEstadoResultados(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/vista/estadoResultados.fxml"));
			loader.load();
			EstadoResultadosController estado = loader.getController();
			
			EstadoResultados er = new EstadoResultados(darVentas(), darImpuestoVenta());
			er.crearEstadoCostos(nombreEmpresa.getText(), mes.getText(), Integer.parseInt(anio.getText()), 
					darInventarioInicialMD(), cantidadCompra, darInventarioFinalMD(), darMOD(), cantidadCIF, darInventarioInicialPP(),
					darInventarioFinalPP(), darInventarioInicialPT(), darInventarioFinalPT());
			er.crearCostosNoManufactura(darGastosEnAdministracion(), darGastosEnMarketingYDistribucion());
			estado.refrescarDatos(er.getEstadoCostos().getNombreEmpresa(), er.getEstadoCostos().getMes(), er.getEstadoCostos().getAnio(),
					er.getVentas(), er.getEstadoCostos().calcularCostoDeVenta(), er.calcularUtilidadBruta(), er.getCostosNoManufactura().calcularCostosDelPeriodo(),
					er.calcularUtilidadOperativa(), er.getPorcentajeImpuestos(), er.calcularUtilidadNeta());
			
			Parent p = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(p));
			stage.setTitle("Estado Resultados");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/vista/dolar.jpg")));
			stage.showAndWait();
		}catch(IOException s) {
			s.printStackTrace();
		}catch(NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Dato inválido");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}catch(NumeroNegativoException n) {
			Alert alert = new Alert(AlertType.ERROR);
			 alert.setTitle("Error");
			 alert.setHeaderText("Dato inválido");
			 alert.setContentText(n.getMessage());
			 alert.showAndWait();
		}catch(CampoVacioException k) {
			Alert alert = new Alert(AlertType.ERROR);
			 alert.setTitle("Error");
			 alert.setHeaderText("Empty field");
			 alert.setContentText(k.getMessage());
			 alert.showAndWait();
		}
	 }
	 
	 @Override
	 public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> states = FXCollections.observableArrayList(ComprasMD.FLETES_COMPRA, ComprasMD.DESCUENTOS, ComprasMD.DEVOLUCIONES);
		tipoCompraMD.setItems(states);
		tipoCompraMD.getSelectionModel().select(0);
		
		columnaElemento.setCellValueFactory(new PropertyValueFactory<ComprasMD, String>("tipoCompra"));
		columnaValor.setCellValueFactory(new PropertyValueFactory<ComprasMD, Double>("valor"));
		compraMD.setItems(listaMD);
		
		columnaCIF.setCellValueFactory(new PropertyValueFactory<CIF, String>("tipoCIF"));
		columnaCosto.setCellValueFactory(new PropertyValueFactory<CIF, Double>("valor"));
		tablaCIF.setItems(listaCIF);
	}

}
