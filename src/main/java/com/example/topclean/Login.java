package com.example.topclean;

import com.example.topclean.Customer.customer_dashboardFxmlController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Login {
    @javafx.fxml.FXML
    private PasswordField passwordPF;
    @javafx.fxml.FXML
    private TextField idTF;
    @FXML
    private Label loginMessageLabel; // label  output for maybe success or something.. login.fxml to display messages

    private static final String USERS_FILE = "users.bin";
    private List<User> users;


    @javafx.fxml.FXML
    public void initialize() {
        loadUsersFromFile();
    }
    private List<User> loadAllUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(USERS_FILE);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (true) {
                    try {
                        User user = (User) ois.readObject();
                        users.add(user);
                    } catch (EOFException e) {
                        break;
                    } catch (ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
    public void saveUser(User updatedUser) {
        List<User> allUsers = loadAllUsers();
        List<User> updatedList = new ArrayList<>();

        for (User user : allUsers) {
            if (user.getId() == updatedUser.getId()) {
                updatedList.add(updatedUser); // update user replacing ...
            } else {
                updatedList.add(user);
            }
        }


        try (FileOutputStream fos = new FileOutputStream(USERS_FILE, false);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (User user : updatedList) {
                oos.writeObject(user);
            }
            System.out.println("User data saved successfully for user ID: " + updatedUser.getId());
        } catch (IOException e) {
            System.err.println("Error saving user data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadUsersFromFile() {
        users = new ArrayList<>();
        File file = new File(USERS_FILE);

        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (true) {
                    try {
                        User user = (User) ois.readObject();
                        users.add(user);
                    } catch (EOFException e) {
                        break;
                    } catch (ClassNotFoundException e) {
                        break;
                    }
                }
            } catch (IOException e) {
                //
            }
        }

    }

    @FXML
    public void signUpbtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sign_up.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Sign Up Page");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void loginBtnOnAction(ActionEvent actionEvent) throws IOException {
        String userIdText = idTF.getText();
        String password = passwordPF.getText();

        if (userIdText.isEmpty() || password.isEmpty()) {
            // Optionally display a message to the user
            System.out.println("Please enter User ID and Password.");
            return;
        }

        try {
            int userId = Integer.parseInt(userIdText);

            for (User user : users) {
                if (user.getId() == userId && user.getPassword().equals(password)) {
                    // if success
                    System.out.println("Login successful for user ID: " + userId);

                    // customer or cleaner check
                    if (String.valueOf(userId).length() == 5 && user.getType().equals("Customer")) {
                        loadCustomerDashboard(actionEvent, user);
                        return;
                    } else if (String.valueOf(userId).length() == 6 && user.getType().equals("Cleaner")) {
                        loadCleanerDashboard(actionEvent, user);
                        return;
                    } else {
                        System.out.println("Error: User ID length does not match user type.");

                    }
                }
            }
            // if wrong pass id and other error
            System.out.println("Invalid User ID or Password.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid User ID format. Please enter a number.");

        }
    }

    private void loadCustomerDashboard(ActionEvent actionEvent, User loggedInUser) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Customer/customer_dashboard.fxml"));
        root = fxmlLoader.load();

        customer_dashboardFxmlController dashboardController = fxmlLoader.getController();
        dashboardController.setLoginController(this);
        dashboardController.setUser(loggedInUser);

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Customer Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    private void loadCleanerDashboard(ActionEvent actionEvent, User loggedInUser) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Cleaner/cleaner_dashboard.fxml")); // Assuming you have a cleaner dashboard FXML
        root = fxmlLoader.load();

        // cleaner dash---
        // CleanerDashboardController cc = fxmlLoader.getController();
        // cc.setData(loggedInUser);

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Cleaner Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}