package com.example.topclean.Cleaner;

import com.example.topclean.Customer.ProfileController;
import com.example.topclean.HelloApplication;
import com.example.topclean.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class cleaner_dashboard {
    @javafx.fxml.FXML
    private Label emailL;
    @javafx.fxml.FXML
    private Label nameL;
    @javafx.fxml.FXML
    private Label phoneNoL;
    @javafx.fxml.FXML
    private BorderPane cleanerDashboardBP;
    private Cleaner loggedInCleaner;
    private Login loginController;

    public void setLoginController(Login loginController) {
        this.loginController = loginController;
    }
    public void setLoggedInCleaner(Cleaner loggedInCleaner) {
        this.loggedInCleaner = loggedInCleaner;
        displayCleanerDashboardInfo();
    }
    @javafx.fxml.FXML
    public void initialize() {


    }
    private void displayCleanerDashboardInfo() {
        if (loggedInCleaner != null) {
            nameL.setText("Name: " + loggedInCleaner.getName());
            emailL.setText("Email: " + loggedInCleaner.getEmail());
           // phoneNoL.setText("Phone: " + loggedInCleaner.getPhoneNo());
        }
    }

    @javafx.fxml.FXML
    public void dashboardbtnOnAction(ActionEvent actionEvent)  throws IOException{
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Cleaner/cleaner_dashboard.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Cleaner Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void setAvailabilitybtnOnAction(ActionEvent actionEvent) {
        try {
            URL availabilityFxmlUrl = getClass().getResource("/com/example/topclean/Cleaner/SetAvailability.fxml");
            if (availabilityFxmlUrl == null) {
                System.err.println("FXML file not found: /com/example/topclean/Cleaner/SetAvailability.fxml");
                return;
            }
            Parent setAvailabilityRoot = FXMLLoader.load(availabilityFxmlUrl);
            cleanerDashboardBP.setCenter(setAvailabilityRoot);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @javafx.fxml.FXML
    public void profilebtnOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/topclean/Cleaner/profile.fxml"));
        Parent profileRoot = fxmlLoader.load();
        cleanerDashboardBP.setCenter(profileRoot);

       // FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/topclean/Cleaner/profile.fxml"));
       // Parent profileRoot = fxmlLoader.load();
       // ProfileController profileController = fxmlLoader.getController();
      //  profileController.setLoginController(loginController);
      //  profileController.setUser(loggedInCleaner);
      //  cleanerDashboardBP.setCenter(profileRoot);
    }


    @javafx.fxml.FXML
    public void assignedJobbtnOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/topclean/Cleaner/AssignedJob.fxml"));
        Parent profileRoot = fxmlLoader.load();
        cleanerDashboardBP.setCenter(profileRoot);
    }

    @javafx.fxml.FXML
    public void insurancebtnOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/topclean/Cleaner/Insurance.fxml"));
        Parent profileRoot = fxmlLoader.load();
        cleanerDashboardBP.setCenter(profileRoot);
    }

    @javafx.fxml.FXML
    public void manageInventorybtnOnAction(ActionEvent actionEvent) {
        System.out.println("Manage Inventory button clicked");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/topclean/Cleaner/ManageInventory.fxml"));
            Parent inventoryRoot = fxmlLoader.load();
            // ManageInventory inventoryController = fxmlLoader.getController();
            cleanerDashboardBP.setCenter(inventoryRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void performanceAndGrowthbtnOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/topclean/Cleaner/PerformanceGrowth.fxml"));
        Parent profileRoot = fxmlLoader.load();
        cleanerDashboardBP.setCenter(profileRoot);
    }

    @javafx.fxml.FXML
    public void signOutbtnOnAction(ActionEvent actionEvent) {
        System.out.println("Sign Out button clicked");
        try {
            Parent root = FXMLLoader.load(HelloApplication.class.getResource("login.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login Page");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @javafx.fxml.FXML
    public void earningWithdrawbtnOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/topclean/Cleaner/EarningWithdraw.fxml"));
        Parent profileRoot = fxmlLoader.load();
        cleanerDashboardBP.setCenter(profileRoot);
    }

    @javafx.fxml.FXML
    public void feedbackbtnOnAction(ActionEvent actionEvent) {
        System.out.println("Feedback button clicked");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/topclean/Cleaner/feedback.fxml"));
            Parent feedbackRoot = fxmlLoader.load();
            // ... pass data
            // FeedbackController feedbackController = fxmlLoader.getController();
            cleanerDashboardBP.setCenter(feedbackRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
