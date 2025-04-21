package com.example.topclean;

import com.example.topclean.Cleaner.Cleaner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

public class SignUp {
    @FXML
    private TextField phoneTF;
    @FXML
    private TextArea addressTA;
    @FXML
    private TextArea showTA;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField emailTF;
    @FXML
    private ComboBox<String> userTypeCB;
    @FXML
    private PasswordField passwordPF;
    @FXML
    private DatePicker dobDP;
    @FXML
    private TextField skillsTF;
    @FXML
    private TextField experienceTF;

    private static final String USERS_FILE = "users.bin";

    @FXML
    public void initialize() {
        userTypeCB.getItems().addAll("Customer", "Cleaner");
    }

    @FXML
    public void userTypeCBOnAction(ActionEvent event) {
        String userType = userTypeCB.getValue();
        if ("Cleaner".equals(userType)) {
            skillsTF.setVisible(true);
            experienceTF.setVisible(true);
        } else {
            skillsTF.setVisible(false);
            experienceTF.setVisible(false);
        }
    }

    @FXML
    public void backToLoginbtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void createbtnOnAction(ActionEvent actionEvent) {
        String name = nameTF.getText();
        String email = emailTF.getText();
        String phone = phoneTF.getText();
        LocalDate dob = dobDP.getValue();
        String userType = userTypeCB.getValue();
        String password = passwordPF.getText();
        String address = addressTA.getText();
        String skills = (skillsTF != null) ? skillsTF.getText() : "";
        String experience = (experienceTF != null) ? experienceTF.getText() : "";

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || dob == null || userType == null || password.isEmpty() || address.isEmpty()) {
            showTA.setText("Please fill in all the fields.");
            return;
        }

        // / Generate User ID
        Random random = new Random();
        int id;
        User newUser; // setting user obj

        if (userType.equals("Customer")) {
            id = 1000 + random.nextInt(90000); // Generates a 5-digit number for customer
            newUser = new User(id, name, email, password, address, dob, Login.UserType.CUSTOMER, phone);
        } else if (userType.equals("Cleaner")) {
            id = 100000 + random.nextInt(900000); // Generates a 6-digit number for cleaner
            newUser = new Cleaner(id, name, email, password, address, dob, Login.UserType.CLEANER, skills, experience, phone);
        } else {
            showTA.setText("Invalid user type. Please select Customer or Cleaner.");
            return;
        }


        if (saveUserToFile(newUser)) {
            showTA.setText("Account created successfully! Your User ID is: " + id + ". Please use this ID to log in.");
            clearFields();
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

    private void clearFields() {
        nameTF.clear();
        emailTF.clear();
        phoneTF.clear();
        dobDP.setValue(null);
        userTypeCB.setValue(null);
        passwordPF.clear();
        addressTA.clear();
        if (skillsTF != null && experienceTF != null) {
            skillsTF.clear();
            experienceTF.clear();
        }
    }
}

