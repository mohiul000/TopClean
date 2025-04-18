package com.example.topclean.Customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;

public class MakePayment {

    @FXML
    private TableColumn<Order, LocalDate> paymentDDTC;
    @FXML
    private Label paymentDoneStatus;
    @FXML
    private TableColumn<Order, String> paymentDesTC;
    @FXML
    private TableColumn<Order, Integer> paymentQuantityTC;
    @FXML
    private TableColumn<Order, Integer> paymentOrderIdTC;
    @FXML
    private TableColumn<Order, Double> paymentCostTC;
    @FXML
    private ComboBox<Integer> paymentOrderIdCB;
    @FXML
    private TableView<Order> paymentOrdersTV;
    @FXML
    private TextField bkashidTF;
    private ObservableList<Order> orderList = FXCollections.observableArrayList();
    private static final String ORDERS_FILE = "orders.bin";
    private int loggedInCustomerId;

    public void setLoggedInCustomerId(int customerId) {
        this.loggedInCustomerId = customerId;
        System.out.println("Customer ID received in MakePayment: " + loggedInCustomerId);
        loadDueOrders();
        populateOrderIdComboBox();
    }

    @FXML
    public void initialize() {

        paymentOrderIdTC.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        paymentDesTC.setCellValueFactory(new PropertyValueFactory<>("materialType"));
        paymentQuantityTC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        paymentDDTC.setCellValueFactory(new PropertyValueFactory<>("dropOffDate"));
        paymentCostTC.setCellValueFactory(new PropertyValueFactory<>("cost"));

        paymentOrdersTV.setItems(orderList);


    }

    private void loadDueOrders() {
        orderList.clear();
        File file = new File(ORDERS_FILE);
        System.out.println("Loading orders for customer ID: " + loggedInCustomerId);

        if (file.exists()) {
            System.out.println("Orders file found.");
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                int orderCount = 0;
                while (true) {
                    try {
                        Order order = (Order) ois.readObject();
                        System.out.println("Read order with ID: " + order.getOrderId() + ", Customer ID: " + order.getCustomerId());
                        if (order.getCustomerId() == loggedInCustomerId) {
                            System.out.println("Order added to list: " + order.getOrderId());
                            orderList.add(order);
                            orderCount++;
                        }
                    } catch (EOFException e) {
                        break;
                    } catch (ClassNotFoundException e) {
                        System.err.println("Error: ClassNotFoundException while reading order data.");
                        e.printStackTrace();
                        break;
                    } catch (IOException e) {
                        System.err.println("Error: IOException while reading order data.");
                        e.printStackTrace();
                        break;
                    }
                }
                System.out.println("Total orders loaded for customer: " + orderCount);
            } catch (IOException e) {
                System.err.println("Error: IOException while opening/reading order file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No orders file found.");
        }
    }

    private void populateOrderIdComboBox() {
        ObservableList<Integer> orderIds = FXCollections.observableArrayList();
        for (Order order : orderList) {
            orderIds.add(order.getOrderId());
        }
        paymentOrderIdCB.setItems(orderIds);
    }

    @Deprecated
    public void paymentDonebtnOnAction(ActionEvent actionEvent) {
        Integer selectedOrderId = paymentOrderIdCB.getValue();
        String transactionId = bkashidTF.getText().trim();

        if (selectedOrderId != null && !transactionId.isEmpty()) {

            paymentDoneStatus.setText("Payment Done!");
            loadDueOrders();
            populateOrderIdComboBox();
        } else {
            paymentDoneStatus.setText("Select Order and enter Bkash ID.");
        }
    }
}