package aydin.firebasedemo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
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
        if(registerUser()) {
            DemoApp.setRoot("secondary");
        }

    }

    public boolean registerUser() {

        //adds user to authentication
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

        } catch (FirebaseAuthException ex) {
            System.out.println("Error creating a new user in the firebase");
            errorLabel.setText("Error: Unable to register user!");
            return false;
        }

        //adds user to firestore so password is saved
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("username", usernameField.getText());
        userMap.put("password", passwordField.getText());
        userMap.put("email", emailField.getText());
        userMap.put("phoneNumber", phoneNumberField.getText());

        ApiFuture<WriteResult> result =
                DemoApp.fstore.collection("Users")
                        .document(usernameField.getText())
                        .set(userMap);
        return true;
    }

}
