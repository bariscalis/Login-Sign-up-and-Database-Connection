package application;

import java.io.IOException;
import java.sql.*;

import javax.swing.JOptionPane;

import connection.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	
	public static String visitor;

    @FXML
    private PasswordField password;

    @FXML
    private Hyperlink signup;

    @FXML
    private Button button_login;

    @FXML
    TextField username;


    @FXML
    void login(ActionEvent event) {
    	DbConnection dbCon = new DbConnection();
    	Connection con = dbCon.get_Connection("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/shopping", "root", "");
    	try {
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery("Select * from customer");
			
			Boolean user_found = false;
			
			while (result.next()) {
				if (
					// login with username or email address
					(username.getText().equals(result.getString("username")) & 
					password.getText().equals(result.getString("password"))) |
					(username.getText().equals(result.getString("email")) & 
					password.getText().equals(result.getString("password")))
				   ){
					user_found = true;
					visitor = result.getString("first_name").toUpperCase() + " " + result.getString("last_name").toUpperCase();
					
					// If typed values are correct, open Store Window
					 Parent root;
			            try {
			                root = FXMLLoader.load(getClass().getResource("Store.fxml"));
			                Stage stage = new Stage();
			                stage.setTitle("Store Window");
			                stage.setScene(new Scene(root, 1250, 700));
			                stage.show();
			                // Hide this current window
			                ((Node)(event.getSource())).getScene().getWindow().hide();
			            }
			            catch (IOException e) {
			                e.printStackTrace();
			            }
					
					
					break;
				}
			}
			
			if (user_found==false) {
				JOptionPane.showMessageDialog(null, "Please check your data. It does not match!", "Invalid Data", JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    }

    @FXML
    void signup(ActionEvent event) {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Sign up Window");
                stage.setScene(new Scene(root, 450, 450));
                stage.show();
                // Hide this current window
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
    }


}
