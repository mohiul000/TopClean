package com.example.topclean.Cleaner;

import com.example.topclean.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class cleaner_dashboard
{
    @javafx.fxml.FXML
    private Label emailL;
    @javafx.fxml.FXML
    private Label nameL;
    @javafx.fxml.FXML
    private Label phoneNoL;
    @javafx.fxml.FXML
    private BorderPane cleanerDashboardBP;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void dashboardbtnOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void setAvailabilitybtnOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void profilebtnOnAction(ActionEvent actionEvent) {
    }



    @javafx.fxml.FXML
    public void assignedJobbtnOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void insurancebtnOnAction(ActionEvent actionEvent) {
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
    public void performanceAndGrowthbtnOnAction(ActionEvent actionEvent) {
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
    public void earningWithdrawbtnOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void feedbackbtnOnAction(ActionEvent actionEvent) {
        System.out.println("Feedback button clicked");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/topclean/Cleaner/feedback.fxml"));
            Parent feedbackRoot = fxmlLoader.load();
            // You might want to get the controller here if you need to pass data
            // FeedbackController feedbackController = fxmlLoader.getController();
            cleanerDashboardBP.setCenter(feedbackRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}