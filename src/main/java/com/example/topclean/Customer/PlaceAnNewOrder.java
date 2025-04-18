package com.example.topclean.Customer;

import com.example.topclean.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlaceAnNewOrder {

    @FXML
    private Label statusOutputLabel;
    @FXML
    private TextField QuantityTF;
    @FXML
    private CheckBox dryCleaingCKB;
    @FXML
    private DatePicker DropOfDate;
    @FXML
    private DatePicker pickUpDate;
    @FXML
    private CheckBox ironingCKB;
    @FXML
    private TextArea AddressTA;
    @FXML
    private CheckBox washAndFoldCKB;
    @FXML
    private TextArea materialTypeTA;
    @FXML
    private TextField BrandTF;
    @FXML
    private ComboBox<String> PaymentTypeCB;
    @FXML
    private CheckBox laundryCkB;
    @FXML
    private TextArea specialNoteTA;

    private static final String ORDERS_FILE = "orders.bin";
    private int customerId;
    private Runnable onOrderPlacedListener; //listener

    @FXML
    public void initialize() {
        PaymentTypeCB.getItems().addAll("Cash on Delivery", "Credit Card", "Bkash", "Nagad");



    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
        System.out.println("Customer ID received in PlaceAnNewOrder: " + customerId);
    }

    public void setOnOrderPlacedListener(Runnable listener) {
        this.onOrderPlacedListener = listener;
    }

    @FXML
    public void placeAnOrderbtnOnAction(ActionEvent actionEvent) {
        List<String> selectedServices = new ArrayList<>();
        if (laundryCkB.isSelected()) selectedServices.add("Laundry");
        if (dryCleaingCKB.isSelected()) selectedServices.add("Dry Cleaning");
        if (washAndFoldCKB.isSelected()) selectedServices.add("Wash & Fold");
        if (ironingCKB.isSelected()) selectedServices.add("Ironing");

        String materialType = materialTypeTA.getText();
        String specialNote = specialNoteTA.getText();
        String brand = BrandTF.getText();
        String quantityText = QuantityTF.getText();
        LocalDate pickUp = pickUpDate.getValue();
        LocalDate dropOff = DropOfDate.getValue();
        String address = AddressTA.getText();
        String payment = PaymentTypeCB.getValue();
        LocalDate orderDate = LocalDate.now();

        if (selectedServices.isEmpty() || materialType.isEmpty() || quantityText.isEmpty() || pickUp == null || dropOff == null || address.isEmpty() || payment == null) {
            statusOutputLabel.setText("Please fill in all the required fields.");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) {
                statusOutputLabel.setText("Quantity must be greater than zero.");
                return;
            }
        } catch (NumberFormatException e) {
            statusOutputLabel.setText("Invalid quantity format.");
            return;
        }

        // Generate a unique Order ID
        Random random = new Random();
        int orderId = random.nextInt(1000000);

        Order newOrder = new Order(orderId, this.customerId, selectedServices, materialType, specialNote, brand, quantity, pickUp, dropOff, address, payment, orderDate); // Using customerId here

        if (saveOrderToFile(newOrder)) {
            statusOutputLabel.setText("Order placed successfully! Order ID: " + orderId);

            clearInputFields();
            if (onOrderPlacedListener != null) {
                onOrderPlacedListener.run();
            }
        } else {
            statusOutputLabel.setText("Failed to place order. Please try again.");
        }
    }

    private boolean saveOrderToFile(Order order) {
        File file = new File(ORDERS_FILE);
        boolean append = file.exists();

        try (FileOutputStream fos = new FileOutputStream(file, append);
             ObjectOutputStream oos = append ? new AppendableObjectOutputStream(fos) : new ObjectOutputStream(fos)) {
            oos.writeObject(order);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void clearInputFields() {
        laundryCkB.setSelected(false);
        dryCleaingCKB.setSelected(false);
        washAndFoldCKB.setSelected(false);
        ironingCKB.setSelected(false);
        materialTypeTA.clear();
        specialNoteTA.clear();
        BrandTF.clear();
        QuantityTF.clear();
        pickUpDate.setValue(null);
        DropOfDate.setValue(null);
        AddressTA.clear();
        PaymentTypeCB.setValue(null);
    }
}