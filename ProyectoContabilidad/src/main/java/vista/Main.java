package vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{

	private Stage window;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Proyecto Contabilidad y Costos");
		window.setResizable(false);
		Parent root = FXMLLoader.load(getClass().getResource("/vista/lobby.fxml"));
		Scene scene = new Scene(root);
		window.getIcons().add(new Image(getClass().getResourceAsStream("/vista/logo.png")));
		window.setScene(scene);
		window.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}

