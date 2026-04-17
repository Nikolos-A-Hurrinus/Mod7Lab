package aydin.firebasedemo;

import java.io.IOException;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SecondaryController {
    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private Button signInButton;


    @FXML
    private void signIn() throws IOException {
        DemoApp.setRoot("primary");
    }

    @FXML
    private void registerButtonClicked() throws IOException {
        DemoApp.setRoot("register");
    }

}
