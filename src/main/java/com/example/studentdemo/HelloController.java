package com.example.studentdemo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class HelloController {
	@FXML
	private Label welcomeText;
	@FXML
	private Button Login;

	@FXML
	private PasswordField Password;

	@FXML
	private TextField Username;


	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;

	public void Login() throws SQLException, IOException {
		connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "sys as sysdba", "12122003");
		Alert alert;
		assert connect != null;
		if (Username.getText().isEmpty() || Password.getText().isEmpty()) {
			alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText(null);
			alert.setContentText("Please fill all blank fields");
			alert.showAndWait();

		}
		prepare = connect.prepareStatement("SELECT * FROM LOGIN_INFO WHERE username = '" + Username.getText() + "' AND user_password = '" + Password.getText() + "'");

		result = prepare.executeQuery();

		//Check if fields are empty
		if (result.next()) {
			System.out.println("Login successful !");
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information message");
			alert.setHeaderText(null);
			alert.setContentText("Login successful");
			alert.showAndWait();

			Login.getScene().getWindow().hide();

			Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainPage.fxml")));
			Stage stage = new Stage();
			Scene scene = new Scene(root);

			stage.setScene(scene);
			stage.show();

		} else {
			System.out.println("Try again");
		}
	}

	@FXML
	protected void onHelloButtonClick() {
		welcomeText.setText("Welcome to JavaFX Application!");
	}
}