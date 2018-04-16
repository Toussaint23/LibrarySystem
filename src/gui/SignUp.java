package gui;

import controller.Admin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.MemberAccountImpl;

public class SignUp extends Application {
	private Parent root;
	private Scene scene;

	@Override
	public void start(Stage stage) throws Exception {
		root = FXMLLoader.load(getClass().getResource("resources/xml/SignUp.fxml"));
		scene = new Scene(root, 360, 520);
		stage.setScene(scene);
		stage.setTitle("SIGN UP");
		stage.setResizable(false);

		Button btnConnect = (Button) scene.lookup("#btn_connect");
		Button btnRegister = (Button) scene.lookup("#btn_register");

		btnConnect.setOnAction(evt -> {
			login();
		});

		btnRegister.setOnAction(evt -> {
			openLogin();
		});

		stage.show();
	}

	public static void Main(String[] args) {
		launch(args);
	}

	public void login() {
		try {
			String firstname = ((TextField) scene.lookup("#firstname")).getText().trim();
			String lastname = ((TextField) scene.lookup("#lastname")).getText().trim();
			String email = ((TextField) scene.lookup("#email")).getText().trim();
			String user = ((TextField) scene.lookup("#username")).getText().trim();
			String pwd = ((TextField) scene.lookup("#password")).getText().trim();

			Label error = (Label) scene.lookup("#error_label");

			if (!firstname.isEmpty() && !lastname.isEmpty() && !email.isEmpty() && !user.isEmpty() && !pwd.isEmpty()) {
				Admin admin = new Admin();
				
				MemberAccountImpl mai = admin.RegisterMember(email, firstname, 0, lastname, user, pwd);
				
				if(mai == null) {
					error.setText("Operation failed! Try again");
					error.setVisible(true);
				}
				else {
					error.setText("Success! Go to sign in now");
					error.setTextFill(Color.GREEN);
					error.setVisible(true);
				}

			} else {
				error.setText("All fields are required");
				error.setVisible(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openLogin() {
		try {
			Stage stage = new Stage();
			new SignIn().start(stage);
			this.root.getScene().getWindow().hide();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
