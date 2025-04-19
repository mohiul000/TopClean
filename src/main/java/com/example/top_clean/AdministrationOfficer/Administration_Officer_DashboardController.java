package com.example.top_clean.AdministrationOfficer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Administration_Officer_DashboardController implements Initializable {

    @FXML
    private BorderPane borderPaneId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void bestEmployeeMenuItemOnClick(ActionEvent event) throws IOException {
        Parent fileChooserViewParent = FXMLLoader.load(getClass().getResource("BestEmployee.fxml"));
        Scene fileChooserViewScene = new Scene(fileChooserViewParent);
        Stage newWindow = new Stage();
        newWindow.setTitle("Announce Best Employee");
        newWindow.setScene(fileChooserViewScene);
        newWindow.show();
    }

    @FXML
    private void qualityControlMenuItemOnClick(ActionEvent event) throws IOException {
        Parent fileChooserViewParent = FXMLLoader.load(getClass().getResource("qualityControl.fxml"));
        Scene fileChooserViewScene = new Scene(fileChooserViewParent);
        Stage newWindow = new Stage();
        newWindow.setTitle("Quality Control Inspection");
        newWindow.setScene(fileChooserViewScene);
        newWindow.show();
    }

    @FXML
    private void developementPlanMenuItemOnClick(ActionEvent event) throws IOException {
        Parent fileChooserViewParent = FXMLLoader.load(getClass().getResource("developementPlan.fxml"));
        Scene fileChooserViewScene = new Scene(fileChooserViewParent);
        Stage newWindow = new Stage();
        newWindow.setTitle("Add a Developement Plan");
        newWindow.setScene(fileChooserViewScene);
        newWindow.show();
    }

    @FXML
    private void monthlyMaintenanceMenuItemOnClick(ActionEvent event) throws IOException {
        Parent fileChooserViewParent = FXMLLoader.load(getClass().getResource("monthly_maintenance_program.fxml"));
        Scene fileChooserViewScene = new Scene(fileChooserViewParent);
        Stage newWindow = new Stage();
        newWindow.setTitle(" Schedule Monthly Maintenance Program");
        newWindow.setScene(fileChooserViewScene);
        newWindow.show();
    }

    @FXML
    private void logOutMenuItemOnClick(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("AdminLogOut.fxml"));
        borderPaneId.setBottom(parent);
    }

    @FXML
    private void sendNoticeMenuItemOnClick(ActionEvent event) throws IOException {
        Parent fileChooserViewParent = FXMLLoader.load(getClass().getResource("SendNotice.fxml"));
        Scene fileChooserViewScene = new Scene(fileChooserViewParent);
        Stage newWindow = new Stage();
        newWindow.setTitle("Send Notice");
        newWindow.setScene(fileChooserViewScene);
        newWindow.show();
    }
}
