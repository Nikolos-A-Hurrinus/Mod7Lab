package aydin.firebasedemo;

import java.io.IOException;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    public Label errorLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button registerButton;




    @FXML
    private void cancel() throws IOException {
        DemoApp.setRoot("secondary");
    }

    @FXML
    void registerButtonClicked(ActionEvent event) throws IOException {
        registerUser();
        DemoApp.setRoot("secondary");

    }

    public boolean registerUser() {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(emailField.getText())
                .setEmailVerified(false)
                .setPassword(passwordField.getText())
                .setPhoneNumber("+" + phoneNumberField.getText())
                .setDisplayName(usernameField.getText())
                .setDisabled(false);

        UserRecord userRecord;
        try {
            userRecord = DemoApp.fauth.createUser(request);
            System.out.println("Successfully created new user with Firebase Uid: " + userRecord.getUid()
                    + " check Firebase > Authentication > Users tab");
            return true;

        } catch (FirebaseAuthException ex) {
            // Logger.getLogger(FirestoreContext.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error creating a new user in the firebase");
            errorLabel.setText("Error: Unable to register user!");
            return false;
        }

    }



}
