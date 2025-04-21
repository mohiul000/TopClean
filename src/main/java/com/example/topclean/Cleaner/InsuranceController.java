package com.example.topclean.Cleaner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.*;

public class InsuranceController {

    @FXML
    private Label resultL;
    @FXML
    private TextField installmentAmount;
    @FXML
    private TextField ageTF;
    @FXML
    private TextField InstallmentTF;

    private static final String DATA_FILE = "insurance_applications.dat"; // insurance store

    @FXML
    public void initialize() {

    }

    @FXML
    public void applybtnOnAction(ActionEvent actionEvent) {
        try {

            String age = ageTF.getText();
            String installmentDuration = InstallmentTF.getText();
            String amount = installmentAmount.getText();


            if (age.isEmpty() || installmentDuration.isEmpty() || amount.isEmpty()) {
                resultL.setText("Please fill in all fields.");
                return;
            }


            String data = age + "," + installmentDuration + "," + amount + "\n";


            try (FileOutputStream fos = new FileOutputStream(DATA_FILE, true);
                 ObjectOutputStream oos = new ObjectOutputStream(fos) {
                     protected void writeStreamHeader() throws IOException {

                         if (new File(DATA_FILE).length() == 0) {
                             super.writeStreamHeader();
                         } else {

                         }
                     }
                 }) {
                oos.writeObject(data);
                resultL.setText("Application data saved.");
            } catch (IOException e) {
                e.printStackTrace();
                resultL.setText("Error saving data: " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultL.setText("An error occurred: " + e.getMessage());
        }
    }

    @FXML
    public void calcbtnOnAction(ActionEvent actionEvent) {
        try {

            int age = Integer.parseInt(ageTF.getText());
            int installmentDuration = Integer.parseInt(InstallmentTF.getText());
            double amount = Double.parseDouble(installmentAmount.getText());


            double result = amount * installmentDuration * (1 + (age / 100.0));


            resultL.setText(String.format("%.2f", result));

        } catch (NumberFormatException e) {
            resultL.setText("Invalid input. Please enter numbers.");
        } catch (Exception e) {
            e.printStackTrace();
            resultL.setText("An error occurred: " + e.getMessage());
        }
    }
}
