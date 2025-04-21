package com.example.topclean.Customer;

import com.example.topclean.HelloApplication;
import com.example.topclean.Login;
import com.example.topclean.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class customer_dashboardFxmlController {

    @FXML
    private BorderPane customerBP;
    @FXML
    private Label nameL;
    @FXML
    private Label emailL;
    @FXML
    private Label phoneNoL;

    private User loggedInUser;
    private Login loginControllerInstance;
    private OrdersController ordersController;
    @FXML
    private Label UserIDL;

    public void setLoginController(Login loginController) {
        this.loginControllerInstance = loginController;
        System.out.println("Login Controller received in Dashboard: " + loginControllerInstance);
    }

    public void setUser(User user) {
        this.loggedInUser = user;
        if (user != null) {
            nameL.setText(user.getName());
            emailL.setText(user.getEmail());
            phoneNoL.setText(user.getPhone());
            UserIDL.setText(String.valueOf(user.getId()));
        }
    }

    @FXML
    public void dashboardbtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Customer/customer_dashboard.fxml"));
        root = fxmlLoader.load();

        customer_dashboardFxmlController dashboardController = fxmlLoader.getController();
        dashboardController.setUser(loggedInUser);

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Customer Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void profilebtnOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Customer/profile.fxml"));
        Parent root = fxmlLoader.load();
        ProfileController profileController = fxmlLoader.getController();
        if (loggedInUser != null) {
            profileController.setUser(loggedInUser);
        }
        System.out.println("Login Controller instance being passed: " + loginControllerInstance);
        profileController.setLoginController(loginControllerInstance);
        customerBP.setCenter(root);
    }

    @FXML
    public void placeAnOrderbtnOnAction(ActionEvent actionEvent) {
        System.out.println("Place An Order button clicked");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/topclean/Customer/placeAnNewOrder.fxml"));
            if (fxmlLoader.getLocation() == null) {
                System.err.println("Error: Cannot find placeAnNewOrder.fxml");
                return;
            }
            Parent placeOrderRoot = fxmlLoader.load();
            PlaceAnNewOrder placeAnNewOrderController = fxmlLoader.getController();
            if (loggedInUser != null) {
                placeAnNewOrderController.setCustomerId(loggedInUser.getId());
            }

            // place an new order  trigger to orders
            customerBP.setCenter(placeOrderRoot);
            placeAnNewOrderController.setOnOrderPlacedListener(() -> {
                try {
                    loadOrdersView();
                } catch (IOException e) {
                    System.err.println("Error loading orders view after placing order: " + e.getMessage());
                    e.printStackTrace();

                }
            });
        } catch (IOException e) {
            System.err.println("Error loading placeAnNewOrder.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void OrderBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadOrdersView();
    }

    private void loadOrdersView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/topclean/Customer/orders.fxml"));
        Parent root = fxmlLoader.load();
        ordersController = fxmlLoader.getController();
        if (loggedInUser != null) {
            ordersController.setCustomerId(loggedInUser.getId());

            // setCustomer call >> ordercontroller call >> loadcustomerorders
        }
        customerBP.setCenter(root);
    }

    @FXML
    public void makePaymentbtnOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/topclean/Customer/makePayment.fxml"));
        Parent root = fxmlLoader.load();
        MakePayment makePaymentController = fxmlLoader.getController();
        if (loggedInUser != null) {
            makePaymentController.setLoggedInCustomerId(loggedInUser.getId());
        }
        customerBP.setCenter(root);
    }

    @FXML
    public void wardrobebtnOnAction(ActionEvent actionEvent) {
        System.out.println("Wardrobe button clicked");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/topclean/Customer/Wardrobe.fxml"));
            Parent wardrobeRoot = fxmlLoader.load();
            WardrobeController wardrobeController = fxmlLoader.getController();
            if (loggedInUser != null) {
                wardrobeController.setCustomerId(loggedInUser.getId());
            }
            customerBP.setCenter(wardrobeRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void serviceReviewbtnOnAction(ActionEvent actionEvent) {
        System.out.println("Service Review button clicked");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/topclean/Customer/serviceReviews.fxml"));
            Parent serviceReviewRoot = fxmlLoader.load();
            ServiceReviews serviceReviewsController = fxmlLoader.getController();
            if (loggedInUser != null) {
                serviceReviewsController.setCustomerId(loggedInUser.getId());
            }
            customerBP.setCenter(serviceReviewRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void writeReviewbtnOnAction(ActionEvent actionEvent) {
        System.out.println("Write A Review button clicked");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/topclean/Customer/WriteAreview.fxml"));
            Parent writeReviewRoot = fxmlLoader.load();
            WriteAreview writeAreviewController = fxmlLoader.getController();
            if (loggedInUser != null) {
                writeAreviewController.setCustomerId(loggedInUser.getId());
            }
            customerBP.setCenter(writeReviewRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void signoutbtnOnAction(ActionEvent actionEvent) {
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

    @FXML
    public void myOfferbtnOnAction(ActionEvent actionEvent) {
        System.out.println("My Offer button clicked");

        try {
            URL location = getClass().getResource("/com/example/topclean/Customer/MyOffers.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(location);
            Parent myOffersRoot = fxmlLoader.load();
            MyOffers myOffersController = fxmlLoader.getController();

            //  if in future data passing  myOffersController.setCustomerId(loggedInUser.getId());
            customerBP.setCenter(myOffersRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

