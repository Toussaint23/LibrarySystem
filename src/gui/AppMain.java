package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AppMain extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("resources/xml/AppMain.fxml"));
		Scene scene = new Scene(root, 750, 650);
		stage.setScene(scene);
		stage.setTitle("Library System");
		stage.setResizable(false);

		Button btnConnect = (Button) scene.lookup("#btn_connect");
		Button btnRegister = (Button) scene.lookup("#btn_register");

		btnConnect.setOnAction(evt -> {
			login();
		});
		
		btnRegister.setOnAction(evt -> {
			openRegister();
		});

		stage.show();
	}

	public static void Main(String[] args) {
		launch(args);
	}

	public void login() {
		try {
			System.out.print("tst action");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openRegister() {
		try {
			System.out.print("tst action");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
