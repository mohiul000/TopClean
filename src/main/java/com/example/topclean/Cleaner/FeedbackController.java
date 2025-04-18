package com.example.topclean.Cleaner;

import com.example.topclean.Customer.Review; // Import the Review class
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

public class FeedbackController {

    @FXML
    private TableView<Review> FeedbackTV; // Use the Review class
    @FXML
    private TableColumn<Review, String> serviceTC;
    @FXML
    private TableColumn<Review, String> productDesTC; // Assuming this maps to Review's description
    @FXML
    private TableColumn<Review, String> customerCommentTC; // Assuming this maps to Review's comment
    @FXML
    private TableColumn<Review, String> adminCommentTC; // You'll need to add this to the Review class if you want to store admin comments

    private static final String REVIEWS_FILE = "reviews.bin";
    private ObservableList<Review> feedbackList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        serviceTC.setCellValueFactory(new PropertyValueFactory<>("service"));
        productDesTC.setCellValueFactory(new PropertyValueFactory<>("description"));
        customerCommentTC.setCellValueFactory(new PropertyValueFactory<>("comment"));
        adminCommentTC.setCellValueFactory(new PropertyValueFactory<>("")); // Initially empty, you'll likely add functionality to set this

        FeedbackTV.setItems(feedbackList);
        loadFeedback();
    }

    private void loadFeedback() {
        feedbackList.clear();
        File file = new File(REVIEWS_FILE);

        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (true) {
                    try {
                        Review review = (Review) ois.readObject();
                        feedbackList.add(review);
                    } catch (java.io.EOFException e) {
                        break;
                    } catch (ClassNotFoundException e) {
                        System.err.println("Error reading feedback: Class not found - " + e.getMessage());
                        e.printStackTrace();
                    } catch (IOException e) {
                        System.err.println("Error loading feedback from file: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                System.err.println("Error opening/reading feedback file: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("No feedback file found.");
        }
    }

    // You might add methods here to allow cleaners/admins to add admin comments
    // and save them back to the reviews.bin file if needed.
}