package com.example.topclean.Customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersController {

    @FXML
    private TableView<Order> myOrdersTV;
    @FXML
    private TableColumn<Order, Integer> orderIdTC;
    @FXML
    private TableColumn<Order, String> desTC;
    @FXML
    private TableColumn<Order, Integer> QuantityTC;
    @FXML
    private TableColumn<Order, LocalDate> dropdateTc;
    @FXML
    private TableColumn<Order, LocalDate> PickDateTC;
    @FXML
    private TextField cancelOrderIdTF;

    private static final String ORDERS_FILE = "orders.bin";
    private int customerId;
    private ObservableList<Order> orderList = FXCollections.observableArrayList();

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
        loadCustomerOrders();
    }

    @FXML
    public void initialize() {
        orderIdTC.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        desTC.setCellValueFactory(new PropertyValueFactory<>("materialType"));
        QuantityTC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        dropdateTc.setCellValueFactory(new PropertyValueFactory<>("dropOffDate"));
        PickDateTC.setCellValueFactory(new PropertyValueFactory<>("pickUpDate"));

        myOrdersTV.setItems(orderList);
    }

    private void loadCustomerOrders() {
        orderList.clear();
        File file = new File(ORDERS_FILE);

        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (true) {
                    try {
                        Order order = (Order) ois.readObject();
                        if (order.getCustomerId() == customerId) {
                            orderList.add(order);
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
            } catch (IOException e) {
                System.err.println("Error: IOException while opening/reading order file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No orders file found.");
        }
    }

    @FXML
    public void cancelOrderbtnOnAction(ActionEvent actionEvent) {
        String orderIdToCancelText = cancelOrderIdTF.getText().trim();
        if (orderIdToCancelText.isEmpty()) {
            //
            return;
        }

        try {
            int orderIdToCancel = Integer.parseInt(orderIdToCancelText);

            //for cancel
            List<Order> remainingOrders = orderList.stream()
                    .filter(order -> order.getOrderId() != orderIdToCancel)
                    .collect(Collectors.toList());

            // updating list
            orderList.setAll(remainingOrders);

            // saving updated list and save it
            saveAllOrdersToFile(remainingOrders);

            // clear all TF
            cancelOrderIdTF.clear();

        } catch (NumberFormatException e) {

        }
    }

    private void saveAllOrdersToFile(List<Order> orders) {
        try (FileOutputStream fos = new FileOutputStream(ORDERS_FILE, false); // Overwrite the file
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (Order order : orders) {
                oos.writeObject(order);
            }
        } catch (IOException e) {
            System.err.println("Error saving updated order list to file.");
            e.printStackTrace();
        }
    }
}