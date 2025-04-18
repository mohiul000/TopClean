package com.example.topclean;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Random;

public class SignUp
{
    @javafx.fxml.FXML
    private TextField phoneTF;
    @javafx.fxml.FXML
    private TextArea addressTA;
    @javafx.fxml.FXML
    private TextArea showTA;
    @javafx.fxml.FXML
    private TextField nameTF;
    @javafx.fxml.FXML
    private TextField emailTF;
    @javafx.fxml.FXML
    private ComboBox<String> userTypeCB;
    @javafx.fxml.FXML
    private PasswordField passwordPF;
    @javafx.fxml.FXML
    private DatePicker dobDP;


    private static final String USERS_FILE = "users.bin";


    @javafx.fxml.FXML
    public void initialize() {

        userTypeCB.getItems().addAll("Customer", "Cleaner");
    }

    @javafx.fxml.FXML
    public void backToLoginbtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void createbtnOnAction(ActionEvent actionEvent) {
        String name = nameTF.getText();
        String email = emailTF.getText();
        String phone = phoneTF.getText();
        LocalDate dob = dobDP.getValue();
        String userType = userTypeCB.getValue();
        String password = passwordPF.getText();
        String address = addressTA.getText();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || dob == null || userType == null || password.isEmpty() || address.isEmpty()) {
            showTA.setText("Please fill in all the fields.");
            return;
        }

        // Generate User ID
        Random random = new Random();
        int id;
        if (userType.equals("Customer")) {
            id = 10000 + random.nextInt(90000); // Generates a 5-digit number
        } else { // Cleaner
            id = 100000 + random.nextInt(900000); // Generates a 6-digit number
        }

        User newUser = new User(id, name, email, phone, password, userType, address, dob);


        if (saveUserToFile(newUser)) {
            showTA.setText("Account created successfully! Your User ID is: " + id + ". Please use this ID to log in.");

            nameTF.clear();
            emailTF.clear();
            phoneTF.clear();
            dobDP.setValue(null);
            userTypeCB.setValue(null);
            passwordPF.clear();
            addressTA.clear();
        } else {
            showTA.setText("Failed to create account. Please try again.");
        }


    }

    private boolean saveUserToFile(User user) {
        File file = new File(USERS_FILE);
        boolean append = file.exists();

        try (FileOutputStream fos = new FileOutputStream(file, append);
             ObjectOutputStream oos = append ? new AppendableObjectOutputStream(fos) : new ObjectOutputStream(fos)) {
            oos.writeObject(user);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}