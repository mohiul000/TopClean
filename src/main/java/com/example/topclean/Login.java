package com.example.topclean;

import com.example.topclean.Cleaner.Cleaner;
import com.example.topclean.Cleaner.cleaner_dashboard;
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
    @FXML
    private PasswordField passwordPF;
    @FXML
    private TextField idTF;
    @FXML
    private Label loginMessageLabel;
    private static final String USERS_FILE = "users.bin";
    private List<User> users;


    public enum UserType {
        CUSTOMER,
        CLEANER
    }

    @FXML
    public void initialize() {
        loadUsersFromFile();
        loginMessageLabel.setText("");
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
                loginMessageLabel.setText("Error loading user data. Please check the log for details.");
            }
        }
        return users;
    }

    public void saveUser(User updatedUser) {
        List<User> allUsers = loadAllUsers();
        List<User> updatedList = new ArrayList<>();

        for (User user : allUsers) {
            if (user.getId() == updatedUser.getId()) {
                updatedList.add(updatedUser);
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
            loginMessageLabel.setText("Error saving user data. Please check the log for details.");
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
                        loginMessageLabel.setText("Error reading user data. The data file may be corrupted.");
                        e.printStackTrace();
                        break;
                    }
                }
            } catch (IOException e) {
                loginMessageLabel.setText("Error accessing user data file.");
                e.printStackTrace();
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

    @FXML
    public void loginBtnOnAction(ActionEvent actionEvent) throws IOException {
        String userIdText = idTF.getText();
        String password = passwordPF.getText();

        if (userIdText.isEmpty() || password.isEmpty()) {
            loginMessageLabel.setText("Please enter User ID and Password.");
            return;
        }

        try {
            int userId = Integer.parseInt(userIdText);
            boolean found = false;

            for (User user : users) {
                if (user.getId() == userId && user.getPassword().equals(password)) {
                    found = true;
                    loginMessageLabel.setText("");
                    System.out.println("Login successful for user ID: " + userId);

                    // customer  cleaner check
                    if (user.getType() == Login.UserType.CUSTOMER) {
                        loadCustomerDashboard(actionEvent, user);
                        return;
                    } else if (user.getType() == Login.UserType.CLEANER) {
                        loadCleanerDashboard(actionEvent, (Cleaner) user);
                        return;
                    } else {
                        loginMessageLabel.setText("Error: User type not recognized.");
                        return;
                    }
                }
            }
            if (!found) {
                loginMessageLabel.setText("Invalid User ID or Password.");
            }


        } catch (NumberFormatException e) {
            loginMessageLabel.setText("Invalid User ID format. Please enter a number.");

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

    private void loadCleanerDashboard(ActionEvent actionEvent, Cleaner loggedInCleaner) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Cleaner/cleaner_dashboard.fxml"));
        root = fxmlLoader.load();
        cleaner_dashboard cleanerDashboardController = fxmlLoader.getController();
        cleanerDashboardController.setLoginController(this);
        cleanerDashboardController.setLoggedInCleaner(loggedInCleaner);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Cleaner Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}