package aydin.firebasedemo;

import java.io.IOException;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SecondaryController {

    @FXML
    private Label errorLabel;

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
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            // Get the user document
            DocumentSnapshot snapshot = DemoApp.fstore
                    .collection("Users")
                    .document(username)
                    .get()
                    .get();

            // Check if user exists
            if (!snapshot.exists()) {
                errorLabel.setText("User not found");
                return;
            }

            // Read fields from Firestore
            String storedPassword = snapshot.getString("password");

            // Compare passwords
            if (storedPassword.equals(password)) {
                System.out.println("Login successful!");
                DemoApp.setRoot("primary"); // go to main screen
            } else {
                errorLabel.setText("Incorrect password");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }    }

    @FXML
    private void registerButtonClicked() throws IOException {
        DemoApp.setRoot("register");
    }

}
