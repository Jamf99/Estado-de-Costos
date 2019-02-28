package vista;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EstadoResultadosController {

    @FXML
    private Label empresa;

    @FXML
    private Label mes;

    @FXML
    private Label anio;

    @FXML
    private Label ventas;

    @FXML
    private Label costoVentas;

    @FXML
    private Label utilidadBruta;

    @FXML
    private Label gastosOperativos;

    @FXML
    private Label utilidadOperativa;

    @FXML
    private Label impuestos;

    @FXML
    private Label utilidadNeta;
    
    public void refrescarDatos(String empresa, String mes, int anio, double ventas, double costoVentas, double utilidadBruta,
    		double gastosOperativos, double utilidadOperativa, double impuestos, double utilidadNeta) {
    	this.empresa.setText(empresa);
    	this.mes.setText(mes);
    	this.anio.setText(anio+"");
    	this.ventas.setText(ventas+"");
    	this.costoVentas.setText(costoVentas+"");
    	this.utilidadBruta.setText(utilidadBruta+"");
    	this.gastosOperativos.setText(gastosOperativos+"");
    	this.utilidadOperativa.setText(utilidadOperativa+"");
    	this.impuestos.setText(impuestos+"");
    	this.utilidadNeta.setText(utilidadNeta+"");
    }


}
