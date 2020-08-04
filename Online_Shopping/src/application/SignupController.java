package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JOptionPane;

import connection.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignupController {

    @FXML
    private Button button_signup;

    @FXML
    private PasswordField password;

    @FXML
    private TextField address;

    @FXML
    private TextField phone;

    @FXML
    private TextField firstname;

    @FXML
    private TextField email;

    @FXML
    private TextField lastname;

    @FXML
    private TextField username;


    @FXML
    void signup(ActionEvent event) {
    	DbConnection dbConnection = new DbConnection();
    	Connection con = dbConnection.get_Connection("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/shopping", "root", "");
    	try {
			PreparedStatement stmt = con.prepareStatement("INSERT INTO customer values (?,?,?,?,?,?,?,?)");
			stmt.setString(1, null);
			stmt.setString(2, firstname.getText());
			stmt.setString(3, lastname.getText());
			stmt.setString(4, email.getText());
			stmt.setString(5, phone.getText());
			stmt.setString(6, address.getText());
			stmt.setString(7, username.getText());
			stmt.setString(8, password.getText());
			stmt.executeUpdate();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null, "Registered succesfully","INFO",JOptionPane.INFORMATION_MESSAGE);
			Stage stage = (Stage) button_signup.getScene().getWindow();
			stage.close();
			
			// return again Login Window
			try {
				Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
				Scene scene = new Scene(root,500,300);
				Stage primaryStage = new Stage();
				primaryStage.setTitle("Login Window");
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		} catch (SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage() + ", try a different one", "Duplicate Value", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "SQL Exception\n" + e.toString(), "SQL Error", JOptionPane.INFORMATION_MESSAGE);
		}
    	

    }

}
