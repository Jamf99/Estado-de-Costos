package vista;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import excepciones.CampoVacioException;
import excepciones.FechaException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LobbyController implements Initializable{
	
	@FXML
	public DatePicker fechaInicio;

	@FXML
	private DatePicker fechaFinal;

	@FXML
	private TextField nombreEmpresa;

	@FXML
	private Button butEmpezar;
	
	@FXML
    void abrirEstadoCostos(ActionEvent event) {
		try {
			darFechaFinal();
			darFechaInicial();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/vista/estadoCostos.fxml"));
			loader.load();
			EstadoCostosController estado = loader.getController();
			estado.recibeDatos2(fechaInicio.getValue().getMonth().name(), fechaInicio.getValue().getYear(), darNombreEmpresa());
			verifyDate();
			Parent p = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(p));
			stage.setTitle("Estado Costos");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/vista/compra.JPG")));
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(CampoVacioException s) {
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Empty fields");
        	alert.setHeaderText("Error");
        	alert.setContentText(s.getMessage());
        	alert.showAndWait();
		} catch(FechaException f) {
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Empty fields");
        	alert.setHeaderText("Error");
        	alert.setContentText(f.getMessage());
        	alert.showAndWait();
		}
    }
	
	public String darNombreEmpresa() throws CampoVacioException{
		if(nombreEmpresa.getText().equals("")) {
			throw new CampoVacioException();
		}else {
			return nombreEmpresa.getText();
		}
	}
	
	public String darFechaInicial() throws CampoVacioException {
		if((fechaInicio.getValue() == null)) {
			throw new CampoVacioException();
		}else {
			return fechaInicio.getValue().getDayOfMonth()+"-"+fechaInicio.getValue().getMonthValue()+"-"+fechaInicio.getValue().getYear();
		}
	}
	
	public String darFechaFinal() throws CampoVacioException {
		if((fechaFinal.getValue() == null)) {
			throw new CampoVacioException();
		}else {
			return fechaFinal.getValue().getDayOfMonth()+"-"+fechaFinal.getValue().getMonthValue()+"-"+fechaFinal.getValue().getYear();
		}
	}
	
	public boolean verifyDate() throws FechaException {
		boolean b = false;
		if(fechaFinal.getValue().getYear() <= fechaInicio.getValue().getYear()) {
			if(fechaFinal.getValue().getMonthValue() <= fechaInicio.getValue().getMonthValue()) {
				if(fechaFinal.getValue().getDayOfMonth() < fechaInicio.getValue().getDayOfMonth()) {
					b = false;
				}else {
					b = true;
				}
			}else {
				b = true;
			}
		}else {
			b = true;
		}
		if(b == false) {
			throw new FechaException();
		}
		return b;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}