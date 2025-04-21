package com.example.topclean.Customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MyOffers {

    @FXML
    private TableColumn<Offer, String> OfferendsInTC;
    @FXML
    private TableColumn<Offer, String> MyOfferServiceTC;
    @FXML
    private TableColumn<Offer, Double> MyOfferMainPriceTC;
    @FXML
    private TableColumn<Offer, Double> DiscountPriceTC;
    @FXML
    private TableView<Offer> MyOffersTV;

    private ObservableList<Offer> offerList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        MyOfferServiceTC.setCellValueFactory(new PropertyValueFactory<>("service"));
        MyOfferMainPriceTC.setCellValueFactory(new PropertyValueFactory<>("mainPrice"));
        DiscountPriceTC.setCellValueFactory(new PropertyValueFactory<>("discountPrice"));
        OfferendsInTC.setCellValueFactory(new PropertyValueFactory<>("offerEndsIn"));

        //  dummy data to just the table
        offerList.addAll(
                new Offer("T Shirt Wash", 50.0, 40.0, "2 days"),
                new Offer("Suit Cleaning", 120.0, 95.0, "5 days"),
                new Offer("Iron Any Service", 80.0, 65.0, "3 days"),
                new Offer("Carpet Cleaning", 90.0, 75.0, "7 days")
        );


        MyOffersTV.setItems(offerList);
    }


    public static class Offer {
        private String service;
        private double mainPrice;
        private double discountPrice;
        private String offerEndsIn;

        public Offer(String service, double mainPrice, double discountPrice, String offerEndsIn) {
            this.service = service;
            this.mainPrice = mainPrice;
            this.discountPrice = discountPrice;
            this.offerEndsIn = offerEndsIn;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public double getMainPrice() {
            return mainPrice;
        }

        public void setMainPrice(double mainPrice) {
            this.mainPrice = mainPrice;
        }

        public double getDiscountPrice() {
            return discountPrice;
        }

        public void setDiscountPrice(double discountPrice) {
            this.discountPrice = discountPrice;
        }

        public String getOfferEndsIn() {
            return offerEndsIn;
        }

        public void setOfferEndsIn(String offerEndsIn) {
            this.offerEndsIn = offerEndsIn;
        }
    }
}