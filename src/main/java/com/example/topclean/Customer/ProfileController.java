package com.example.topclean.Customer;

import com.example.topclean.Login;
import com.example.topclean.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;

public class ProfileController {

    @FXML
    private TextField nameTF;
    @FXML
    private Label addressL;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField AddressTF;
    @FXML
    private DatePicker dobDP;
    @FXML
    private Label emailL;
    @FXML
    private Label nameL;
    @FXML
    private Label dobL;
    @FXML
    private TextField PasswordTF;
    @FXML
    private Label userIdL;
    @FXML
    private Label ProfileUpdateL;

    private User currentUser;
    private Login loginController;
    @FXML
    private Label passwordL;

    public void setLoginController(Login loginController) {
        this.loginController = loginController;
    }

    public void setUser(User user) {
        this.currentUser = user;
        populateProfile();
        ProfileUpdateL.setText("");
    }

    @FXML
    public void initialize() {
        ProfileUpdateL.setText("");
    }

    private void populateProfile() {
        if (currentUser != null) {
            nameL.setText(currentUser.getName());
            userIdL.setText(String.valueOf(currentUser.getId()));
            emailL.setText(currentUser.getEmail());
            if (currentUser.getDob() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                dobL.setText(currentUser.getDob().format(formatter));
                dobDP.setValue(currentUser.getDob());
            } else {
                dobL.setText("");
                dobDP.setValue(null);
            }
            addressL.setText(currentUser.getAddress());
            passwordL.setText("********");
            nameTF.setText(currentUser.getName());
            emailTF.setText(currentUser.getEmail());
            AddressTF.setText(currentUser.getAddress());
            PasswordTF.setText(currentUser.getPassword());
        }
    }

    @FXML
    public void UpdatebtnOnAction(ActionEvent actionEvent) {
        if (currentUser != null && loginController != null) {
            String updatedName = nameTF.getText();
            String updatedEmail = emailTF.getText();
            String updatedAddress = AddressTF.getText();
            String updatedPassword = PasswordTF.getText();
            java.time.LocalDate updatedDob = dobDP.getValue();


            currentUser.setName(updatedName);
            currentUser.setEmail(updatedEmail);
            currentUser.setAddress(updatedAddress);
            currentUser.setPassword(updatedPassword);
            currentUser.setDob(updatedDob);

            // Save updated infos
           loginController.saveUser(currentUser);

            System.out.println("Profile updated and saved for user: " + currentUser.getId());
            ProfileUpdateL.setText("Profile updated successfully!");
            populateProfile();
        } else {
            ProfileUpdateL.setText("Error: No user logged in or Login controller not set.");
        }
    }
}