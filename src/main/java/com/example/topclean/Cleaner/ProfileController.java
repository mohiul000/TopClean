package com.example.topclean.Cleaner;

import com.example.topclean.Login;
import com.example.topclean.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.time.LocalDate;

public class ProfileController {
    @FXML
    private Label expL;
    @FXML
    private TextField nameTF;
    @FXML
    private Label addressL;
    @FXML
    private TextField skillsTF;
    @FXML
    private TextField expTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField AddressTF;
    @FXML
    private DatePicker dobDP;
    @FXML
    private Label emailL;
    @FXML
    private Label skillsL;
    @FXML
    private Label ProfileUpdateL;
    @FXML
    private Label nameL;
    @FXML
    private Label dobL;
    @FXML
    private TextField PasswordTF;
    @FXML
    private Label userIdL;

    private User loggedInUser;
    private Login loginController;

    public void setLoginController(Login loginController) {
        this.loginController = loginController;
    }

    public void setUser(User user) {
        this.loggedInUser = user;
        displayProfileInformation();
    }

    @FXML
    public void initialize() {

        ProfileUpdateL.setText("");
    }

    private void displayProfileInformation() {
        if (loggedInUser != null) {
            userIdL.setText(String.valueOf(loggedInUser.getId()));
            nameL.setText(loggedInUser.getName());
            emailL.setText(loggedInUser.getEmail());
            dobL.setText(loggedInUser.getDob() != null ? loggedInUser.getDob().toString() : "N/A");
            addressL.setText(loggedInUser.getAddress());


            nameTF.setText(loggedInUser.getName());
            emailTF.setText(loggedInUser.getEmail());
            if (loggedInUser.getDob() != null) {
                dobDP.setValue(loggedInUser.getDob());
            }
            AddressTF.setText(loggedInUser.getAddress());
            PasswordTF.setText(loggedInUser.getPassword());


            if (loggedInUser instanceof Cleaner) {
                Cleaner cleaner = (Cleaner) loggedInUser;
                skillsL.setText(cleaner.getSkills());
                expL.setText(cleaner.getExperience());
                skillsTF.setText(cleaner.getSkills());
                expTF.setText(cleaner.getExperience());
            } else {
                skillsL.setText("N/A");
                expL.setText("N/A");
                skillsTF.setText("");
                expTF.setText("");
            }


        }
    }

    @FXML
    public void UpdatebtnOnAction(ActionEvent actionEvent) {
        if (loggedInUser != null) {

            String updatedName = nameTF.getText();
            String updatedEmail = emailTF.getText();
            LocalDate updatedDob = dobDP.getValue();
            String updatedAddress = AddressTF.getText();
            String updatedPassword = PasswordTF.getText();


            loggedInUser.setName(updatedName);
            loggedInUser.setEmail(updatedEmail);
            loggedInUser.setDob(updatedDob);
            loggedInUser.setAddress(updatedAddress);
            loggedInUser.setPassword(updatedPassword);


            if (loggedInUser instanceof Cleaner) {
                Cleaner cleaner = (Cleaner) loggedInUser;
                String updatedSkills = skillsTF.getText();
                String updatedExp = expTF.getText();
                cleaner.setSkills(updatedSkills);
                cleaner.setExperience(updatedExp);
                loginController.saveUser(cleaner);
            } else {
                loginController.saveUser(loggedInUser);
            }


            ProfileUpdateL.setText("Profile Updated Successfully");
            displayProfileInformation();

        } else {
            ProfileUpdateL.setText("Error: No user logged in");
        }
    }
}

