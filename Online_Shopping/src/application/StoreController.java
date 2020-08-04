package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class StoreController implements Initializable{

    @FXML
    private WebView web_view;

    @FXML
    private Label lbl_name;
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		WebEngine webEngine = web_view.getEngine();
		webEngine.load("http://amazon.de");
		lbl_name.setText(LoginController.visitor);
	}
    
	
}