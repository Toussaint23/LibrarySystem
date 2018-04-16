package gui;

import java.io.IOException;

import controller.Admin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Member;

public class SignIn extends Application {
	private Scene scene;
	private Parent root;
	public static Member currentMember;

	@Override
	public void start(Stage stage) throws Exception {
		root = FXMLLoader.load(getClass().getResource("resources/xml/SignIn.fxml"));
		scene = new Scene(root, 360, 275);
		stage.setScene(scene);
		stage.setTitle("SIGN IN");
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

	public static void main(String[] args) {
		launch(args);
	}

	public void login() {
		try {
			TextField user = (TextField) scene.lookup("#username");
			TextField pwd = (TextField) scene.lookup("#password");
			Label error1 = (Label) scene.lookup("#error_label1");
			Label error2 = (Label) scene.lookup("#error_label2");

			Admin admin = new Admin();

			if (user.getLength() != 0 && pwd.getLength() != 0) {
				currentMember = admin.signIn(user.getText(), pwd.getText());
				if (currentMember != null) {
					Stage stage = new Stage();
					new AppMain(currentMember).start(stage);
					this.root.getScene().getWindow().hide();
				} else {
					error1.setText("Connection failed");
					error1.setVisible(true);
					error2.setVisible(true);
				}
			} else {
				error1.setText("User and Password are required");
				error1.setVisible(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openRegister() {
		try {
			try {
				Stage stage = new Stage();
				new SignUp().start(stage);
				this.root.getScene().getWindow().hide();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
