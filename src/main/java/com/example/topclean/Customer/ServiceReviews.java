package com.example.topclean.Customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ServiceReviews {

    @FXML
    private TableView<Review> serviceReviewTV;
    @FXML
    private TableColumn<Review, String> serviceTC;
    @FXML
    private TableColumn<Review, String> desTC;
    @FXML
    private TableColumn<Review, String> commentTC;

    private static final String REVIEWS_FILE = "reviews.bin";
    private ObservableList<Review> reviewList = FXCollections.observableArrayList();
    private int customerId; // To store the logged-in customer's ID

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
        loadCustomerReviews();
    }

    @FXML
    public void initialize() {
        serviceTC.setCellValueFactory(new PropertyValueFactory<>("service"));
        desTC.setCellValueFactory(new PropertyValueFactory<>("description"));
        commentTC.setCellValueFactory(new PropertyValueFactory<>("comment"));

        serviceReviewTV.setItems(reviewList);
    }

    public void loadCustomerReviews() {
        reviewList.clear();
        File file = new File(REVIEWS_FILE);

        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (true) {
                    try {
                        Review review = (Review) ois.readObject();
                        if (review.getCustomerId() == this.customerId) {
                            reviewList.add(review);
                        }
                    } catch (java.io.EOFException e) {
                        break; // End of file
                    } catch (ClassNotFoundException e) {
                        System.err.println("Error reading review: Class not found - " + e.getMessage());
                        e.printStackTrace();
                    } catch (IOException e) {
                        System.err.println("Error loading reviews from file: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                System.err.println("Error opening/reading reviews file: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("No reviews file found.");
        }
    }
}